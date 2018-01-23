$(function(){
    var errorCount = 0;
    var verifyCode = 0;

    //提交注册信息
    $('a[_type="submit"]').on('click', function(){
            var szReg= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
            var szMail = $('#personal input[_key="mail"]').val();
            var mailFlag = szReg.test(szMail);


            if(mailFlag == false){
                $('#personal div[_errorTips="mail"]').show();
                $('#personal div[_errorTips="mailAlready"]').hide();
                $('#personal div[_errorTips="mailNone"]').hide();
                return;
            }else if($('#personal input[_key="emailcode"]').val() == ""){
                $('#personal div[_errorTips="emailcode"]').show();
                return;
            }else if($('#personal input[_key="account"]').val() == ""){
            $('#personal div[_errorTips="account"]').show();
            $('#personal div[_errorTips="accountAlready"]').hide();
            $('#personal div[_errorTips="accountNone"]').hide();
            return;
            }else if($('#personal input[_key="password"]').val() == ""){
                $('#personal div[_errorTips="password"]').show();
                return;
            }else if($('#personal input[_key="passwordAgain"]').val() == ""){
                $('#personal div[_errorTips="passwordAgain"]').show();
                $('#personal div[_errorTips="passworderror"]').hide();
                return;
            }else if($('#personal input[_key="password"]').val() != $('#personal input[_key="passwordAgain"]').val()){
                $('#personal div[_errorTips="passwordAgain"]').hide();
                $('#personal div[_errorTips="passworderror"]').show();
                return;
            }else if(verifyCode != 1){
                toastr.error("请重新进行邮箱验证");
                return;
            }

            var url = ctx + "xiaoyusvr/boss/developer/modifyPwd";
            var data = new Object();
            data.account = $('#personal input[_key="account"]').val();
            data.password = $.md5($('#personal input[_key="password"]').val());

            Util.ajaxLoadData(url,data,"POST",true,function(result) {
                if(result.msg == "修改密码成功"){
                    $(".register-wrap").hide();
                    $(".finish-entrance").show();
                    window.location.href = "http://xiaoyu.fise-wi.com:8787/xiaoyusvr/html/index.html";
                    $('#personal input[_key="mail"]').val("");
                    $('#personal input[_key="account"]').val("");
                    $('#personal input[_key="emailcode"]').val("");
                    verifyCode = 0;
                }else{
                    toastr.error(result.msg);
                    $(".register-wrap").show();
                    $(".finish-entrance").hide();
                }
            });
    });

    //发送验证码到邮箱
    $('#personal input[_type="sendmailcode"]').on('click', function(){
        var szReg= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        var szMail = $('#personal input[_key="mail"]').val();
        var mailFlag = szReg.test(szMail);
        if(mailFlag == false){
            $('#personal div[_errorTips="mail"]').show();
            $('#personal div[_errorTips="mailAlready"]').hide();
            $('#personal div[_errorTips="mailNone"]').hide();
            return;
        }
        var emailAddr = $('#personal input[_key="mail"]').val();
        var url = ctx + "xiaoyusvr/boss/developer/sendcode";
        var data = new Object();
        data.emailaddress = emailAddr;

        Util.ajaxLoadData(url,data,"POST",true,function(result) {
            if (result.code == ReturnCode.SUCCESS) {
                $('#personal div[_errorTips="emailcodeAlready"]').hide();
                $('#personal div[_errorTips="emailcodeNone"]').show();
                var tt =$('#personal input[_type="sendmailcode"]');
                settime(tt);
            }else{
                toastr.error(result.msg);
                $('#personal div[_errorTips="emailcodeAlready"]').show();
                $('#personal div[_errorTips="emailcodeNone"]').hide();
            }
        });
    });


    //提交验证码进行验证
    $('#personal input[_type="verifycode"]').on('click', function(){
        var szReg= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        var szMail = $('#personal input[_key="mail"]').val();
        var mailFlag = szReg.test(szMail);
        if(mailFlag == false){
            $('#personal div[_errorTips="mail"]').show();
            $('#personal div[_errorTips="mailAlready"]').hide();
            $('#personal div[_errorTips="mailNone"]').hide();
            return;
        }else if($('#personal input[_key="emailcode"]').val() == ""){
            $('#personal div[_errorTips="emailcode"]').show();
            return;
        }
        var url = ctx + "xiaoyusvr/boss/developer/checkcode";
        var data = new Object();
        data.checkCode = $('#personal input[_key="emailcode"]').val();

        Util.ajaxLoadData(url,data,"POST",true,function(result) {
            if (result.msg == "邮箱验证通过") {
                var url1 = ctx + "xiaoyusvr/boss/developer/getAccountByEmail";
                var data1 = new Object();
                data1.emailaddress = $('#personal input[_key="mail"]').val();

                Util.ajaxLoadData(url1,data1,"POST",true,function(result1) {
                    if (result1.msg == "ok") {
                        $('#personal input[_key="account"]').val(result1.data);
                        verifyCode = 1;
                    }else{
                        toastr.error(result1.msg);
                    }
                });
            }else if(result.msg == "验证码有误，请重新输入！"){
                errorCount++;
                if(errorCount == 1){
                    toastr.error("对不起，您输入错误1次，还有2次输入机会");
                }else if(errorCount == 2){
                    toastr.error("对不起，您输入错误2次，还有1次输入机会");
                }else if(errorCount == 3){
                    toastr.error("对不起，您输入错误3次，请重新核实输入注册信息");
                    errorCount = 0;
                    $('#personal input[_key="mail"]').val("");
                    $('#personal input[_key="emailcode"]').val("");
                    clearTimeout(timer);
                    $('#personal input[_type="sendmailcode"]').removeAttr("disabled");
                    $('#personal input[_type="sendmailcode"]').val("发送验证码到邮箱");
                }
            }else{
                toastr.error(result.msg);
            }
        });
    });


    //输入改变，自动检测改变输出提示信息
    $('#personal input[_key="account"]').change(function () {
        if ($(this).val() != "") {
            $('#personal div[_errorTips="account"]').hide();

            var url = ctx + "xiaoyusvr/boss/developer/queryAccount";
            var data = new Object();
            data.account = $('#personal input[_key="account"]').val();

            Util.ajaxLoadData(url,data,"POST",true,function(result) {
                if(result.msg == "该账户已注册"){
                    $('#personal div[_errorTips="accountAlready"]').show();
                    $('#personal div[_errorTips="accountNone"]').hide();
                }else if(result.msg == "该账户未注册"){
                    $('#personal div[_errorTips="accountAlready"]').hide();
                    $('#personal div[_errorTips="accountNone"]').show();
                }
            });
        }else {
            $('#personal div[_errorTips="account"]').hide();
            $('#personal div[_errorTips="accountAlready"]').hide();
            $('#personal div[_errorTips="accountNone"]').hide();
        }
    });
    $('#personal input[_key="password"]').change(function () {
        if ($(this).val() != "") {
            $('#personal div[_errorTips="password"]').hide();
        }
    });
    $('#personal input[_key="passwordAgain"]').change(function () {
        if ($(this).val() != "") {
            $('#personal div[_errorTips="passwordAgain"]').hide();
            $('#personal div[_errorTips="passworderror"]').hide();
            if($('#personal input[_key="password"]').val() != $('#personal input[_key="passwordAgain"]').val()){
                $('#personal div[_errorTips="passwordAgain"]').hide();
                $('#personal div[_errorTips="passworderror"]').show();
            }
        }
    });



    $('#personal input[_key="mail"]').change(function () {
        if ($(this).val() != "") {
            var szReg= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
            var szMail = $('#personal input[_key="mail"]').val();
            var mailFlag = szReg.test(szMail);


            if(mailFlag == false){
                $('#personal div[_errorTips="mail"]').show();
                $('#personal div[_errorTips="mailAlready"]').hide();
                $('#personal div[_errorTips="mailNone"]').hide();
                return;
            }
            $('#personal div[_errorTips="mail"]').hide();
            var url = ctx + "xiaoyusvr/boss/developer/queryEmail";
            var data = new Object();
            data.email = $('#personal input[_key="mail"]').val();

            Util.ajaxLoadData(url,data,"POST",true,function(result) {
                if(result.msg == "该邮箱已注册"){
                    $('#personal input[_type="sendmailcode"]').removeAttr("disabled");
                    $('#personal div[_errorTips="mailAlready"]').hide();
                    $('#personal div[_errorTips="mailNone"]').show();
                }else if(result.msg == "该邮箱未注册"){
                    $('#personal input[_type="sendmailcode"]').attr("disabled", "disabled");
                    $('#personal div[_errorTips="mailAlready"]').show();
                    $('#personal div[_errorTips="mailNone"]').hide();
                }
            });
        } else{
            $('#personal div[_errorTips="mail"]').hide();
            $('#personal div[_errorTips="mailAlready"]').hide();
            $('#personal div[_errorTips="mailNone"]').hide();
        }
    });
    $('#personal input[_key="emailcode"]').change(function () {
        if ($(this).val() != "") {
            $('div[_errorTips="emailcode"]').hide();
        }
    });

});

var countdown=120;

function settime(val) {
    if (countdown == 0) {
        val.removeAttr("disabled");
        val.val("发送验证码到邮箱");
        clearTimeout(timer);
        countdown = 120;
    } else {
        val.attr("disabled", "disabled");
        val.val("重新发送(" + countdown + ")");
        countdown--;
        timer = setTimeout(function() {
            settime(val)
        },1000)
    }
}





