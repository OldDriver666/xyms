$(function(){
    var userName = Util.cookieStorage.getCookie("username");
    var token_value = Util.cookieStorage.getCookie("accesstoken");
    var depart_id = Util.cookieStorage.getCookie("departId");
    var role_level = Util.cookieStorage.getCookie("userLevel");
	var admin_id = Util.cookieStorage.getCookie("adminId");
    var nick_name = Util.cookieStorage.getCookie("nickname");

    //页面加载左侧Menu菜单栏
	var Index = {
        //获取全部公司团体数据
        allCompanyQuery: function(){
            var allCompanyArray = [];
            var url = ctx + "xiaoyusvr/boss/organization/query";
            var moduleId = 0;
            var data = new Object();
            data.name = "";
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS && result.data != ""){
                    allCompanyArray = result.data;
                    localStorage.setItem("allCompanyArray",JSON.stringify(allCompanyArray));
                } else {
                    /*alert(result.msg);*/
                }
            },function() {
                /*alert("服务器开个小差，请稍后重试！")*/
            });

        },
	};
    Index.allCompanyQuery();
});

