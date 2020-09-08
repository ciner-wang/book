layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider','form','jquery'], function() {
    var laydate = layui.laydate //日期
        , laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , carousel = layui.carousel //轮播
        , upload = layui.upload //上传
        ,form=layui.form
        , element = layui.element //元素操作
        , slider = layui.slider //滑块
        , $ = layui.$ //jquery



    //监听Tab切换
    element.on('tab(booklist)', function (data) {
        layer.tips('切换了 ' + data.index + '：' + this.innerHTML, this, {
            tips: 1
        });
    });

    //执行一个 table 实例
    var booktable = table.render({
        elem: '#booklist'
        , height: 600
        , url: '/Book/list' //数据接口
        ,parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.status, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.total, //解析数据长度
                "data": res.item //解析数据列表
            };
        }

        , title: '图书表'
        , page: true //开启分页
        ,request: {
            pageName: 'page' //页码的参数名称，默认：page
            ,limitName: 'limit' //每页数据量的参数名，默认：limit
        }

        , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , totalRow: true //开启合计行
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'bookId', title: 'ID'}
            ,{field: 'bookImage', title: '图书封面'
                // d :每行数据对应的对象 templet:"#imgtmp",width: 70,unresize:true
                //  根路径开始取：/img/1.jpg
                ,templet:function (d) {
                    return "<img     src=/"+d.bookImage+" style='width:70px'/>"
                }
            }
            ,{field: 'bookName', title: '书名'}
            ,{field: 'bookAuthor', title: '作者'}
            ,{field: 'publishCode', title: '出版号' }
            ,{field: 'bookClass', title: '所属类别'
                ,templet:function (d) {
                 return d.bookClass.className;
                  }
                  }
            ,{field: 'publishName', title: '出版商'}
            ,{field: 'publishTime', title: '出版时间', width: 165,sort: true,unresize:true
                ,templet:function(d){
                    return showTime(d.publishTime);
                }
            }
            ,{field: 'bookPrice', title: '单价',sort: true}
            ,{field: 'bookInfo', title: '简介' }
            ,{field: 'salesNum', title: '销量' ,sort: true}
            ,{fixed: 'right',  width: 165, align:'center',toolbar: '#barDemo'}
        ]]
    })
    //监听头工具栏事件
    table.on('toolbar(booklist)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data; //获取选中的数据
        var ids = [];
        for (var i=0;i<data.length;i++){
            ids.push(data[i].bookId)
        }

        switch(obj.event){
            case 'add':
                layer.open({
                    //0（信息框，默认）1（页面层）2（iframe层）
                    type:2,
                    content:"/add_book_page",//上传视频的页面
                    area: ['70%', '70%'],
                    shade:0.8,//弹层外的区域透明度取值
                    shadeClose:true//是否点击遮罩关闭
                })
                break;
            case 'delete':
                if(data.length === 0){
                    layer.msg('请选择一行!！！');
                } else {
                    //将数据传book到后台
                    $.ajax({
                        url:"/Book/deleteBatch",
                        type:"post",
                        data:{
                          "ids":ids
                        },
                        //直接传输数组，需要将traditional:true,
                        traditional:true,
                        dataType:"json",//返回的数据结果为json格式,
                        success:function(res){//后台请求执行成功之后的回调函数
                            //res:后台接口返回的数据
                            if(res.status==200){
                                //index:便于layui记录弹框的索引
                                //function:点击alert弹框的确定之后执行的函数
                                layer.alert("确认删除第"+ids+"条吗？",function(index){

                                    //关闭弹框
                                    layer.close(index);
                                    //重载表格
                                    booktable.reload();
                                })
                            }
                        }
                    })

                }
                break;
        };
    });

//监听行工具事件
    table.on('tool(booklist)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'del'){
            $.ajax({
                url:"/Book/deleteBatch",
                type:"post",
                data:{
                    "ids":data.bookId
                },
                //直接传输数组，需要将traditional:true,
                traditional:true,
                dataType:"json",//返回的数据结果为json格式,
                success:function(res){//后台请求执行成功之后的回调函数
                    //res:后台接口返回的数据
                    if(res.status==200){
                        //index:便于layui记录弹框的索引
                        //function:点击alert弹框的确定之后执行的函数
                        layer.alert("确认删除"+data.bookName+"这本书吗？",function(index){
                            obj.del(); //删除对应行（tr）的DOM结构
                            //关闭弹框
                            layer.close(index);
                            //重载表格
                            booktable.reload();
                        })
                    }
                }
            })

        } else if(layEvent === 'edit'){
            window.location.href="/Book/edit?id="+data.bookId;//窗口跳转传参
        }
    });

    //图片上传
    var uploadInst = upload.render({
        elem: '#head_upload'//上传按钮的id
        ,url: '/Book/upload' //上传接口
        //,accept:"img"//指定上传文件的类型(默认是图片)
        ,auto:true//设置true，选完文件自动上传，
        //如果设置为false,需要bindAction参数指向其他按钮实现文件上传
        ,field:"uploadFile"//设定文件域的字段名
        ,data:{
            parentName:"img/bookphoto"// 上传文件的父文件夹(video或者img)
        }
        ,done: function(res){
            if(res.status==200){
                $("#bookImage").val(res.item)//文件存储相对路径给输入框赋值
                $("#delimg").show()//显示删除封面的按钮
                //上传封面按钮设置为不可点击的状态  class:layui-btn-disabled
                $("#head_upload").addClass("layui-btn-disabled");//只是个样式
                $("#head_upload").attr("disabled",true);//按钮设置为不可点击状态
            }
        }
        ,error: function(){
            layer.msg("网络延迟，上传失败")
        }
    });

    //文件删除(删除视频、图片)
    $(".remove-upload").click(function(){
        //获取当前点击删除按钮的id,来确定是视频还是图片？
        var id = $(this).attr("id");
        //定义一个变量存储向后台传递的被删除文件的路径
        var removeTarget = "";
        //定义变量存储上传文件的按钮
        var uploadButton = "";
        //定义变量存储上传路径显示的标签
        var showPath = "";
        if(id=="delvideo"){
            //删除视频
            removeTarget = $("#videoPath").val()//视频的路径
            uploadButton = $("#choosevideo")//上传视频的按钮
            showPath = $("#videoPath")//显示视频路径的标签
        }else if(id=="delimg"){
            //删除图片
            removeTarget = $("#bookImage").val()//图片的路径
            uploadButton = $("#head_upload")//上传图片的按钮
            showPath = $("#bookImage")//显示封面路径的标签
        }
        //使用ajax根据文件路径删除文件
        $.ajax({
            url:"/Book/removeFile",
            type:"post",
            data:{
                //被删除文件的相对路径
                removeTarget:removeTarget
            },
            dataType:"json",
            success:function(res){
                layer.msg(res.message);
                //隐藏删除标签
                $("#"+id).hide()
                //对应的上传按钮恢复为可点击的状态
                uploadButton.removeClass("layui-btn-disabled");
                uploadButton.attr("disabled",false);
                //清空当前被删除视频的路径
                showPath.val("");
            }
        })
    })
//监听修改视频提交
    // add:是submit按钮的lay-filter取值
    form.on('submit(sureEdit)', function(data){

        $.ajax({
            type:"post",
            url:"/Book/updateBook",
            data:data.field,//当前容器的全部表单字段提交到后台，
            dataType:"json",
            success:function(res){
                if(res.status==200){
                    layer.msg(res.message);
                    window.location.href="/booklist_page";
                }else{
                    layer.msg(res.message);
                }
            }
        })
        return false;//阻止表单跳转
    });

    //监听添加视频提交
    // add:是submit按钮的lay-filter取值
    form.on('submit(add)', function(data){
        // console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
        // console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
        // console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        // return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        $.ajax({
            type:"post",
            url:"/Book/addBook",
            data:data.field,//当前容器的全部表单字段提交到后台，
            dataType:"json",
            success:function(res){
                if(res.status==200){
                    layer.msg(res.message);
                    window.parent.location.reload()//父窗口重载
                }else{
                    layer.msg(res.message);
                }
            }
        })

        return false;//阻止表单跳转
    });

    //时间实例化
    laydate.render({
        elem: '#publishTime', //指定元素
        type:"date",// date:日期   datetime:日期和时间
        range:"~"//定义分割字符
    });


    //时间实例化
    laydate.render({
        elem: '#timerange', //指定元素
        type:"date",// date:日期   datetime:日期和时间
        range:"~"//定义分割字符
    });
    //根据关键字进行搜索
    $("#search").click(function(){
        //为了我们搜索之后重新渲染表格数据方便，我们使用重载
        booktable.reload({
            where: { //设定异步数据接口的额外参数，任意设
                keyword:$("#keyword").val(),//关键字
                timerange:$("#timerange").val()//时间范围
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            },
            url: '/Book/search'
        });
    });
});