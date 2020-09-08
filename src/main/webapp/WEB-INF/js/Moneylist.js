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
    element.on('tab(moneylist)', function (data) {
        layer.tips('切换了 ' + data.index + '：' + this.innerHTML, this, {
            tips: 1
        });
    });

    //执行一个 table 实例
    var moneytable = table.render({
        elem: '#moneylist'
        , height: 600
        , url: '/Money/list' //数据接口
        ,parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.status, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.total, //解析数据长度
                "data": res.item //解析数据列表
            };
        }

        , title: '用户余额表'
        , page: true //开启分页
        ,request: {
            pageName: 'page' //页码的参数名称，默认：page
            ,limitName: 'limit' //每页数据量的参数名，默认：limit
        }
        , toolbar: 'head-option' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , totalRow: true //开启合计行
        ,cols: [[ //表头
            //{type: 'checkbox', fixed: 'left'}
            {field: 'umId', title: 'ID'}
            ,{field: 'user', title: '用户名' ,totalRowText: '总充值额：'
                ,templet:function(d){
                    return d.user.userName;
                }
            }
            ,{field: 'umMoney', title: '用户余额',totalRow: true,edit:true}


        ]]
    });




    //根据关键字进行搜索
    $("#search").click(function(){
        //为了我们搜索之后重新渲染表格数据方便，我们使用重载
        moneytable.reload({
            where: { //设定异步数据接口的额外参数，任意设
                 keyword:$("#keyword").val(),//关键字
                 mBase:$("#mBase").val(), //关键字
                 mTop:$("#mTop").val() //关键字
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            },
            url: '/Money/search'
        });
    });
    table.on('edit(MoneyTable)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        var value = obj.value, //得到修改后的值
            field = obj.field, //当前编辑的字段名
            data = obj.data; //所在行的所有相关数据
        //去后台更新数据
        //if (typeof value == "number") {
        if (!isNaN(value)) {
            $.ajax({
                url:"/Money/updateById",
                type:"post",
                dataType:"json",
                data:{
                    "vid":data.umId,//被修改的id
                    "value":value,//被修改之后的值
                    "field":field//被修改的字段名
                },success:function(res){
                    if(res.status==200){
                        layer.msg(res.message);
                    }
                }
            })
        }else{
            layer.msg('输入有误，应是数值类型，请重新输入');
        }

    });

});