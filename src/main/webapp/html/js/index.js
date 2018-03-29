$(function(){
    var departId = Util.cookieStorage.getCookie("departId");
    var companyId = Util.cookieStorage.getCookie("companyId");
    var roleId = Util.cookieStorage.getCookie("roleId");
	var id = Util.cookieStorage.getCookie("id");
    var nickName = Util.cookieStorage.getCookie("nickName");

    //点击一级菜单
    $('#menuContent').on('click', '.tpl-left-nav-link-list', function () {
        $(this).next().toggle(200);
        $(this).parent().siblings().find('.tpl-left-nav-sub-menu').slideUp(200);
        var $this = $(this);
        var url = $this.data('url');
        if (url !== "" && url !== "data-insert=1") {
            $("#menuName").html('');
            var menuid = $this.data('menuid');
            var insertRight = $this.data('insert');
            var queryRight = $this.data('query');
            var updateRight = $this.data('update');
            var moduleName = $this.data('name');
            var nameArray = [{name: moduleName}]
            url += "?insertAuth=" +(insertRight) +"&queryAuth=" +(queryRight) +"&updateAuth=" +(updateRight)+"&moduleId=" +(menuid);
            $('#iframePage').attr('src', url);
            $("#showMenuName").tmpl(nameArray).appendTo('#menuName');
        }
    });

    //点击二级菜单
    $('.tpl-left-nav').on('click', '.childrenMenu', function () {
        $("#menuName").html('');
        var $this = $(this);
        var url = $this.data('url');
        var menuid = $this.data('menuid');
        var insertRight = $this.data('insert');
        var queryRight = $this.data('query');
        var updateRight = $this.data('update');
        var moduleName = $this.data('name');
        var nameArray = [{name: moduleName}]
        url += "?insertAuth=" +(insertRight) +"&queryAuth=" +(queryRight) +"&updateAuth=" +(updateRight)+"&moduleId=" +(menuid);
        if (url !== undefined) {
            $('#iframePage').attr('src', url);
            $("#showMenuName").tmpl(nameArray).appendTo('#menuName');
        }
        $('#menuDowns').html('');
    });

    //模糊搜索菜单栏功能
    $("#menuSearch").bind('click',function(){
        if($("#menuInput").val() == ''){
            return
        }
        $('#menuDowns').html("");
        var url = ctx + "xiaoyusvr/boss/role/queryAuthByName";
        var data = {
            "role_id":parseInt(roleId),
            "company_id":parseInt(companyId),
            "name": $("#menuInput").val()
        };
        var moduleId= 0;
        Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
            if(result.code == ReturnCode.SUCCESS){
                $("#searchMenu").tmpl(result.data).appendTo('#menuDowns');
            }  else {
                toastr.error(result.msg);
            }
        },function() {
            toastr.error("服务器开个小差，请稍后重试！");
        });
    });

    //个人信息
    $(".personal-Info").on('click', function(){
        var $this = $(this);
        var url = $this.data('url');
        $("#menuName").html('');
        var moduleName = $this.data('name');
        var nameArray = [{name: moduleName}]
        $('#iframePage').attr('src', url);
        $("#showMenuName").tmpl(nameArray).appendTo('#menuName');
        /*$('#showPerInfo').toggle();*/
    });

    //安全退出
    $(".header-safeExit").on('click', function(){
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
                Util.cookieStorage.clearCookie("creatorId");
                Util.cookieStorage.clearCookie("userType");
                localStorage.clear();
                window.location.href = "login.html";
            }  else {
                toastr.error("账户名、密码或错误！");
            }
        },function() {
            toastr.error("服务器开个小差，请稍后重试！");
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

                }  else {
                    toastr.error(result.msg);
                }
            },function() {
                toastr.error("服务器开个小差，请稍后重试！");
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

