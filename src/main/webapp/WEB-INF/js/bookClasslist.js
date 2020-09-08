layui.use(['laydate', 'laypage', 'jquery','layer', 'table', 'carousel','slider', 'upload','element','form'], function() {
    var laydate = layui.laydate //日期
        , laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
       , carousel = layui.carousel //轮播
        , upload = layui.upload //上传
       , element = layui.element //元素操作
      , slider = layui.slider //滑块
        ,form=layui.form
        , $ = layui.$ //jquery



    //监听Tab切换
    element.on('tab(bookClasslist)', function (data) {
        layer.tips('切换了 ' + data.index + '：' + this.innerHTML, this, {
            tips: 1
        });
    });

    //执行一个 table 实例
    var bookClasstable = table.render({
        elem: '#bookClasslist'
        , height: 600
        , url: '/BookClass/list' //数据接口
        ,parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.status, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.total, //解析数据长度
                "data": res.item //解析数据列表
            };
        }

        , title: '图书类别'
        , page: true //开启分页
        ,request: {
            pageName: 'page' //页码的参数名称，默认：page
            ,limitName: 'limit' //每页数据量的参数名，默认：limit
        }
        , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , totalRow: true //开启合计行
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
           , {field: 'classId', title: 'ID'}
            ,{field: 'classCode', title: '类别号' }
            ,{field: 'className', title: '类别名',edit:true}
            ,{fixed: 'right', toolbar: '#barDemo'}

        ]]
    });




    table.on('edit(ClassTable)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        var value = obj.value, //得到修改后的值
            field = obj.field, //当前编辑的字段名
            data = obj.data; //所在行的所有相关数据
        //去后台更新数据
        //if (typeof value == "number") {
            $.ajax({
                url:"/BookClass/updateById",
                type:"post",
                dataType:"json",
                data:{
                    "vid":data.classId,//被修改的id
                    "value":value,//被修改之后的值
                    "field":field//被修改的字段名
                },success:function(res){
                    if(res.status==200){
                        layer.msg(res.message);
                    }
                    else {
                        layer.msg(res.message);
                    }
                }
            })

    });
    table.on('tool(ClassTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data;//获得当前行数据
            $.ajax({
                url:"/BookClass/deleteBatch",
                type:"post",
                data:{
                    "ids":data.classCode
                },
                //直接传输数组，需要将traditional:true,
                traditional:true,
                dataType:"json",//返回的数据结果为json格式,
                success:function(res){//后台请求执行成功之后的回调函数
                    //res:后台接口返回的数据
                    if(res.status==200){
                        //index:便于layui记录弹框的索引
                        //function:点击alert弹框的确定之后执行的函数
                        layer.alert("确认删除"+data.className+"吗？",function(index){
                            obj.del(); //删除对应行（tr）的DOM结构
                            //关闭弹框
                            layer.close(index);
                            //重载表格
                            bookClasstable.reload();
                        })
                    }
                }
            })
    });
    //监听头工具栏事件
    table.on('toolbar(ClassTable)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data; //获取选中的数据
        var ids = [];
        for (var i=0;i<data.length;i++){
            ids.push(data[i].classCode)
        }

        switch(obj.event){
            case 'add':
                layer.alert("暂无此功能!");
                break;

            case 'delete':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else {
                    //将数据传到后台
                    $.ajax({
                        url:"/BookClass/deleteBatch",
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
                                    //刷新列表表格

                                    window.location.href="/bookclasslist_page";
                                })
                            }
                        }
                    });

                }
                break;
        };
    });

});