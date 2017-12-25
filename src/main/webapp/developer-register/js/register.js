$(function(){
    var userType = null;
    var errorCount = 0;
    $('#personal input[_type="sendmailcode"]').attr("disabled", "disabled");
    $('#agency input[_type="sendmailcode"]').attr("disabled", "disabled");

    //选择开发者用户注册类型 1个人 2公司
    $('div[_type="person"]').on('click', function(){
        $(".register-entrance").hide();
        $(".register-wrap").show();
        $("#personal").show();
        $("#agency").hide();
        userType = 1;
    });

    $('div[_type="agency"]').on('click', function(){
        $(".register-entrance").hide();
        $(".register-wrap").show();
        $("#personal").hide();
        $("#agency").show();
        userType = 2;
    });

    //页面前进后退
    $('a[_type="step0"]').on('click', function(){
        $(".register-entrance").show();
        $(".register-wrap").hide();
        $("#personal").hide();
        $("#agency").hide();
    });


    //提交注册信息
    $('a[_type="submit"]').on('click', function(){
        if(userType == 1){

            var reg = /^1[0-9]{10}$/;
            var phoneNum = $('#personal input[_key="tel"]').val();//手机号码
            var phoneFlag = reg.test(phoneNum); //true

            var szReg= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
            var szMail = $('#personal input[_key="mail"]').val();
            var mailFlag = szReg.test(szMail);

            var imgFlag = (document.getElementById("preview1").src == window.location.href) && (document.getElementById("preview2").src == window.location.href) && (document.getElementById("preview3").src == window.location.href);

            if($('#personal input[_key="account"]').val() == ""){
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
            }else if($('#personal input[_key="name"]').val() == ""){
                $('#personal div[_errorTips="name"]').show();
                return;
            }else if($('#personal input[_key="id_card"]').val() == ""){
                $('#personal div[_errorTips="id_card"]').show();
                return;
            }else if(imgFlag == true){
                $('#personal div[_errorTips="uuid"]').show();
                return;
            }else if(phoneFlag == false){
                $('#personal div[_errorTips="tel"]').show();
                return;
            }else if($('#personal span[_key="agree_msg_notice"]').attr("_value") == 0){
                $('#personal div[_errorTips="agree_msg_notice"]').show();
                return;
            }else if(mailFlag == false){
                $('#personal div[_errorTips="mail"]').show();
                $('#personal div[_errorTips="mailAlready"]').hide();
                $('#personal div[_errorTips="mailNone"]').hide();
                return;
            }else if($('#personal input[_key="emailcode"]').val() == ""){
                $('#personal div[_errorTips="emailcode"]').show();
                return;
            }else if($(".agree-wp .ui-ico-chk").attr("_value") == 0){
                $('div[_errorTips="agree-wp"]').show();
                return;
            }

            var form = new FormData();
            var newBase64Data1 = document.getElementById("preview1").src;
            var newBase64Data2 = document.getElementById("preview2").src;
            var newBase64Data3 = document.getElementById("preview3").src;
            if(newBase64Data1 != window.location.href){
                var blob1 = dataURItoBlob(newBase64Data1); // 上一步中的函数
                form.append("images", blob1, 'image1.png');
            }
            if(newBase64Data2 != window.location.href){
                var blob2 = dataURItoBlob(newBase64Data2); // 上一步中的函数
                form.append("images", blob2, 'image2.png');
            }
            if(newBase64Data3 != window.location.href){
                var blob3 = dataURItoBlob(newBase64Data3); // 上一步中的函数
                form.append("images", blob3, 'image3.png');
            }
            /*var blob1 = dataURItoBlob(newBase64Data1); // 上一步中的函数
            var blob2 = dataURItoBlob(newBase64Data2); // 上一步中的函数
            var blob3 = dataURItoBlob(newBase64Data3); // 上一步中的函数
            form.append("images", blob1, 'image1.png');
            form.append("images", blob2, 'image2.png');
            form.append("images", blob3, 'image3.png');*/
            form.append("account", $('#personal input[_key="account"]').val());
            form.append("password",$.md5($('#personal input[_key="password"]').val()));
            form.append("nickName", $('#personal input[_key="name"]').val());
            form.append("phone", $('#personal input[_key="tel"]').val());
            form.append("email", $('#personal input[_key="mail"]').val());
            form.append("idCard", $('#personal input[_key="id_card"]').val());
            form.append("description", '');
            form.append("userType", 1);

            var url = ctx + "xiaoyusvr/boss/developer/queryAccount";
            var data = new Object();
            data.account = $('#personal input[_key="account"]').val();

            Util.ajaxLoadData(url,data,"POST",true,function(result) {
                if(result.msg == "该账户已注册"){
                    $('#personal div[_errorTips="accountAlready"]').show();
                    $('#personal div[_errorTips="accountNone"]').hide();
                }else if(result.msg == "该账户未注册"){
                    var url1 = ctx + "xiaoyusvr/boss/developer/checkcode";
                    var data1 = new Object();
                    data1.checkCode = $('#personal input[_key="emailcode"]').val();

                    Util.ajaxLoadData(url1,data1,"POST",true,function(result1) {
                        if (result1.msg == "邮箱验证通过") {
                            $.ajax({
                                url:ctx + "xiaoyusvr/boss/developer/register",
                                type:"post",
                                data:form,
                                dataType: 'json',
                                processData:false,
                                contentType: false,
                                success:function(result){
                                    if (result.code == ReturnCode.SUCCESS) {
                                        $(".register-entrance").hide();
                                        $(".register-wrap").hide();
                                        $(".finish-entrance").show();
                                    }else{
                                        alert(result.msg);
                                    }
                                },
                                error:function(e){
                                    alert("错误！！");
                                }
                            });
                        }else if(result1.msg == "验证码有误，请重新输入！"){
                            errorCount++;
                            if(errorCount == 1){
                                alert("对不起，您输入错误1次，还有2次输入机会");
                            }else if(errorCount == 2){
                                alert("对不起，您输入错误2次，还有1次输入机会");
                            }else if(errorCount == 3){
                                alert("对不起，您输入错误3次，请重新核实输入注册信息");
                                $(".register-entrance").show();
                                $(".register-wrap").hide();
                                $(".finish-entrance").hide();
                                errorCount = 0;
                            }
                        }else{
                            alert(result1.msg);
                        }
                    });
                }
            });

        }else if(userType == 2){
            var reg = /^1[0-9]{10}$/;
            var phoneNum = $('#agency input[_key="tel"]').val();//手机号码
            var phoneFlag = reg.test(phoneNum); //true

            var szReg= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
            var szMail = $('#agency input[_key="mail"]').val();
            var mailFlag = szReg.test(szMail);

            var imgFlag = (document.getElementById("preview4").src == window.location.href) && (document.getElementById("preview5").src == window.location.href) && (document.getElementById("preview6").src == window.location.href);

            if($('#agency input[_key="account"]').val() == ""){
                $('#agency div[_errorTips="account"]').show();
                $('#agency div[_errorTips="accountAlready"]').hide();
                $('#agency div[_errorTips="accountNone"]').hide();
                return;
            }else if($('#agency input[_key="password"]').val() == ""){
                $('#agency div[_errorTips="password"]').show();
                return;
            }else if($('#agency input[_key="passwordAgain"]').val() == ""){
                $('#agency div[_errorTips="passwordAgain"]').show();
                $('#agency div[_errorTips="passworderror"]').hide();
                return;
            }else if($('#agency input[_key="password"]').val() != $('#agency input[_key="passwordAgain"]').val()){
                $('#agency div[_errorTips="passwordAgain"]').hide();
                $('#agency div[_errorTips="passworderror"]').show();
                return;
            }else if($('#agency input[_key="nickname"]').val() == ""){
                $('#agency div[_errorTips="nickname"]').show();
                return;
            }else if($('#agency input[_key="name"]').val() == ""){
                $('#agency div[_errorTips="name"]').show();
                return;
            }else if($('#agency input[_key="id_card"]').val() == ""){
                $('#agency div[_errorTips="id_card"]').show();
                return;
            }else if(imgFlag == true){
                $('#agency div[_errorTips="uuid"]').show();
                 return;
            }else if($('#agency input[_key="contact"]').val() == ""){
                $('#agency div[_errorTips="contact"]').show();
                return;
            }else if(phoneFlag == false){
                $('#agency div[_errorTips="tel"]').show();
                return;
            }else if(mailFlag == false){
                $('#agency div[_errorTips="mail"]').show();
                $('#agency div[_errorTips="mailAlready"]').hide();
                $('#agency div[_errorTips="mailNone"]').hide();
                return;
            }else if($('#agency input[_key="emailcode"]').val() == ""){
                $('#agency div[_errorTips="emailcode"]').show();
                return;
            }else if($(".agree-wp .ui-ico-chk").attr("_value") == 0){
                $('div[_errorTips="agree-wp"]').show();
                return;
            }

            var form = new FormData();
            var newBase64Data4 = document.getElementById("preview4").src;
            var newBase64Data5 = document.getElementById("preview5").src;
            var newBase64Data6 = document.getElementById("preview6").src;
            if(newBase64Data4 != window.location.href){
                var blob4 = dataURItoBlob(newBase64Data4); // 上一步中的函数
                form.append("images", blob4, 'image4.png')
            }
            if(newBase64Data5 != window.location.href){
                var blob5 = dataURItoBlob(newBase64Data5); // 上一步中的函数
                form.append("images", blob5, 'image5.png');
            }
            if(newBase64Data6 != window.location.href){
                var blob6 = dataURItoBlob(newBase64Data6); // 上一步中的函数
                form.append("images", blob6, 'image6.png');
            }
           /* var blob4 = dataURItoBlob(newBase64Data4); // 上一步中的函数
            var blob5 = dataURItoBlob(newBase64Data5); // 上一步中的函数
            var blob6 = dataURItoBlob(newBase64Data6); // 上一步中的函数
            form.append("images", blob4, 'image4.png');
            form.append("images", blob5, 'image5.png');
            form.append("images", blob6, 'image6.png');*/
            form.append("account", $('#agency input[_key="account"]').val());
            form.append("password",$.md5($('#agency input[_key="password"]').val()));
            form.append("nickName", $('#agency input[_key="name"]').val());
            form.append("phone", $('#agency input[_key="tel"]').val());
            form.append("email", $('#agency input[_key="mail"]').val());
            form.append("idCard", $('#agency input[_key="id_card"]').val());
            form.append("description", $('#agency input[_key="contact"]').val());
            form.append("userType", 2);

            var url = ctx + "xiaoyusvr/boss/developer/queryAccount";
            var data = new Object();
            data.account = $('#agency input[_key="account"]').val();

            Util.ajaxLoadData(url,data,"POST",true,function(result) {
                if(result.msg == "该账户已注册"){
                    $('#agency div[_errorTips="accountAlready"]').show();
                    $('#agency div[_errorTips="accountNone"]').hide();
                }else if(result.msg == "该账户未注册"){
                    var url1 = ctx + "xiaoyusvr/boss/developer/checkcode";
                    var data1 = new Object();
                    data1.checkCode = $('#agency input[_key="emailcode"]').val();

                    Util.ajaxLoadData(url1,data1,"POST",true,function(result1) {
                        if (result1.msg == "邮箱验证通过") {
                            $.ajax({
                                url:ctx + "xiaoyusvr/boss/developer/register",
                                type:"post",
                                data:form,
                                dataType: 'json',
                                processData:false,
                                contentType: false,
                                success:function(result){
                                    if (result.code == ReturnCode.SUCCESS) {
                                        $(".register-entrance").hide();
                                        $(".register-wrap").hide();
                                        $(".finish-entrance").show();
                                    }else{
                                        alert(result.msg);
                                    }
                                },
                                error:function(e){
                                    alert("错误！！");
                                }
                            });
                        }else if(result1.msg == "验证码有误，请重新输入！"){
                            errorCount++;
                            if(errorCount == 1){
                                alert("对不起，您输入错误1次，还有2次输入机会");
                            }else if(errorCount == 2){
                                alert("对不起，您输入错误2次，还有1次输入机会");
                            }else if(errorCount == 3){
                                alert("对不起，您输入错误3次，请重新核实输入注册信息");
                                $(".register-entrance").show();
                                $(".register-wrap").hide();
                                $(".finish-entrance").hide();
                                errorCount = 0;
                            }
                        }else{
                            alert(result1.msg);
                        }
                    });
                }
            });
        }
    });

    //发送验证码到邮箱
    $('#personal input[_type="sendmailcode"]').on('click', function(){
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
                alert(result.msg);
                $('#personal div[_errorTips="emailcodeAlready"]').show();
                $('#personal div[_errorTips="emailcodeNone"]').hide();
            }
        });
    });

    $('#agency input[_type="sendmailcode"]').on('click', function(){
        var emailAddr = $('#agency input[_key="mail"]').val();
        var url = ctx + "xiaoyusvr/boss/developer/sendcode";
        var data = new Object();
        data.emailaddress = emailAddr;

        Util.ajaxLoadData(url,data,"POST",true,function(result) {
            if (result.code == ReturnCode.SUCCESS) {
                $('#agency div[_errorTips="emailcodeAlready"]').hide();
                $('#agency div[_errorTips="emailcodeNone"]').show();
                var tt =$('#agency input[_type="sendmailcode"]');
                settime(tt);
            }else{
                alert(result.msg);
                $('#agency div[_errorTips="emailcodeAlready"]').show();
                $('#agency div[_errorTips="emailcodeNone"]').hide();
            }
        });
    });



    //选择证件类型
    $('span[_type="shenfenzheng"]').on('click', function(){
        $('div[_key="id_card_type"]').attr("_value", 1);
        $('span[_type="shenfenzheng"]').addClass("checked");
        $('span[_type="huzhao"]').removeClass("checked");
        $('#personal input[_key="id_card"]').attr("placeholder", "请填写18位身份证号码");
        $('#personal label[_type="uuidTitle"]').html("手持身份证照片"); 
    });

    $('span[_type="huzhao"]').on('click', function(){
        $('div[_key="id_card_type"]').attr("_value", 0);
        $('span[_type="shenfenzheng"]').removeClass("checked");
        $('span[_type="huzhao"]').addClass("checked");
        $('#personal input[_key="id_card"]').attr("placeholder", "请填写护照号");
        $('#personal label[_type="uuidTitle"]').html("护照照片");
    });

    //同意接收审核通知短信
    $('span[_key="agree_msg_notice"]').on('click', function(){
        if($('span[_key="agree_msg_notice"]').attr("_value") == 0){
            $('span[_key="agree_msg_notice"]').addClass("ui-ico-chked").attr("_value", 1);
            $('#personal div[_errorTips="agree_msg_notice"]').hide();
        }else if($('span[_key="agree_msg_notice"]').attr("_value") == 1){
            $('span[_key="agree_msg_notice"]').removeClass("ui-ico-chked").attr("_value", 0);
        }
    });

    //同意开发者协议
    $('a[_type="protocol"]').on('click', function(){
        $("#agreementDialog").show();
        var agreementContent = window._agreement;
        $("#agreementMain").append(agreementContent);
    })
    $('a[_type="closeAgreement"]').on('click', function(){
        $("#agreementDialog").hide();
        $("#agreementMain").empty();
    });
    $('a[_type="acceptAgreement"]').on('click', function(){
        $("#agreementDialog").hide();
        $(".agree-wp .ui-ico-chk").addClass("ui-ico-chked").attr("_value", 1);
        $("#agreementMain").empty();
    });
    $(".agree-wp .ui-ico-chk").on('click', function(){
        if($(".agree-wp .ui-ico-chk").attr("_value") == 0){
            $(".agree-wp .ui-ico-chk").addClass("ui-ico-chked").attr("_value", 1);
            $('div[_errorTips="agree-wp"]').hide();
        }else if($(".agree-wp .ui-ico-chk").attr("_value") == 1){
            $(".agree-wp .ui-ico-chk").removeClass("ui-ico-chked").attr("_value", 0);
        }
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

    $('#personal input[_key="name"]').change(function () {
        if ($(this).val() != "") {
            $('#personal div[_errorTips="name"]').hide();
        }
    });
    $('#personal input[_key="id_card"]').change(function () {
        if ($(this).val() != "") {
            $('#personal div[_errorTips="id_card"]').hide();
        }
    });
    $('#personal input[_key="tel"]').change(function () {
        if ($(this).val() != "") {
            $('#personal div[_errorTips="tel"]').hide();
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
                    $('#personal input[_type="sendmailcode"]').attr("disabled", "disabled");
                    $('#personal div[_errorTips="mailAlready"]').show();
                    $('#personal div[_errorTips="mailNone"]').hide();
                }else if(result.msg == "该邮箱未注册"){
                    $('#personal input[_type="sendmailcode"]').removeAttr("disabled");
                    $('#personal div[_errorTips="mailAlready"]').hide();
                    $('#personal div[_errorTips="mailNone"]').show();
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


    $('#agency input[_key="account"]').change(function () {
        if ($(this).val() != "") {
            $('#agency div[_errorTips="account"]').hide();

            var url = ctx + "xiaoyusvr/boss/developer/queryAccount";
            var data = new Object();
            data.account = $('#agency input[_key="account"]').val();

            Util.ajaxLoadData(url,data,"POST",true,function(result) {
                if(result.msg == "该账户已注册"){
                    $('#agency div[_errorTips="accountAlready"]').show();
                    $('#agency div[_errorTips="accountNone"]').hide();
                }else if(result.msg == "该账户未注册"){
                    $('#agency div[_errorTips="accountAlready"]').hide();
                    $('#agency div[_errorTips="accountNone"]').show();
                }
            });
        }else {
            $('#agency div[_errorTips="account"]').hide();
            $('#agency div[_errorTips="accountAlready"]').hide();
            $('#agency div[_errorTips="accountNone"]').hide();
        }
    });
    $('#agency input[_key="password"]').change(function () {
        if ($(this).val() != "") {
            $('#agency div[_errorTips="password"]').hide();
        }
    });
    $('#agency input[_key="passwordAgain"]').change(function () {
        if ($(this).val() != "") {
            $('#agency div[_errorTips="passwordAgain"]').hide();
            $('#agency div[_errorTips="passworderror"]').hide();
            if($('#agency input[_key="password"]').val() != $('#agency input[_key="passwordAgain"]').val()){
                $('#agency div[_errorTips="passwordAgain"]').hide();
                $('#agency div[_errorTips="passworderror"]').show();
            }
        }
    });

    $('#agency input[_key="name"]').change(function () {
        if ($(this).val() != "") {
            $('#agency div[_errorTips="name"]').hide();
        }
    });
    $('#agency input[_key="id_card"]').change(function () {
        if ($(this).val() != "") {
            $('#agency div[_errorTips="id_card"]').hide();
        }
    });
    $('#agency input[_key="contact"]').change(function () {
        if ($(this).val() != "") {
            $('#agency div[_errorTips="contact"]').hide();
        }
    });
    $('#agency input[_key="tel"]').change(function () {
        if ($(this).val() != "") {
            $('#agency div[_errorTips="tel"]').hide();
        }
    });

    $('#agency input[_key="mail"]').change(function () {
        if ($(this).val() != "") {
            var szReg= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
            var szMail = $('#agency input[_key="mail"]').val();
            var mailFlag = szReg.test(szMail);
            if(mailFlag == false){
                $('#agency div[_errorTips="mail"]').show();
                $('#agency div[_errorTips="mailAlready"]').hide();
                $('#agency div[_errorTips="mailNone"]').hide();
                return;
            }
            $('#agency div[_errorTips="mail"]').hide();
            var url = ctx + "xiaoyusvr/boss/developer/queryEmail";
            var data = new Object();
            data.email = $('#agency input[_key="mail"]').val();

            Util.ajaxLoadData(url,data,"POST",true,function(result) {
                if(result.msg == "该邮箱已注册"){
                    $('#agency input[_type="sendmailcode"]').attr("disabled", "disabled");
                    $('#agency div[_errorTips="mailAlready"]').show();
                    $('#agency div[_errorTips="mailNone"]').hide();
                }else if(result.msg == "该邮箱未注册"){
                    $('#agency input[_type="sendmailcode"]').removeAttr("disabled");
                    $('#agency div[_errorTips="mailAlready"]').hide();
                    $('#agency div[_errorTips="mailNone"]').show();
                }
            });
        } else{
            $('#agency div[_errorTips="mail"]').hide();
            $('#agency div[_errorTips="mailAlready"]').hide();
            $('#agency div[_errorTips="mailNone"]').hide();
        }
    });

    $('#agency input[_key="emailcode"]').change(function () {
        if ($(this).val() != "") {
            $('div[_errorTips="emailcode"]').hide();
        }
    });

    //输入规则：登录账户号检验有无重复，  密码  身份证号  email 检测是否合规



    //上传图片图标美化（不直接用Input或者隐藏方式）
    $('a[_uploadBox="add1"]').on('click', function(){
        var input = document.createElement('input');
        input.id = "file1";
        input.class = "fileupload";
        input.type = 'file';
        input.name = 'images';
        input.onchange = function(){
            imgPreview(input);
        };
        input.click();
    });
    $('a[_uploadBox="add2"]').on('click', function(){
        var input = document.createElement('input');
        input.id = "file2";
        input.class = "fileupload";
        input.type = 'file';
        input.name = 'images';
        input.onchange = function(){
            imgPreview(input);
        };
        input.click();
    });
    $('a[_uploadBox="add3"]').on('click', function(){
        var input = document.createElement('input');
        input.id = "file3";
        input.class = "fileupload";
        input.type = 'file';
        input.name = 'images';
        input.onchange = function(){
            imgPreview(input);
        };
        input.click();
    });
    $('a[_uploadBox="add4"]').on('click', function(){
        var input = document.createElement('input');
        input.id = "file4";
        input.class = "fileupload1";
        input.type = 'file';
        input.name = 'images';
        input.onchange = function(){
            imgPreview(input);
        };
        input.click();
    });
    $('a[_uploadBox="add5"]').on('click', function(){
        var input = document.createElement('input');
        input.id = "file5";
        input.class = "fileupload1";
        input.type = 'file';
        input.name = 'images';
        input.onchange = function(){
            imgPreview(input);
        };
        input.click();
    });
    $('a[_uploadBox="add6"]').on('click', function(){
        var input = document.createElement('input');
        input.id = "file6";
        input.class = "fileupload1";
        input.type = 'file';
        input.name = 'images';
        input.onchange = function(){
            imgPreview(input);
        };
        input.click();
    });

    //预览图片上三个按钮
    //delete button
    $('div[_uploadBox="finish1"] a[_type="delete"]').on('click', function(){
        document.getElementById("preview1").src = "";
        $('#personal a[_uploadBox="add1"]').show();
        $('#personal div[_uploadBox="finish1"]').hide();
        document.querySelector('#file1').value = null;
    });
    $('div[_uploadBox="finish2"] a[_type="delete"]').on('click', function(){
        document.getElementById("preview2").src = "";
        $('#personal a[_uploadBox="add2"]').show();
        $('#personal div[_uploadBox="finish2"]').hide();
        document.querySelector('#file2').value = null;
    });
    $('div[_uploadBox="finish3"] a[_type="delete"]').on('click', function(){
        document.getElementById("preview3").src = "";
        $('#personal a[_uploadBox="add3"]').show();
        $('#personal div[_uploadBox="finish3"]').hide();
        document.querySelector('#file3').value = null;
    });
    $('div[_uploadBox="finish4"] a[_type="delete"]').on('click', function(){
        document.getElementById("preview4").src = "";
        $('#agency a[_uploadBox="add4"]').show();
        $('#agency div[_uploadBox="finish4"]').hide();
        document.querySelector('#file4').value = null;
    });
    $('div[_uploadBox="finish5"] a[_type="delete"]').on('click', function(){
        document.getElementById("preview5").src = "";
        $('#agency a[_uploadBox="add5"]').show();
        $('#agency div[_uploadBox="finish5"]').hide();
        document.querySelector('#file5').value = null;
    });
    $('div[_uploadBox="finish6"] a[_type="delete"]').on('click', function(){
        document.getElementById("preview6").src = "";
        $('#agency a[_uploadBox="add6"]').show();
        $('#agency div[_uploadBox="finish6"]').hide();
        document.querySelector('#file6').value = null;
    });
    //replace button
    $('div[_uploadBox="finish1"] a[_type="upload"]').on('click', function(){
        //document.querySelector('#file1').click();
        $('a[_uploadBox="add1"]').click();
    });
    $('div[_uploadBox="finish2"] a[_type="upload"]').on('click', function(){
        //document.querySelector('#file2').click();
        $('a[_uploadBox="add2"]').click();
    });
    $('div[_uploadBox="finish3"] a[_type="upload"]').on('click', function(){
        //document.querySelector('#file3').click();
        $('a[_uploadBox="add3"]').click();
    });
    $('div[_uploadBox="finish4"] a[_type="upload"]').on('click', function(){
        //document.querySelector('#file4').click();
        $('a[_uploadBox="add4"]').click();
    });
    $('div[_uploadBox="finish5"] a[_type="upload"]').on('click', function(){
        //document.querySelector('#file5').click();
        $('a[_uploadBox="add5"]').click();
    });
    $('div[_uploadBox="finish6"] a[_type="upload"]').on('click', function(){
        //document.querySelector('#file6').click();
        $('a[_uploadBox="add6"]').click();
    });



});


//个人上传图片预览
function imgPreview(fileDom){
    if(fileDom.id == 'file1'){
        $('#personal a[_uploadBox="add1"]').hide();
        $('#personal div[_uploadBox="finish1"]').show();
    }else if(fileDom.id == 'file2'){
        $('#personal a[_uploadBox="add2"]').hide();
        $('#personal div[_uploadBox="finish2"]').show();
    }else if(fileDom.id == 'file3'){
        $('#personal a[_uploadBox="add3"]').hide();
        $('#personal div[_uploadBox="finish3"]').show();
    }else if(fileDom.id == 'file4'){
        $('#agency a[_uploadBox="add4"]').hide();
        $('#agency div[_uploadBox="finish4"]').show();
    }else if(fileDom.id == 'file5'){
        $('#agency a[_uploadBox="add5"]').hide();
        $('#agency div[_uploadBox="finish5"]').show();
    }else if(fileDom.id == 'file6'){
        $('#agency a[_uploadBox="add6"]').hide();
        $('#agency div[_uploadBox="finish6"]').show();
    }

    //判断是否支持FileReader
    if (window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
    }

    //获取文件
    var file = fileDom.files[0];
    var imageType = /^image\//;
    //是否是图片
    if (!imageType.test(file.type)) {
        alert("请选择图片！");
        return;
    }
    //读取完成
    reader.onload = function(e) {
        //获取图片src
        var imgurlGet = e.target.result;

        if(imgurlGet != window.location.href){
            $('div[_errorTips="uuid"]').hide();
        }

        if(fileDom.id == 'file1'){
            var img = document.getElementById("preview1");
            $('div[_uploadBox="finish1"] a[_artwork="1"]').attr("href",imgurlGet);
        }else if(fileDom.id == 'file2'){
            var img = document.getElementById("preview2");
            $('div[_uploadBox="finish2"] a[_artwork="1"]').attr("href",imgurlGet);
        }else if(fileDom.id == 'file3'){
            var img = document.getElementById("preview3");
            $('div[_uploadBox="finish3"] a[_artwork="1"]').attr("href",imgurlGet);
        }else if(fileDom.id == 'file4'){
            var img = document.getElementById("preview4");
            $('div[_uploadBox="finish4"] a[_artwork="1"]').attr("href",imgurlGet);
        }else if(fileDom.id == 'file5'){
            var img = document.getElementById("preview5");
            $('div[_uploadBox="finish5"] a[_artwork="1"]').attr("href",imgurlGet);
        }else if(fileDom.id == 'file6'){
            var img = document.getElementById("preview6");
            $('div[_uploadBox="finish6"] a[_artwork="1"]').attr("href",imgurlGet);
        }

        //图片路径设置为读取的图片
        img.src = imgurlGet;
    };
    reader.readAsDataURL(file);
}


function dataURItoBlob(base64Data) {
    var byteString;
    if (base64Data.split(',')[0].indexOf('base64') >= 0)
        byteString = atob(base64Data.split(',')[1]);
    else
        byteString = unescape(base64Data.split(',')[1]);
    var mimeString = base64Data.split(',')[0].split(':')[1].split(';')[0];
    var ia = new Uint8Array(byteString.length);
    for (var i = 0; i < byteString.length; i++) {
        ia[i] = byteString.charCodeAt(i);
    }
    return new Blob([ia], {type:mimeString});
}

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





