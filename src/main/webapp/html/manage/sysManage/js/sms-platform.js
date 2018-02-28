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
            var url = ctx + "xiaoyusvr/boss/smsplatfrom/add";
			var data = new Object();
			data.platfrom_name = $("#input-platfrom_name").val();
			data.status = parseInt($("input[name=status]:checked").val());
			data.config = $("#input-config").val();

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
			var search_uname = $("#input-search-uname").val();
            var td_len = $("#table thead tr th").length;//表格字段数量
			var url = ctx + "xiaoyusvr/boss/smsplatfrom/query";
			var data = new Object();
			if(search_uname == ""){
				data.platfrom_name = "";
			}else{
				data.platfrom_name = search_uname;
			}

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
				} else {
					toastr.error(result.msg);
                }
            },function(errorMsg) {
				toastr.error(errorMsg)
            });

		},
		//编辑数据
		edit : function() {
			var url = ctx + "xiaoyusvr/boss/smsplatfrom/update";
			var data = new Object();
			data.smsplatfrom_id = parseInt($("#input-smsplatfrom_id").val());
			data.platfrom_name = $("#input-platfrom_name-txt").val();
			data.status = parseInt($("input[name=status]:checked").val());
			data.config = $("#input-config").val();

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
				var url = ctx + "xiaoyusvr/boss/smsplatfrom/del";
				var data = new Object();
                data.smsplatfrom_id = id;
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
		var check_status = $.trim(that.find("td").eq(3).text());
		var status_val = null;
		if(check_status === "使用"){
			status_val = 1;
		}else if(check_status === "弃用"){
			status_val = 0;
		}
        $("#input-smsplatfrom_id").val(that.find("td").eq(0).text());
        $("#input-platfrom_name-txt").val(that.find("td").eq(1).text());
        $("#input-config").val(that.find("td").eq(2).text());
		$("input[name=status]").filter("[value=" + status_val + "]").prop('checked', true);
        $("#addTempl-modal").modal("show");
    });

	$("#addTempl-modal").on('show.bs.modal', function(e) {
		// 处理modal label显示及表单重置
		var $form = $("form#form-addTempl");
		if (!e.relatedTarget) {
			$("h4#addTempl-modal-label").text("编辑短信平台");
			$("#input-platfrom_name-wrap").hide();
			$("#input-platfrom_name-txt-wrap").show();
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add") {
			$("h4#addTempl-modal-label").text("添加短信平台");
			$("#input-platfrom_name-wrap").show();
			$("#input-platfrom_name-txt-wrap").hide();
			$form.data("action", "add");
			$form[0].reset();
		}
	});

	//验证表单
    $("#form-addTempl").validate({
        rules : {
			platfrom_name : {
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
	$("#input-search-uname").on('keydown', function(e) {
        if (e.keyCode == 13) {
            action.loadPageData();
        }

	});
});