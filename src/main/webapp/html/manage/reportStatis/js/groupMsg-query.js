$(function() {
	var userName = Util.cookieStorage.getCookie("username");
    var token_value = Util.cookieStorage.getCookie("accesstoken");
	var admin_id = Util.cookieStorage.getCookie("adminId");

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
		//新增数据
		/*add : function() {
            var url = ctx + "boss/admin/insert";
            var data = new Object();
			data.admin_id = parseInt(admin_id);
			data.account = $("#input-account").val();
			data.password = $.md5($("#input-password").val());
			data.nick_name = $("#input-nickName").val();
			data.role_id = parseInt($("#input-roleId").val());
			data.phone = $("#input-phone").val();
			data.email = $("#input-email").val();
			data.organization_id = parseInt($("#input-companyId").val());
            Util.ajaxLoadData(url,data,"POST",true,function(result) {
                if (result.code == ReturnCode.SUCCESS) {
                    $("#addTempl-modal").modal('hide');
                    toastr.success("添加成功!");
                    action.loadPageData();
                }
            });
		},*/
		//获取所有数据
		loadPageData : function() {
            var search_groupName = $("#input-search-groupName").val();
            var td_len = $("#table thead tr th").length;//表格字段数量

            var url = ctx + "boss/groupmessage/query";
            var data = new Object();
			data.admin_id = parseInt(admin_id);
			data.name = search_groupName;


            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS && result.data != ""){
                    $('#pageContent').find("tr").remove();
                    $("#pageTmpl").tmpl(result.data).appendTo('#pageContent');

                    if($('#pageContent tr').length == 0){
                        $('#pageContent').append("<tr><td  colspan='" + td_len + "' class='t_a_c'>暂无数据</td></tr>");
					}
                } else {
					toastr.error(result.msg);
                }
            },function() {
				toastr.error("服务器开个小差，请稍后重试！")
            });

		},
		//编辑数据
		/*edit : function() {
			var url = ctx + "boss/admin/update";
			var data = new Object();
			data.login_id = parseInt(admin_id);
			data.admin_id = parseInt($("#input-id").val());
			data.account = $("#input-account").val();
			data.password = $("#input-password-wrap").val();
			data.nick_name = $("#input-nickName").val();
			data.role_id = parseInt($("#input-roleId").val());
			data.organization_id = parseInt($("#input-companyId").val());
			data.phone = $("#input-phone").val();
			data.email = $("#input-email").val();

			Util.ajaxLoadData(url,data,"POST",true,function(result) {
				if (result.code == ReturnCode.SUCCESS) {
			 		$("#addTempl-modal").modal('hide');
                    toastr.success("编辑成功!");
                    action.loadPageData();
				}
			});
		},*/
		//删除数据
		/*deleteConfig : function(id) {
			if (confirm("删除后不可恢复，确定删除" + name + "？")) {
				var url = ctx + "boss/clienttype/delclienttype";
				var data = new Object();
                data.type_id = id;
				Util.ajaxLoadData(url,data,"POST",true,function(result) {
					if (result.code == ReturnCode.SUCCESS) {
                        toastr.success("删除成功!");
						$("#input-search-client_type").val("");
						$("#input-search-client_name").val("");
                        action.loadPageData();
					}
				});
			}
		}*/
	};
	window.action = action;
	//action.loadPageData();

	$("#addTempl-modal").on('show.bs.modal', function(e) {
		// 处理modal label显示及表单重置
		var $form = $("form#form-addTempl");
		if (!e.relatedTarget) {
			$("h4#addTempl-modal-label").text("编辑管理员");
			$("#input-password-txt").hide();
			$("#input-password-txt-wrap").show();
			//$("#input-companyId-txt").show();
			/*$("#input-status-txt").show();*/
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add") {
			$("h4#addTempl-modal-label").text("添加管理员");
			$("#input-password-txt").show();
			$("#input-password-txt-wrap").hide();
			//$("#input-companyId-txt").hide();
			/*$("#input-status-txt").hide();*/
			$form.data("action", "add");
			$form[0].reset();
		}
	});

    //编辑获取数据
    $("#pageContent").on("click",".table-edit-btn",function(){
        var that = $(this).parent().parent();

        $("#input-id").val(that.find("td").eq(0).text());
        $("#input-account").val(that.find("td").eq(1).text());
        $("#input-password-wrap").val(that.find("td").eq(2).text());
		$("#input-nickName").val(that.find("td").eq(3).text());
		$("#input-roleId").val(that.find("td").eq(4).text());
		$("#input-phone").val(that.find("td").eq(5).text());
		$("#input-email").val(that.find("td").eq(6).text());
		$("#input-companyId").val(that.find("td").eq(7).text());
		/*$("#input-status").val(that.find("td").eq(8).text());*/


        $("#addTempl-modal").modal("show");
    });

	//验证表单
    $("#form-addTempl").validate({
        rules : {
            client_type : {
                required : true
            },
            client_name : {
                required : true
            }
        }
    });

	$("#btn-add-submit").on('click', function() {
		if (!$("#form-addTempl").valid()) {
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