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
            var url = ctx + "xiaoyusvr/boss/clienttype/addclienttype";
            var data = new Object();

            data.client_type = $("#input-client_type").val();
            data.client_name = $("#input-client_name").val();

            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if (result.code == ReturnCode.SUCCESS) {
                    $("#addTempl-modal").modal('hide');
                    toastr.success("添加成功!");
                    action.loadPageData();
					action.allDevTypeQuery();
                }else{
					alert(result.msg);
				}
            });
		},
		//获取所有数据
		loadPageData : function() {
            var search_client_type = $("#input-search-client_type").val();
            var search_client_name = $("#input-search-client_name").val();
            var td_len = $("#table thead tr th").length;//表格字段数量

            var url = ctx + "xiaoyusvr/boss/clienttype/queryclienttype";
            var data = new Object();
            data.client_type = parseInt(search_client_type);
            data.client_name = search_client_name;

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
					alert(result.msg);
                }
            },function(errorMsg) {
                alert(errorMsg)
            });

		},
		//获取所有设备类型数据
		allDevTypeQuery: function(){
			var allDevTypeArray = [];
			var url = ctx + "xiaoyusvr/boss/clienttype/queryclienttype";
			var data = new Object();
			data.client_type = null;
			data.client_name = "";
			Util.ajaxLoadData(url,data,0,"POST",true,function(result) {
				if(result.code == ReturnCode.SUCCESS && result.data != ""){
					allDevTypeArray = result.data;
					localStorage.setItem("allDevTypeArray",JSON.stringify(allDevTypeArray));
				} else {
					alert(result.msg);
				}
			},function() {
				alert("服务器开个小差，请稍后重试！")
			});

		},
		//编辑数据
		edit : function() {
			var url = ctx + "xiaoyusvr/boss/clienttype/updateclienttype";
			var data = new Object();
            data.type_id = parseInt($("#input-type_id").val());
            data.client_type = $("#input-client_type").val();
            data.client_name = $("#input-client_name").val();

			Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
				if (result.code == ReturnCode.SUCCESS) {
			 		$("#addTempl-modal").modal('hide');
                    toastr.success("编辑成功!");
                    action.loadPageData();
					action.allDevTypeQuery();
				}else{
					alert(result.msg);
				}
			});
		},
		//删除数据
		deleteConfig : function(id) {
			if (confirm("删除后不可恢复，确定删除" + name + "？")) {
				var url = ctx + "xiaoyusvr/boss/clienttype/delclienttype";
				var data = new Object();
                data.type_id = id;
				Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
					if (result.code == ReturnCode.SUCCESS) {
                        toastr.success("删除成功!");
						$("#input-search-client_type").val("");
						$("#input-search-client_name").val("");
                        action.loadPageData();
						action.allDevTypeQuery();
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
			$("h4#addTempl-modal-label").text("编辑设备类型");
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add") {
			$("h4#addTempl-modal-label").text("添加设备类型");
			$form.data("action", "add");
			$form[0].reset();
		}
	});

    //编辑获取数据
    $("#pageContent").on("click",".table-edit-btn",function(){
        var that = $(this).parent().parent();

        $("#input-type_id").val(that.find("td").eq(0).text());
        $("#input-client_type").val(that.find("td").eq(1).text());
        $("#input-client_name").val(that.find("td").eq(2).text());

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
		if(isNaN($("#input-client_type").val())) {
			$("#input-client_type").parent().parent().addClass("has-error");
			var err_html = "<label class='error control-label' style='padding-left: 5px;'>请填入数字</label>";
			$("#input-client_type").parent().append(err_html);
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

	$("#input-client_type").change(function () {
		if(!isNaN($(this).val())) {
			$(this).parent().removeClass("has-error");
			$(this).next().remove();
		}
	});

	$("#input-search-client_type").change(function () {
		if(!isNaN($(this).val())) {
			$(this).parent().removeClass("has-error");
		}
	});

	$("#btn-search").on('click', function() {
		if(isNaN($("#input-search-client_type").val())) {
			$("#input-search-client_type").parent().addClass("has-error");
			return;
		}
        action.loadPageData();
	});
	$("#input-search-client_type").on('keydown', function(e) {
		if(isNaN($("#input-search-client_type").val())) {
			$("#input-search-client_type").parent().addClass("has-error");
			return;
		}
        if (e.keyCode == 13) {
            action.loadPageData();
        }

	});
	$("#input-search-client_name").on('keydown', function(e) {
		if (e.keyCode == 13) {
			action.loadPageData();
		}

	});
});