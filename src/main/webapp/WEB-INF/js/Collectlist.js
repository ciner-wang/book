layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel','slider', 'upload','element'], function() {
    var laydate = layui.laydate //日期
        , laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
       , carousel = layui.carousel //轮播
        , upload = layui.upload //上传
       , element = layui.element //元素操作
      , slider = layui.slider //滑块
        , $ = layui.$ //jquery



    //监听Tab切换
    element.on('tab(collectlist)', function (data) {
        layer.tips('切换了 ' + data.index + '：' + this.innerHTML, this, {
            tips: 1
        });
    });

    //执行一个 table 实例
    var collecttable = table.render({
        elem: '#collectlist'
        , height: 600
        , url: '/Collect/list' //数据接口
        ,parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.status, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.total, //解析数据长度
                "data": res.item //解析数据列表
            };
        }

        , title: '用户购物车表'
        , page: true //开启分页
        ,request: {
            pageName: 'page' //页码的参数名称，默认：page
            ,limitName: 'limit' //每页数据量的参数名，默认：limit
        }
        , toolbar: 'head-option' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , totalRow: true //开启合计行
        ,cols: [[ //表头
           // {type: 'checkbox', fixed: 'left'}
            {field: 'collectId', title: 'ID'}
            ,{field: 'user1', title: '用户名'
                ,templet:function(d){
                        return d.user1.userName;
                }}
            ,{field: 'bookName', title: '书名'}
           ,{field: 'bookPrice', title: '单价'}
            ,{field: 'collectNum', title: '数量'}
            ,{filed: 'collectMoney',title:'小计'}

        ]]
    })



    //根据关键字进行搜索
    $("#search").click(function(){
        //为了我们搜索之后重新渲染表格数据方便，我们使用重载
        collecttable.reload({
            where: { //设定异步数据接口的额外参数，任意设
                keyword:$("#keyword").val(),//关键字

            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            },
            url: '/Collect/search'
        });
    })
});