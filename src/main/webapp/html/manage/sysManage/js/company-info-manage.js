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
            var url = ctx + "xiaoyusvr/boss/organization/insert";
            var data = new Object();

			data.name = $("#input-name").val();
			data.address = $("#input-address").val();
			data.contact = $("#input-contact").val();
			data.email = $("#input-email").val();
			data.describtion = $("#input-describtion").val();

            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if (result.code == ReturnCode.SUCCESS) {
                    $("#addTempl-modal").modal('hide');
                    toastr.success("添加成功!");
                    action.loadPageData();
					action.allCompanyQuery();
                }else{
					alert(result.msg);
				}
            });
		},
		//获取所有数据
		loadPageData : function() {
            var search_name = $("#input-search-name").val();
            var td_len = $("#table thead tr th").length;//表格字段数量

            var url = ctx + "xiaoyusvr/boss/organization/query";
            var data = new Object();
            data.name = search_name;

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
		//获取全部公司团体数据
		allCompanyQuery: function(){
			var allCompanyArray = [];
			var url = ctx + "xiaoyusvr/boss/organization/query";
			var data = new Object();
			data.name = "";
			Util.ajaxLoadData(url,data,0,"POST",true,function(result) {
				if(result.code == ReturnCode.SUCCESS && result.data != ""){
					allCompanyArray = result.data;
					localStorage.setItem("allCompanyArray",JSON.stringify(allCompanyArray));
				} else {
					alert(result.msg);
				}
			},function() {
				alert("服务器开个小差，请稍后重试！")
			});
		},
		//编辑数据
		edit : function() {
			var url = ctx + "xiaoyusvr/boss/organization/update";
			var data = new Object();
            data.id = parseInt($("#input-id").val());
            data.name = $("#input-name").val();
            data.address = $("#input-address").val();
			data.contact = $("#input-contact").val();
			data.email = $("#input-email").val();
			data.describtion = $("#input-describtion").val();
			data.status = parseInt($("input[name=status]:checked").val());

			Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
				if (result.code == ReturnCode.SUCCESS) {
			 		$("#addTempl-modal").modal('hide');
                    toastr.success("编辑成功!");
                    action.loadPageData();
					action.allCompanyQuery();
				}else{
					alert(result.msg);
				}
			});
		},
		//删除数据
		deleteConfig : function(id) {
			if (confirm("删除后不可恢复，确定删除" + name + "？")) {
				var url = ctx + "xiaoyusvr/boss/organization/del";
				var data = new Object();
                data.id = id;
				Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
					if (result.code == ReturnCode.SUCCESS) {
                        toastr.success("删除成功!");
						$("#input-search-name").val("");
                        action.loadPageData();
						action.allCompanyQuery();
					}else{
						alert(result.msg);
					}
				});
			}
		}
	};
	window.action = action;
	action.init();
	action.loadPageData();

	$("#addTempl-modal").on('show.bs.modal', function(e) {
		// 处理modal label显示及表单重置
		var $form = $("form#form-addTempl");
		if (!e.relatedTarget) {
			$("h4#addTempl-modal-label").text("编辑公司团体信息");
			$("#input-status-wrap").show();
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add") {
			$("h4#addTempl-modal-label").text("添加公司团体信息");
			$form.data("action", "add");
			$("#input-status-wrap").hide();
			$form[0].reset();
		}
	});

    //编辑获取数据
    $("#pageContent").on("click",".table-edit-btn",function(){
        var that = $(this).parent().parent();
		var check_status = $.trim(that.find("td").eq(6).text());
		var status_val = null;
		if(check_status === "删除"){
			status_val = 0;
		}else if(check_status === "正常"){
			status_val = 1;
		}

        $("#input-id").val(that.find("td").eq(0).text());
        $("#input-name").val(that.find("td").eq(1).text());
        $("#input-address").val(that.find("td").eq(2).text());
		$("#input-contact").val(that.find("td").eq(3).text());
		$("#input-email").val(that.find("td").eq(4).text());
		$("#input-describtion").val(that.find("td").eq(5).text());
		$("input[name=status]").filter("[value=" + status_val + "]").prop('checked', true);
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
			name : {
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
			if (!$("#form-addTempl").valid()) {
				return;
			}else {
			    window.action.edit();
			}
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