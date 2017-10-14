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

