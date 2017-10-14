$(function() {
	var id = Util.cookieStorage.getCookie("id");
	var roleId = Util.cookieStorage.getCookie("roleId");
	var companyId = Util.cookieStorage.getCookie("companyId");
	var departId = Util.cookieStorage.getCookie("departId");

	var url=location.search;
	var Request = new Object();
	if(url.indexOf("?")!=-1) {
		var str = url.substr(1)　//去掉?号
		strs = str.split("&");
		for(var i=0;i<strs.length;i++){
			Request[strs[i ].split("=")[0]]=unescape(strs[ i].split("=")[1]);
		}
	};
	var moduleId = Request["moduleId"];
	var insertAuth = Request["insertAuth"];
	var queryAuth = Request["queryAuth"];
	var updateAuth = Request["updateAuth"];

	var action = {
		init: function(){
			if(0 == insertAuth){
                $("#btn-add").hide();
			 }
			if(0 == queryAuth){

			}
			if(0 == updateAuth){

			}
		},
		//新增数据
		add : function() {
			var add_company_id = null;
			if(1 == parseInt(roleId)){
				add_company_id = parseInt($('#add-companyId option:selected').val());
			}else{
				add_company_id = parseInt(companyId);
			}

            var url = ctx + "xiaoyusvr/boss/admin/insert";
            var data = new Object();
			data.admin_id = parseInt(id);
			data.account = $("#input-account").val();
			data.password = $.md5($("#input-password").val());
			data.nick_name = $("#input-nickName").val();
			data.role_id = parseInt($('#add-search-userRoles option:selected').val());
			data.phone = $("#input-phone").val();
			data.email = $("#input-email").val();
			data.company_id = add_company_id;
			//data.depart_id = add_company_id;

            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if (result.code == ReturnCode.SUCCESS) {
                    $("#addTempl-modal").modal('hide');
                    toastr.success("添加成功!");
                    action.loadPageData();
                }else{
					alert(result.msg);
				}
            });
		},
		//获取所有数据
		loadPageData : function() {
			var search_account = $("#input-search-account").val();
			var search_role_id = parseInt($('#input-search-userRoles option:selected').val());
			var search_company_id = null;
			if(1 == parseInt(roleId)){
				$("#input-search-company_id-wrap").show();
				$("#input-search-company_id-txt-wrap").hide();
				$("#add-companyId-wrap").show();
				$("#role-companyId-wrap").show();
				search_company_id = parseInt($('#input-search-company_id option:selected').val());
			}else{
				$("#input-search-company_id-wrap").hide();
				$("#input-search-company_id-txt-wrap").show();
				$("#add-companyId-wrap").hide();
				$("#role-companyId-wrap").hide();
				search_company_id = parseInt(companyId);
				var my_companyName = companyNameQuery(search_company_id);
				$("#input-search-company_id-txt").val(my_companyName);
			}

            var td_len = $("#table thead tr th").length;//表格字段数量

            var url = ctx + "xiaoyusvr/boss/admin/query";
            var data = new Object();
			data.admin_id = parseInt(id);
			data.account = search_account;
            data.role_id = search_role_id;
			data.company_id = search_company_id;

            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS && result.data != ""){
                    $('#pageContent').find("tr").remove();
                    $("#pageTmpl").tmpl(result.data).appendTo('#pageContent');

                    if($('#pageContent tr').length == 0){
                        $('#pageContent').append("<tr><td  colspan='" + td_len + "' class='t_a_c'>暂无数据</td></tr>");
					}
					if(0 == updateAuth){
						$(".table-update").hide();
						$(".table-manage").hide();
					}
                } else if(result.code == ReturnCode.SUCCESS && result.data.length == 0){
					alert("记录不存在");
                }else {
					alert(result.msg);
				}
            },function(errorMsg) {
				alert(errorMsg);
            });
		},
		//获取当前用户角色列表数据
		loadUserRolesData : function() {
			var url = ctx + "xiaoyusvr/boss/role/query";
			var data = new Object();
			data.role_id = parseInt(roleId);
			data.company_id = parseInt(companyId);
			Util.ajaxLoadData(url,data,0,"POST",true,function(result) {
				if(result.code == ReturnCode.SUCCESS && result.data != ""){
					var myrolesArray = [];
					for(var i=0; i< result.data.length; i++){
						if(result.data[i].id != roleId){
							myrolesArray.push(result.data[i]);
						}
					}
					$("#pageUserRoles").tmpl(myrolesArray).appendTo('#add-search-userRoles');
					$("#pageUserRoles").tmpl(myrolesArray).appendTo('#input-search-userRoles');
				} else {
					alert(result.msg);
				}
			},function(errorMsg) {
				alert(errorMsg);
			});
		},
		//获取全部公司团体数据
		loadCompanyInfoData: function(){
			var allCompanyArray = JSON.parse(localStorage.getItem("allCompanyArray"));
			$("#pageCompanyInfo").tmpl(allCompanyArray).appendTo('#input-search-company_id');
			$("#pageCompanyInfo").tmpl(allCompanyArray).appendTo('#add-companyId');
			$("#pageCompanyInfo").tmpl(allCompanyArray).appendTo('#role-companyId');
		},
		//编辑数据
		edit : function() {
			var pwd ="";
			var pwd2 = $("#modify-password-wrap").val();
			if(pwd2 == null || pwd2 == ""){
				pwd = $("#input-password-wrap").val();
			}else{
				pwd = $.md5(pwd2);
			}

			var url = ctx + "xiaoyusvr/boss/admin/update";
			var data = new Object();
			data.login_id = parseInt(id);
			data.admin_id = parseInt($("#input-id").val());
			data.account = $("#input-account-txt").val();
			data.password = pwd;
			data.nick_name = $("#input-nickName").val();
			data.status = parseInt($("input[name=status]:checked").val());
			data.role_id = parseInt($("#input-roleId").val());
			data.organization_id = parseInt($("#input-companyId").val());
			data.phone = $("#input-phone").val();
			data.email = $("#input-email").val();

			Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
				if (result.code == ReturnCode.SUCCESS) {
			 		$("#addTempl-modal").modal('hide');
                    toastr.success("编辑成功!");
                    action.loadPageData();
					$("#modify-password-wrap").val("");
				}else{
					$("#modify-password-wrap").val("");
					alert(result.msg);
				}
			});
		},
		//删除数据
		deleteConfig : function(deleteid) {
			if (confirm("删除后不可恢复，确定删除" + name + "？")) {
				var url = ctx + "xiaoyusvr/boss/admin/update";
				var data = new Object();
				data.login_id = parseInt(id);
				data.admin_id = parseInt(deleteid);
				data.status = 2;

				Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
					if (result.code == ReturnCode.SUCCESS) {
                        toastr.success("删除成功!");
						/*$("#input-search-client_type").val("");
						$("#input-search-client_name").val("");*/
                        action.loadPageData();
					}else{
						alert(result.msg);
					}
				});
			}
		}
	};
	window.action = action;
	action.init();
	action.loadUserRolesData();
	action.loadCompanyInfoData();
	action.loadPageData();

	$("#addTempl-modal").on('show.bs.modal', function(e) {
		// 处理modal label显示及表单重置
		var $form = $("form#form-addTempl");
		if (!e.relatedTarget) {
			$("h4#addTempl-modal-label").text("编辑管理员");
			$("#input-account-wrap").hide();
			$("#input-account-txt-wrap").show();
			$("#add-companyId-wrap").hide();
			$("#input-password-txt").hide();
			$("#input-password-txt-wrap").hide();
			$("#input-roleId-wrap").hide();
			$("#add-userRoles-wrap").hide();
			$("#input-roleName-txt-wrap").show();
			$("#add-companyId-txt-wrap").show();
			$("#input-companyId-txt").hide();
			$("#modify-password-txt-wrap").show();
			$("#edit-status-wrap").show();
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add") {
			$("h4#addTempl-modal-label").text("添加管理员");
			$("#input-account-wrap").show();
			$("#input-account-txt-wrap").hide();
			$("#input-password-txt").show();
			$("#input-password-txt-wrap").hide();
			$("#input-roleId-wrap").hide();
			$("#add-userRoles-wrap").show();
			$("#input-roleName-txt-wrap").hide();
			$("#add-companyId-txt-wrap").hide();
			$("#input-companyId-txt").hide();
			$("#modify-password-txt-wrap").hide();
			$("#edit-status-wrap").hide();
			$form.data("action", "add");
			$form[0].reset();
		}
	});


    //编辑获取数据
    $("#pageContent").on("click",".table-edit-btn",function(){
        var that = $(this).parent().parent();
		var check_status = $.trim(that.find("td").eq(10).text());
		var status_val = null;
		if(check_status === "可用"){
			status_val = 1;
		}else if(check_status === "不可用"){
			status_val = 0;
		}

        $("#input-id").val(that.find("td").eq(0).text());
        $("#input-account-txt").val(that.find("td").eq(1).text());
        $("#input-password-wrap").val(that.find("td").eq(2).text());
		$("#input-nickName").val(that.find("td").eq(3).text());
		$("#input-roleName-txt").val(that.find("td").eq(4).text());
		$("#input-roleId").val(that.find("td").eq(8).text());
		$("#input-phone").val(that.find("td").eq(5).text());
		$("#input-email").val(that.find("td").eq(6).text());
		$("#add-companyId-txt").val(that.find("td").eq(7).text());
		$("#input-companyId").val(that.find("td").eq(9).text());
		$("input[name=status]").filter("[value=" + status_val + "]").prop('checked', true);
		/*$("#input-status").val(that.find("td").eq(8).text());*/


        $("#addTempl-modal").modal("show");
    });

	//验证表单
    $("#form-addTempl").validate({
        rules : {
			account : {
                required : true
            }
        }
    });


	$("#btn-add-submit").on('click', function() {
		var action = $("form#form-addTempl").data("action");
		if(action == "add"){
			if (!$("#form-addTempl").valid()) {
				return;
			}else if($("#input-password").val() == "") {
				$("#input-password").parent().parent().addClass("has-error");
				var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
				$("#input-password").parent().append(err_html);
				return;
			}else if($("#add-search-userRoles").val() == "") {
				$("#add-search-userRoles").parent().parent().addClass("has-error");
				var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
				$("#add-search-userRoles").parent().append(err_html);
				return;
			}else if($("#add-companyId").val() == "") {
				$("#add-companyId").parent().parent().addClass("has-error");
				var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
				$("#add-companyId").parent().append(err_html);
				return;
			}else {
				window.action.add();
			}
		}else if(action == "edit"){
				window.action.edit();
		}
	});



	$("#input-authLevel").change(function () {
		if(!isNaN($(this).val())) {
			$(this).parent().removeClass("has-error");
			$(this).next().remove();
		}
	});

	$("#btn-search").on('click', function() {
        action.loadPageData();
	});
	$("#input-search-account").on('keydown', function(e) {
        if (e.keyCode == 13) {
            action.loadPageData();
        }

	});
	$("#input-search-role_id").on('keydown', function(e) {
		if (e.keyCode == 13) {
			action.loadPageData();
		}

	});
	$("#input-search-company_id").on('keydown', function(e) {
		if (e.keyCode == 13) {
			action.loadPageData();
		}

	});

});