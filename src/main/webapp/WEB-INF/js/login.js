layui.use(['form', 'layer', 'jquery'], function () {
    //如果父窗口为空，用当前窗口，父窗口不为空用顶窗口
    var layer = parent.layer===undefined?layui.layer:top.layer
    $ = layui.$,
    form = layui.form;

    //点击登录按钮
    form.on("submit(login)",function (data) {
        $.ajax({
            url:"/login",
            type:"post",
            data:data.field,
            dataType:"json",
            success:function(res){
                if(res.status==200){
                    layer.msg(res.message);
                    //跳转到主页面
                    window.location.href = "/main";
                }else{
                    layer.msg(res.message);
                    //重刷验证码
                    window.changeCode()
                }
            }
        })
    })

    //随机生成验证码
   window.changeCode = function(){
        //重新请求验证码后台，加载图片标签
        $("#codeImg").attr("src","/getCode?time="+new Date().getTime())
    }

    //表单输入效果
    $(".loginBody .input-item").click(function (e) {
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function () {
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function () {
        $(this).parent().removeClass("layui-input-focus");
        if ($(this).val() != '') {
            $(this).parent().addClass("layui-input-active");
        } else {
            $(this).parent().removeClass("layui-input-active");
        }
    })
})





