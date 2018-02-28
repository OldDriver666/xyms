$(function(){
    var userName = Util.cookieStorage.getCookie("username");
    var token_value = Util.cookieStorage.getCookie("accesstoken");
    var depart_id = Util.cookieStorage.getCookie("departId");
    var companyId = Util.cookieStorage.getCookie("companyId");
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
                    var Lens2 = allCompanyArray.length;
                    for(var i=0; i< Lens2; i++){
                        if(allCompanyArray[i].id == companyId){
                            var myCompanyName = allCompanyArray[i].name;
                            localStorage.setItem("myCompanyName", myCompanyName);
                        }
                    }
                    //localStorage.setItem("allCompanyArray",JSON.stringify(allCompanyArray));
                } else {
                }
            },function() {
            });

        },
	};
    Index.allCompanyQuery();
});

