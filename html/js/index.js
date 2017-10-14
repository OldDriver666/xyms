$(function(){
    var departId = Util.cookieStorage.getCookie("departId");
    var companyId = Util.cookieStorage.getCookie("companyId");
    var roleId = Util.cookieStorage.getCookie("roleId");
	var id = Util.cookieStorage.getCookie("id");
    var nickName = Util.cookieStorage.getCookie("nickName");

    //安全退出
    $("#header-safeExit").on('click', function(){
        var url = ctx + "xiaoyusvr/boss/admin/logout";
        var data = {"admin_id": parseInt(id)};
        Util.ajaxLoadData(url,data,0,"POST",true,function(result) {
            if(result.code == ReturnCode.SUCCESS){
                Util.cookieStorage.clearCookie("accessToken");
                Util.cookieStorage.clearCookie("account");
                Util.cookieStorage.clearCookie("companyId");
                Util.cookieStorage.clearCookie("departId");
                Util.cookieStorage.clearCookie("id");
                Util.cookieStorage.clearCookie("nickName");
                Util.cookieStorage.clearCookie("phone");
                Util.cookieStorage.clearCookie("roleId");
                localStorage.removeItem("myDevTypeArray");
                localStorage.removeItem("myUserRolesArray");
                localStorage.removeItem("allDevTypeArray");
                localStorage.removeItem("allCompanyArray");
                window.location.href = "login.html";
            } else if(result.Status == 1){
                alert("服务器开个小差，请稍后重试！");
            } else {
                alert("账户名、密码或错误！");
            }
        },function() {
            alert("服务器开个小差，请稍后重试！");
        });
    });

    //页面加载左侧Menu菜单栏
	var Index = {
		init:function(){
			Index.loadMenu();
            $("#admin-header-nick").text(nickName);
		},
		loadMenu : function(){
            var url = ctx + "xiaoyusvr/boss/role/queryAuth";
            var moduleId= 0;
            var data = {
                "role_id":parseInt(roleId),
                "company_id":parseInt(companyId),
                "include_all":0
            };
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS){
                    var rdata = result.data;
                    var data = rdata.sort(Index.sortBy('priority',false));
                    var Len = data.length;
                    var parent_data = new Array;

                    for(var i=0; i<Len; i++){
                        var mid = data[i].module_id;
                        var pid = data[i].parent_id;

                        if(0 == pid){
                            var children_menu = new Array;
                            for(var j=0; j<Len; j++){
                                if(mid == data[j].parent_id){
                                    var str = data[j];
                                    children_menu.push(str);
                                }
                            }
                            data[i].children = children_menu;
                            parent_data.push(data[i]);
                        }
                    }
                    for(var i in parent_data){
                        $("#pageMenu").tmpl(parent_data[i]).appendTo('#menuContent');
                    }

                } else if(result.Status == 1){
                    alert("服务器开个小差，请稍后重试！");
                } else {
                    //alert("账户名、密码或错误！");
                }
            },function() {
                alert("服务器开个小差，请稍后重试！");
            });
		},
        sortBy: function(attr,rev){
            //第二个参数没有传递 默认升序排列
            if(rev ==  undefined){
                rev = 1;
            }else{
                rev = (rev) ? 1 : -1;
            }
            return function(a,b){
                a = a[attr];
                b = b[attr];
                if(a < b){
                    return rev * -1;
                }
                if(a > b){
                    return rev * 1;
                }
                return 0;
            }
        }
	};
	Index.init();
});

