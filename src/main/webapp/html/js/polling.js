$(function(){
    var accessToken = Util.cookieStorage.getCookie("accessToken");

    //页面加载左侧Menu菜单栏
	var Index = {
        push: function(){
            var url = ctx + "xiaoyusvr/boss/admin/islogin";
            var data = new Object();
            var moduleId = 0;
            data.accessToken = accessToken;
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS){

                }else if(result.code == ReturnCode.TOKEN_ERROR){
                    alert(result.msg);
                    Util.cookieStorage.clearCookie("accessToken");
                    Util.cookieStorage.clearCookie("account");
                    Util.cookieStorage.clearCookie("companyId");
                    Util.cookieStorage.clearCookie("departId");
                    Util.cookieStorage.clearCookie("id");
                    Util.cookieStorage.clearCookie("nickName");
                    Util.cookieStorage.clearCookie("phone");
                    Util.cookieStorage.clearCookie("roleId");
                    Util.cookieStorage.clearCookie("creatorId");
                    Util.cookieStorage.clearCookie("userType");
                    localStorage.clear();
                    window.location.href = "login.html";
                }
            },function(errorMsg) {
                /*alert(errorMsg);*/
            });

        }
	};
    setTimeout(function(){
        Index.push();
    },200);

    setInterval(function(){
        Index.push();
    },300000);
});

