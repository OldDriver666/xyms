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
		//新增用户角色
		add : function() {
			var url = ctx + "xiaoyusvr/boss/devicecontrol/add";
			var data = new Object();
			data.device_id = parseInt($("#input-device_id").val());
			data.auth_type = parseInt($('#input-auth_type option:selected').val());
			data.mobile = $("#input-mobile").val();
			data.status = parseInt($("input[name=status]:checked").val());
			data.name = $("#input-name").val()

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
		//获取所有数据
		loadPageData : function() {
			var search_device_id = parseInt($("#input-search-device_id").val());
			var search_mobile = $("#input-search-mobile").val();
			var td_len = $("#table thead tr th").length;//表格字段数量

			var url = ctx + "xiaoyusvr/boss/devicecontrol/query";
			var data = new Object();
			data.device_id =  search_device_id;
			data.mobile =  search_mobile;

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
		//编辑数据
		edit : function() {
			var url = ctx + "xiaoyusvr/boss/devicecontrol/update";
			var data = new Object();
			data.id = parseInt($("#input-id").val());
			data.device_id = parseInt($("#input-device_id").val());
			data.auth_type = parseInt($('#input-auth_type option:selected').val());
			data.mobile = $("#input-mobile").val();
			data.status = parseInt($("input[name=status]:checked").val());
			data.name = $("#input-name").val()


			Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
				if (result.code == ReturnCode.SUCCESS) {
					$("#addTempl2-modal").modal('hide');
					toastr.success("编辑成功!");
					action.loadPageData();
				}else{
					alert(result.msg);
				}
			});
		},
		//删除数据
		deleteConfig : function(id) {
			if (confirm("删除后不可恢复，确定删除" + name + "？")) {
				var url = ctx + "xiaoyusvr/boss/devicecontrol/del";
				var data = new Object();
				data.id = parseInt(id);

				Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
					if (result.code == ReturnCode.SUCCESS) {
						toastr.success("删除成功!");
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


	$("#addTempl2-modal").on('show.bs.modal', function(e) {
		// 处理modal label显示及表单重置
		var $form = $("form#form-addTempl2");
		if (!e.relatedTarget) {
			$("h4#addTempl2-modal-label").text("编辑通讯号码");
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add-userRoles") {
			$("h4#addTempl2-modal-label").text("添加通讯号码");
			$form.data("action", "add");
			$form[0].reset();
		}
	});

	//编辑获取数据
	$("#pageContent").on("click",".table-edit-btn",function(){
		var that = $(this).parent().parent();
		var check_status = $.trim(that.find("td").eq(4).text());
		var status_val = null;
		if(check_status === "不可用"){
			status_val = 0;
		}else if(check_status === "可用"){
			status_val = 1;
		}

		var check_type = $.trim(that.find("td").eq(2).text());
		var type_val = null;
		if(check_type === "管理员"){
			type_val = 0;
		}else if(check_type === "亲情"){
			type_val = 1;
		}else if(check_type === "白名单"){
			type_val = 2;
		}else if(check_type === "紧急号码"){
			type_val = 3;
		}

		$("#input-id").val(that.find("td").eq(0).text());
		$("#input-device_id").val(that.find("td").eq(1).text());
		$("#input-auth_type option[value='" + type_val + "']").attr("selected", "selected");
		$("#input-mobile").val(that.find("td").eq(3).text());
		$("input[name=status]").filter("[value=" + status_val + "]").prop('checked', true);
		$("#input-name").val(that.find("td").eq(5).text());
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
			device_id : {
				required : true
			},
			auth_type : {
				required : true
			},
			mobile : {
				required : true
			}
		}
	});

	$("#btn-add-submit2").on('click', function() {
		var action = $("form#form-addTempl2").data("action");
		if(action == "add"){
			if (!$("#form-addTempl2").valid()) {
				return;
			}else if($("#input-device_id").val() == "") {
				$("#input-device_id").parent().parent().addClass("has-error");
				var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
				$("#input-device_id").parent().append(err_html);
				return;
			}else if($("#input-auth_type").val() == "") {
				$("#input-auth_type").parent().parent().addClass("has-error");
				var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
				$("#input-auth_type").parent().append(err_html);
				return;
			}else if($("#input-mobile").val() == "") {
				$("#input-mobile").parent().parent().addClass("has-error");
				var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
				$("#input-mobile").parent().append(err_html);
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
		if(($("#input-search-device_id").val() == "") ||( $.trim($("#input-search-device_id").val()) == "")) {
			$("#input-search-device_id").parent().addClass("has-error");
			var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
			$("#input-search-device_id").append(err_html);
			return;
		}
		action.loadPageData();
	});

	$("#input-search-device_id").change(function () {
		if (($("#input-search-device_id").val() != "") &&( $.trim($("#input-search-device_id").val()) != "")){
			$(this).parent().removeClass("has-error");
			$(this).next().remove();
		}
	});
	$("#input-search-device_id").on('keydown', function(e) {
		if(($("#input-search-device_id").val() == "") ||( $.trim($("#input-search-device_id").val()) == "")) {
			$("#input-search-device_id").parent().addClass("has-error");
			var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
			$("#input-search-device_id").append(err_html);
			return;
		}
		if (e.keyCode == 13) {
			action.loadPageData();
		}
	});
	$("#input-search-mobile").on('keydown', function(e) {
		if (e.keyCode == 13) {
			action.loadPageData();
		}
	});

});