$(function() {
	var departId = Util.cookieStorage.getCookie("departId");
	var companyId = Util.cookieStorage.getCookie("companyId");
	var roleId = Util.cookieStorage.getCookie("roleId");
	var id = Util.cookieStorage.getCookie("id");

	var url=location.search;
	var Request = new Object();
	if(url.indexOf("?")!=-1) {
		var str = url.substr(1)　//去掉?号
		strs = str.split("&");
		for(var i=0;i<strs.length;i++){
			Request[strs[i ].split("=")[0]]=unescape(strs[ i].split("=")[1]);
		}
	};
	var moduleId = parseInt(Request["moduleId"]);
	var insertAuth = parseInt(Request["insertAuth"]);
	var queryAuth = parseInt(Request["queryAuth"]);
	var updateAuth = parseInt(Request["updateAuth"]);

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
		//获取所有数据
		loadPageData : function() {
			var td_len = $("#table thead tr th").length;//表格字段数量

			var url = ctx + "xiaoyusvr/boss/role/query";
			var data = new Object();
			data.role_id = parseInt(roleId);
			data.company_id = parseInt(companyId);

			Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
				if(result.code == ReturnCode.SUCCESS && result.data != ""){
					$('#pageContent').find("tr").remove();
					$("#pageTmpl").tmpl(result.data).appendTo('#pageContent');
					localStorage.setItem("myUserRolesArray",JSON.stringify(result.data));

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
		//新增用户角色
		add : function() {
			/*var add_depart_id = null;
			 if(1 == parseInt(role_level)){
			 if($('#role-companyId option:selected').val() == ""){
			 add_depart_id = null;
			 }else{
			 add_depart_id = parseInt($('#role-companyId option:selected').val());
			 }
			 }else{
			 add_depart_id = parseInt(depart_id);
			 }*/

			var url = ctx + "xiaoyusvr/boss/role/insert";
			var data = new Object();
			data.role_level = parseInt($("#input-authLevel").val());
			data.role_name = $("#input-name").val();
			data.desc = $("#input-description").val();
			data.company_id = parseInt(companyId);
			//data.depart_id = parseInt(departId);

			Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
				if(result.code == ReturnCode.SUCCESS){
					$("#addTempl2-modal").modal('hide');
					toastr.success("添加成功!");
					action.loadPageData();
				} else {
					alert(result.msg);
				}
			},function() {
				alert("服务器开个小差，请稍后重试！")
			});
		},
		//编辑数据
		edit : function() {
			var url = ctx + "xiaoyusvr/boss/role/update";
			var data = new Object();
			data.id = parseInt($("#input-id").val());
			data.role_level = parseInt($("#input-authLevel").val());
			data.role_name = $("#input-name").val();
			data.desc = $("#input-description").val();
			data.company_id = parseInt(companyId);
			data.depart_id = parseInt(departId);


			Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
				if (result.code == ReturnCode.SUCCESS) {
					$("#addTempl2-modal").modal('hide');
					toastr.success("编辑成功!");
					action.loadPageData();
				}else{
					alert(result.msg);
				}
			});
		}
	};
	window.action = action;
	action.init();
	action.loadPageData();


	$("#addTempl2-modal").on('show.bs.modal', function(e) {
		// 处理modal label显示及表单重置
		var $form = $("form#form-addTempl2");
		if (!e.relatedTarget) {
			$("h4#addTempl2-modal-label").text("编辑用户角色");
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add-userRoles") {
			$("h4#addTempl2-modal-label").text("添加用户角色");
			$form.data("action", "add");
			$form[0].reset();
		}
	});

	//编辑获取数据
	$("#pageContent").on("click",".table-edit-btn",function(){
		var that = $(this).parent().parent();

		$("#input-id").val(that.find("td").eq(0).text());
		$("#input-authLevel").val(that.find("td").eq(1).text());
		$("#input-name").val(that.find("td").eq(2).text());
		$("#input-description").val(that.find("td").eq(3).text());
		$("#role-departId").val(that.find("td").eq(5).text());

		$("#addTempl2-modal").modal("show");
	});

	//验证表单
	/* $("#form-addTempl").validate({
	 rules : {
	 account : {
	 required : true
	 }
	 }
	 });*/
	$("#form-addTempl2").validate({
		rules : {
			authLevel : {
				required : true
			},
			name : {
				required : true
			}
		}
	});

	$("#btn-add-submit2").on('click', function() {
		var action = $("form#form-addTempl2").data("action");
		if(action == "add"){
			if (!$("#form-addTempl2").valid()) {
				return;
			}else if(isNaN($("#input-authLevel").val())) {
				$("#input-authLevel").parent().parent().addClass("has-error");
				var err_html = "<label class='error control-label' style='padding-left: 5px;'>请填入数字</label>";
				$("#input-authLevel").parent().append(err_html);
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