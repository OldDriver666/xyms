$(function() {

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
            var url = ctx + "xiaoyusvr/boss/accountmanage/add";
            var data = new Object();
			data.description = $("#input-description").val();
			data.status = parseInt($("input[name=status]:checked").val());
			data.depart_id = parseInt($('#input-depart_id option:selected').val());
			data.begin_account = $("#input-begin_account").val();
			data.end_account = $("#input-end_account").val();

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
            var search_id = parseInt($('#input-search-name option:selected').val());
            var td_len = $("#table thead tr th").length;//表格字段数量
			if($("#input-search-name").val() == "") {
				$("#input-search-name").parent().addClass("has-error");
				var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
				$("#input-search-name").append(err_html);
				return;
			}

            var url = ctx + "xiaoyusvr/boss/accountmanage/query";
            var data = new Object();
            data.depart_id = search_id;

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
					$('#pageContent').find("tr").remove();
					toastr.info("记录不存在");
                }else {
					$('#pageContent').find("tr").remove();
					toastr.error(result.msg);
				}
            },function(errorMsg) {
				toastr.error(errorMsg)
            });

		},
		//获取全部公司团体数据
		loadCompanyInfoData: function(){
			var allCompanyArray = [];
			var url = ctx + "xiaoyusvr/boss/organization/query";
			var moduleId = 0;
			var data = new Object();
			data.name = "";
			Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
				if(result.code == ReturnCode.SUCCESS && result.data != ""){
					allCompanyArray = result.data;
					$("#pageCompanyInfo").tmpl(allCompanyArray).appendTo('#input-search-name ');
					$("#pageCompanyInfo").tmpl(allCompanyArray).appendTo('#input-depart_id ');
				} else {
				}
			},function() {
			});

			/*var allCompanyArray = JSON.parse(localStorage.getItem("allCompanyArray"));
			$("#pageCompanyInfo").tmpl(allCompanyArray).appendTo('#input-search-name ');
			$("#pageCompanyInfo").tmpl(allCompanyArray).appendTo('#input-depart_id ');*/
		},
		//编辑数据
		edit : function() {
			var url = ctx + "xiaoyusvr/boss/accountmanage/update";
			var data = new Object();
            data.id = parseInt($("#input-id").val());
            data.description = $("#input-description").val();
			data.status = parseInt($("input[name=status]:checked").val());
            data.depart_id = parseInt($('#input-depart_idNo').val());
			data.begin_account = $("#input-begin_account").val();
			data.end_account = $("#input-end_account").val();

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
		deleteConfig : function(id) {
			if (confirm("删除后不可恢复，确定删除" + name + "？")) {
				var url = ctx + "xiaoyusvr/boss/accountmanage/del";
				var data = new Object();
                data.id = id;
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
	action.loadCompanyInfoData();
	//action.loadPageData();

	$("#addTempl-modal").on('show.bs.modal', function(e) {
		// 处理modal label显示及表单重置
		var $form = $("form#form-addTempl");
		if (!e.relatedTarget) {
			$("h4#addTempl-modal-label").text("编辑设备出厂信息");
			$("#input-depart_id-wrap").hide();
			$("#input-depart_id-txt-wrap").show();
			$("#input-depart_idNo-wrap").hide();
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add") {
			$("h4#addTempl-modal-label").text("添加设备出厂信息");
			$("#input-depart_id-wrap").show();
			$("#input-depart_id-txt-wrap").hide();
			$("#input-depart_idNo-wrap").hide();
			$form.data("action", "add");
			$form[0].reset();
		}
	});

    //编辑获取数据
    $("#pageContent").on("click",".table-edit-btn",function(){
        var that = $(this).parent().parent();
		var check_status = $.trim(that.find("td").eq(2).text());
		var status_val = null;
		if(check_status === "登记"){
			status_val = 0;
		}else if(check_status === "出厂"){
			status_val = 1;
		}

        $("#input-id").val(that.find("td").eq(0).text());
        $("#input-description").val(that.find("td").eq(1).text());
		$("input[name=status]").filter("[value=" + status_val + "]").prop('checked', true);
		$("#input-depart_id-txt").val(that.find("td").eq(4).text());
		$("#input-depart_idNo").val(that.find("td").eq(7).text());

        $("#input-begin_account").val(that.find("td").eq(5).text());
		$("#input-end_account").val(that.find("td").eq(6).text());
        $("#addTempl-modal").modal("show");
    });

	$("#input-name").change(function(){
		if($(this).val() != ""){
			$(this).parent().parent().removeClass("has-error");
			$(this).next().remove();
		}
	});
	//验证表单
    $("#form-addTempl").validate({
        rules : {
			depart_id : {
                required : true
            }
        }
    });

	$("#btn-add-submit").on('click', function() {
		var action = $("form#form-addTempl").data("action");
		if(action == "add"){
			if (!$("#form-addTempl").valid()) {
				return;
			}else {
				window.action.add();
			}
		}else if(action == "edit"){
			window.action.edit();
		}
	});

	$("#btn-search").on('click', function() {
        action.loadPageData();
	});

	$("#input-search-name").on('keydown', function(e) {
		if (e.keyCode == 13) {
			action.loadPageData();
		}

	});
});