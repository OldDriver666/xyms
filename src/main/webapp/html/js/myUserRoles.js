$(function(){
    var companyId = Util.cookieStorage.getCookie("companyId");
    var roleId = Util.cookieStorage.getCookie("roleId");

    //页面加载左侧Menu菜单栏
	var Index = {
        myUserRoles: function(){
            var myUserRolesArray = [];
            var url = ctx + "xiaoyusvr/boss/role/query";
            var moduleId = 0;
            var data = new Object();
            data.role_id = parseInt(roleId);
            data.company_id = parseInt(companyId);
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS && result.data != ""){
                    myUserRolesArray = result.data;
                    localStorage.setItem("myUserRolesArray",JSON.stringify(myUserRolesArray));
                } else {
                    /*alert(result.msg);*/
                }
            },function() {
                /*alert("服务器开个小差，请稍后重试！")*/
            });
        }
	};
    Index.myUserRoles();
});

