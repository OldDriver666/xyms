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
            var url = ctx + "xiaoyusvr/boss/module/insert";
			var data = new Object();
			data.name = $("#input-moduleName").val();
			data.company_id = parseInt(companyId);
			data.description = $("#input-description").val();
			data.priority = parseInt($("#input-priority").val());
			data.sn = $("#input-sn").val();
			data.url = $("#input-url").val();
			//data.module_type = $("#input-url").val();
			data.parent_id = parseInt($("#input-parent_id").val());
			data.status = parseInt($("input[name=status]:checked").val());


            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if (result.code == ReturnCode.SUCCESS) {
                    $("#addTempl-modal").modal('hide');
                    toastr.success("添加成功!");
                    action.loadPageData();
                }else{
					toastr.error(result.msg);
				}
            });
		},
		//获取所有数据
		loadPageData : function() {
            var td_len = $("#table thead tr th").length;//表格字段数量
			var url = ctx + "xiaoyusvr/boss/module/query";
			var data = {
				"company_id":parseInt(companyId)
			};
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
                } else {
					toastr.error(result.msg);
                }
            },function(errorMsg) {
				toastr.error(errorMsg)
            });
		},
		//编辑数据
		edit : function() {
			var url = ctx + "xiaoyusvr/boss/module/update";
			var data = new Object();
            data.admin_id = parseInt(id),
			data.module_id = parseInt($("#input-moduleId").val());
			data.company_id = parseInt(companyId);
			data.name = $("#input-moduleName").val();
            data.description = $("#input-description").val();
			data.priority = parseInt($("#input-priority").val());
			data.sn = $("#input-sn").val();
			data.status = parseInt($("input[name=status]:checked").val());
			data.url = $("#input-url").val();
			data.parent_id = parseInt($("#input-parent_id").val());

			Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
				if (result.code == ReturnCode.SUCCESS) {
			 		$("#addTempl-modal").modal('hide');
                    toastr.success("编辑成功!");
                    action.loadPageData();
				}else{
					toastr.error(result.msg);
				}
			});
		},
		//删除数据
		deleteItem : function(id) {
			if (confirm("删除后不可恢复，确定删除" + name + "？")) {
				var url = ctx + "xiaoyusvr/boss/module/delete";
				var data = new Object();
                data.module_id = id;
				Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
					if (result.code == ReturnCode.SUCCESS) {
                        toastr.success("删除成功!");
                        action.loadPageData();
					}else{
						toastr.error(result.msg);
					}
				});
			}
		}
	};
	window.action = action;
	action.init();
	action.loadPageData();


    //编辑获取数据数据
    $("#pageContent").on("click",".table-edit-btn",function(){
        var that = $(this).parent().parent();
		var check_status = $.trim(that.find(".td-status").text());
		var status_val = null;
		if(check_status === "可见"){
			status_val = 1;
		}else if(check_status === "不可见"){
			status_val = 0;
		}
        $("#input-moduleId").val(that.find(".td-moduleId").text());
        $("#input-priority").val(that.find(".td-priority").text());
        $("#input-moduleName").val(that.find(".td-moduleName").text());
        $("#input-url").val(that.find(".td-url").text());
		$("#input-sn").val(that.find(".td-sn").text());
		$("#input-parent_id").val(that.find(".td-parent_id").text());
		$("#input-description").val(that.find(".td-description").text());
		$("input[name=status]").filter("[value=" + status_val + "]").prop('checked', true);
        $("#addTempl-modal").modal("show");
    });

	$("#addTempl-modal").on('show.bs.modal', function(e) {
		// 处理modal label显示及表单重置
		var $form = $("form#form-addTempl");
		if (!e.relatedTarget) {
			$("h4#addTempl-modal-label").text("编辑菜单信息");
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add") {
			$("h4#addTempl-modal-label").text("添加菜单信息");
			$form.data("action", "add");
			$form[0].reset();
		}
	});

	//验证表单
    $("#form-addTempl").validate({
        rules : {
			moduleName : {
                required : true
            },
			priority : {
				required : true
			},
			sn : {
				required : true
			},
			status : {
				required : true
			},
			parent_id : {
				required : true
			}
        }
    });

	$("#btn-add-submit").on('click', function() {
		if (!$("#form-addTempl").valid()) {
			return;
		}
		if(isNaN($("#input-priority").val())) {
			$("#input-priority").parent().parent().addClass("has-error");
			var err_html = "<label class='error control-label' style='padding-left: 5px;'>请填入数字</label>";
			$("#input-priority").parent().append(err_html);
			return;
		}
		if(isNaN($("#input-parent_id").val())) {
			$("#input-parent_id").parent().parent().addClass("has-error");
			var err_html = "<label class='error control-label' style='padding-left: 5px;'>请填入数字</label>";
			$("#input-parent_id").parent().append(err_html);
			return;
		}
		var action = $("form#form-addTempl").data("action");
		switch (action) {
		case "add":
			window.action.add();
			break;
		case "edit":
			window.action.edit();
			break;
		}
	});

	$("#input-priority").change(function () {
		if(!isNaN($(this).val())) {
			$(this).parent().removeClass("has-error");
			$(this).next().remove();
		}
	});

	$("#input-parent_id").change(function () {
		if(!isNaN($(this).val())) {
			$(this).parent().removeClass("has-error");
			$(this).next().remove();
		}
	});

	$("#btn-search").on('click', function() {
        action.loadPageData();
	});
	$("#input-search-txt").on('keydown', function(e) {
        if (e.keyCode == 13) {
            action.loadPageData();
        }

	});
});