layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider','form', 'layedit'], function() {
    var laydate = layui.laydate //日期
        , laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , carousel = layui.carousel //轮播
        , upload = layui.upload //上传
        , element = layui.element //元素操作
        , slider = layui.slider //滑块
        , $ = layui.$ //jquery
        , adduserform = layui.form//表单
        , layedit = layui.layedit//编辑框
        , $ = layui.jquery;




    //执行一个 table 实例
    var ordertable = table.render({
        elem: '#ordertlist'
        , height: 600
        , url: '/Order/list' //数据接口
        ,parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.status, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.total, //解析数据长度
                "data": res.item //解析数据列表
            };
        }

        , title: '用户订单表'
        , page: true //开启分页
        ,request: {
            pageName: 'page' //页码的参数名称，默认：page
            ,limitName: 'limit' //每页数据量的参数名，默认：limit
        }
        , toolbar: 'head-option' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , totalRow: true //开启合计行
        ,cols: [[ //表头
            // {type: 'checkbox', fixed: 'left'}
            {field: 'orderId', title: 'ID'}
            ,{field: 'orderCode', title: '订单号'}
            ,{field: 'user', title: '用户名'
                ,templet:function(d){
                    return d.user.userName;
                }}
            ,{field: 'orderDate', title: '订单日期',width: 100
                ,templet:function(d){
                    return showTime(d.orderDate);
                },totalRowText: '总额：',unresize:true}
            ,{field: 'orderMoney', title: '订单金额',totalRow: true}
            ,{field: 'orderStatus', title: '订单状态'}

        ]]
    })

//时间实例化
    laydate.render({
        elem: '#timerange', //指定元素
        type:"date",// date:日期   datetime:日期和时间
        range:"~"//定义分割字符
    });
    //根据关键字进行搜索
    $("#search").click(function(){
        //为了我们搜索之后重新渲染表格数据方便，我们使用重载
        ordertable.reload({
            where: { //设定异步数据接口的额外参数，任意设
                keyword:$("#keyword").val(),//关键字
                timerange:$("#timerange").val()//时间范围
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            },
            url: '/Order/search'
        });
    });




});