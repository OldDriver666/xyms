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
            var url = ctx + "xiaoyusvr/boss/suggest/add";
            var data = new Object();
			data.user_id = parseInt($("#input-user_id").val());
			data.uname = $("#input-uname").val();
			data.suggestion = $("#input-suggestion").val();
			data.contact = $("#input-contact").val();

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
            var page_content_num = parseInt($("#input-page-content-num").val());

            var url = ctx + "xiaoyusvr/boss/suggest/query";
			if(search_uname == ""){
				var uname = null;
			}else{
				var uname = search_uname;
			}

			var data = new Object();
			data.page_no = 1;
			data.page_size = page_content_num;
			data.param = {
				"uname":uname
			};

			var opt = {
				"targetContentId" : "pageContent",
				"url" : url,
				"moduleId" : moduleId,
				"forAuth2" : true,
				"updateAuth" : updateAuth,
				"rowTemplateId" : "pageTmpl",
				"contextUrl" : ctx,
				"pageBtnsContentId" : "pagination",
				"tmplEvents" : {
					setTime : function(time) {
						if (time) {
							var times = new Date(time);
							time = times.format('yyyy-MM-dd hh:mm:ss');
						}
						return time;
					}
				},
				"resultFilter" : function(result) {
					return result.data.result;
				},
				"param" : data
			};
			this.page = new Util.Page(opt);

		},
		//编辑数据
		edit : function() {
			var url = ctx + "xiaoyusvr/boss/suggest/update";
			var data = new Object();
			data.suggest_id = parseInt($("#input-suggest_id").val());
			data.user_id = parseInt($("#input-user_id").val());
			data.uname = $("#input-uname").val();
			data.status = parseInt($("input[name=status]:checked").val());
			data.suggestion = $("#input-suggestion-txt").val();
			data.contact = $("#input-contact").val();

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
				var url = ctx + "xiaoyusvr/boss/suggest/del";
				var data = new Object();
                data.suggest_id = id;
				Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
					if (result.code == ReturnCode.SUCCESS) {
                        toastr.success("删除成功!");
						$("#input-search-uname").val("");
                        action.loadPageData();
					}else{
						toastr.error(result.msg);
					}
				});
			}
		}
	};
	window.action = action;
	action.loadPageData();

	$("#addTempl-modal").on('show.bs.modal', function(e) {
		// 处理modal label显示及表单重置
		var $form = $("form#form-addTempl");
		if (!e.relatedTarget) {
			$("h4#addTempl-modal-label").text("编辑用户意见");
			$("#input-status-txt-wrap").show();
			$("#input-suggestion-wrap").hide();
			$("#input-suggestion-txt-wrap").show();
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add") {
			$("h4#addTempl-modal-label").text("添加用户意见");
			$("#input-status-txt-wrap").hide();
			$("#input-suggestion-wrap").show();
			$("#input-suggestion-txt-wrap").hide();
			$form.data("action", "add");
			$form[0].reset();
		}
	});

    //编辑获取数据
    $("#pageContent").on("click",".table-edit-btn",function(){
        var that = $(this).parent().parent();
		var check_status = $.trim(that.find("td").eq(3).text());
		var status_val = null;
		if(check_status === "已经回复"){
			status_val = 1;
		}else if(check_status === "初始正常"){
			status_val = 0;
		}

		$("#input-suggest_id").val(that.find("td").eq(0).text());
        $("#input-user_id").val(that.find("td").eq(1).text());
        $("#input-uname").val(that.find("td").eq(2).text());
		$("input[name=status]").filter("[value=" + status_val + "]").prop('checked', true);
		$("#input-suggestion-txt").val(that.find("td").eq(4).text());
		$("#input-contact").val(that.find("td").eq(5).text());

        $("#addTempl-modal").modal("show");
    });

	//验证表单
    $("#form-addTempl").validate({
        rules : {
			userid : {
                required : true
            },
			uname : {
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
	$("#input-search-account").on('keydown', function(e) {
        if (e.keyCode == 13) {
            action.loadPageData();
        }

	});
    $("#input-page-content-num").change(function() {
        action.loadPageData();
    });
	$("#input-search-uname").on('keydown', function(e) {
		if (e.keyCode == 13) {
			action.loadPageData();
		}

	});

});



//分页
Util.Page = (function() {
	var Page = function(options) {
		var opt = options ? options : {};
		this.targetContentId = opt.targetContentId ? opt.targetContentId : "";// 显示查询到结果的区域
		this.url = opt.url ? opt.url : "";
		this.ctx = opt.ctx ? opt.ctx : "";
		this.pageBtnsContentId = opt.pageBtnsContentId ? opt.pageBtnsContentId
			: "";// 显示分页按钮的区域
		this.rowTemplateId = opt.rowTemplateId ? opt.rowTemplateId : "";// 每一行的模板
		this.tmplBindEvents = opt.tmplBindEvents ? opt.tmplBindEvents : {};
		this.tmplEvents = opt.tmplEvents;
		this.allNumContentId = opt.allNumContentId ? opt.allNumContentId : "";// 显示所有条数的区域
		this.resultFilter = opt.resultFilter ? opt.resultFilter : null;// 过滤要遍历的数据
		this.callback = opt.callback ? opt.callback : null;// 成功加载所有html后的回调
		this.errorCallback = opt.errorCallback ? opt.errorCallback : null;// 失败加载所有html后的回调
		this.allNumberAreaId = opt.allNumberAreaId ? opt.allNumberAreaId : "";// 每一行的模板
		this.Page = 1;
		this.PageSize = 20;
		this.allPageSize = 0;
		this.showPageNum = 7;// 显示多少个页面数
		this.param = options.param;
		this.filterParam = null;
		this.forAuth2 = typeof (opt.forAuth2) != "undefined" ? opt.forAuth2
			: false;
		this.updateAuth = typeof (opt.updateAuth) != "undefined" ? opt.updateAuth
			: false;
		this.moduleId = typeof (opt.moduleId) != "undefined" ? opt.moduleId
			: false;
		this.loadTBodyData(this.param);
		// this.initPageBtns();
	};
	Page.prototype.setCurrentPage = function() {
		$(_this).siblings(".page_a").removeClass("current");
		$(_this).addClass("current");
		var pageNow = $(_this).text();
		var pageNowInt = parseInt(pageNow);
		if (isNaN(pageNowInt)) {
			return;
		}
		if (pageNowInt == ManagerSet.productManager.pageNow) {// 如果点击的页面跟当前页相等，则返回
			return;
		}
		ManagerSet.productManager.pageNow = pageNowInt;
		ManagerSet.productManager.loadTBodyData();
	};
	Page.prototype.refreshData = function(data) {
		this.pageNow = 1;
		this.loadTBodyData(data);
	};

	Page.prototype.refreshCurrentPageData = function(data) {
		this.loadTBodyData(data);
	};

	Page.prototype.initPageBtns = function(totalCount,allPageSize) {
		var that = this;
		var htmlStr = '';
		var hasDots = false;
		htmlStr = '<span>共'+allPageSize+'页，'+totalCount+'条数据</span>';
		htmlStr += '<a href="javascript:void(0)" title="上一页" class="page_a jsBtnA prev_page">上一页</a><span id="pageBtns">';
		for (var i = 0; i < allPageSize; i++) {
			var pageNow = i + 1;
			var pe = allPageSize - that.pageNow;
			if (that.pageNow >= 5) {
				if (i < 2) {
					cBtnStr = '<a href="javascript:void(0)" class="page_a current_page jsBtnA " title="第'
						+ pageNow + '页">' + pageNow + '</a>';
					htmlStr = htmlStr + cBtnStr;
				}
			}
			if (pe > 3) {
				if (i == that.pageNow - 2 && i > 0) {
					htmlStr = htmlStr + "<span>...</span>";
				}
				if (i >= (that.pageNow - 2) && i <= (that.pageNow + 2)) {
					if (pageNow == that.pageNow) {
						cBtnStr = '<a href="javascript:void(0)" class="page_a current_page jsBtnA current" title="第'
							+ pageNow + '页">' + pageNow + '</a>';
					} else {
						cBtnStr = '<a href="javascript:void(0)" class="page_a current_page jsBtnA " title="第'
							+ pageNow + '页">' + pageNow + '</a>';
					}
					htmlStr = htmlStr + cBtnStr;
				} else {
					if (i == (that.pageNow + 2)) {
						htmlStr = htmlStr + "<span>...</span>";
					}
				}
			} else {
				if (i == (that.pageNow - 2) && i > 0) {
					htmlStr = htmlStr + "<span>...</span>";
				}
				if (i > (that.pageNow - 3)) {
					if (pageNow == that.pageNow) {
						cBtnStr = '<a href="javascript:void(0)" class="page_a current_page jsBtnA current" title="第'
							+ pageNow + '页">' + pageNow + '</a>';
					} else {
						cBtnStr = '<a href="javascript:void(0)" class="page_a current_page jsBtnA " title="第'
							+ pageNow + '页">' + pageNow + '</a>';
					}
					htmlStr = htmlStr + cBtnStr;
				}
			}

		}
		htmlStr += '</span><a href="javascript:void(0)" title="下一页" class="page_a jsBtnA next_page"">下一页</a>';
		htmlStr += '<input type="text"  style="width:30px;"/>';
		htmlStr += '<a href="javascript:void(0)" class="page_a jsBtnA page_a_go" style="margin-left:5px;">Go</a>';
		$("#" + this.pageBtnsContentId).html(htmlStr);

		if (allPageSize == 0) {
			$("#" + this.pageBtnsContentId).hide();
		} else {
			$("#" + this.pageBtnsContentId).show();
		}
		$("#" + this.pageBtnsContentId).find(".current_page").bind("click",
			function() {// 点击某一页
				$(this).siblings(".page_a").removeClass("current");
				$(this).addClass("current");
				var pageNow = $(this).text();
				var pageNowInt = parseInt(pageNow);
				if (isNaN(pageNowInt)) {
					return;
				}
				if (pageNowInt == that.pageNow) {// 如果点击的页面跟当前页相等，则返回
					return;
				}
				that.pageNow = pageNowInt;
				that.loadTBodyData();
			});

		$("#" + this.pageBtnsContentId).find(".prev_page").bind(
			"click",
			function() {// 上一页
				if (1 == that.pageNow) {
					return;
				}
				that.pageNow = that.pageNow - 1;
				that.loadTBodyData();
				$("#pageBtns .page_a").removeClass("current");
				$("#pageBtns .page_a").eq(that.pageNow - 1).addClass(
					"current");
			});

		$("#" + this.pageBtnsContentId).find(".next_page").bind(
			"click",
			function() {// 下一页
				if (that.allPageSize == that.pageNow) {
					return;
				}
				that.pageNow = that.pageNow + 1;
				that.loadTBodyData();
				$("#pageBtns .page_a").removeClass("current");
				$("#pageBtns .page_a").eq(that.pageNow - 1).addClass(
					"current");
			});

		$("#" + this.pageBtnsContentId).find(".page_a_go").bind(
			"click",
			function() {// 下一页
				var pageNow = $(this).prev().val();
				var pageNowInt = parseInt(pageNow);
				if (isNaN(pageNowInt)) {
					Util.showTit("请输入数字", "no");
					return;
				}

				if (pageNowInt <= 0 || pageNowInt > that.allPageSize) {
					Util.showTit("输入数字必须在 0 与  " + that.allPageSize
						+ " 之间", "no");
					return;
				}

				if (pageNowInt == that.pageNow) {// 如果点击的页面跟当前页相等，则返回
					return;
				}
				that.pageNow = pageNowInt;
				that.loadTBodyData();
				$("#pageBtns .page_a").removeClass("current");
				$("#pageBtns .page_a").eq(that.pageNow - 1).addClass(
					"current");
			});
	};

	Page.prototype.loadTBodyData = function(data) {

		var target = null;
		if (typeof (this.targetContentId) == "string") {
			target = $("#" + this.targetContentId);
		} else {
			target = this.targetContentId;
		}
		var length = target.prev().find("th").length
			|| target.prev().find("td").length || 7;
		var loadDiv = $('<tr><td colspan="' + length
			+ '"><div class="loading"></div></td></tr>');
		target.css({
			"postion" : "relative"
		});
		target.html(loadDiv);
		var contentType = null;
		if (data) {
			this.filterParam = data;
			/*this.pageSize = 20;
			 this.pageNow = 1;*/
			this.pageSize = data.page_size;
			this.pageNow = data.page_no;
		}
		var sendData = {
			"page_size" : this.pageSize,
			"page_no" : this.pageNow
		};
		if (this.forAuth2 == true) {
			if(data){
				sendData = data;
			}else{
				sendData = $.extend({},this.param,sendData);
			}
		}
		if (this.filterParam) {
			$.extend(this.filterParam,sendData);
		}
		var that = this;
		var url = this.url;
		var moduleId = this.moduleId;
		var data = sendData;
		var callback = function(result) {
			/*if(result.Status !=0){
			 Util.showDialog({title:"提示",msg:result.ErrorInfo,okBth:"确定"});
			 }*/
			if(!result.data){
				result.data = null;
				toastr.info("记录不存在");
			}
			that.allPageSize = Math.ceil(result.data.total_count/that.pageSize);
			var list = null;
			if (that.resultFilter) {
				list = that.resultFilter(result);
			} else {
				list = result.data.result;
			}

			// 把当前索引号添加进去
			for (var i = 0; i < list.length; i++) {
				var h_ = list[i];
				h_.DATAINDEX_ = (that.pageNow - 1) * that.pageSize + i + 1;
			}
			var html = "";
			if (that.tmplEvents) {
				html = $("#" + that.rowTemplateId).tmpl(list, that.tmplEvents);
			} else {
				html = $("#" + that.rowTemplateId).tmpl(list);
			}

			var bindTargets = html.find(".js_bind_data");
			if (typeof (that.targetContentId) == "string") {
				// if(html.length!=0){
				$("#" + that.targetContentId).html(html);
				// }
				bindTargets = $("#" + that.targetContentId).find(
					".js_bind_data");
			} else {
				that.targetContentId.html(html);
				bindTargets = that.targetContentId.find(".js_bind_data");
			}

			for (var i = 0; i < list.length; i++) {
				var h_ = $(bindTargets[i]);
				h_.data("personInfor", list[i]);
				h_.attr("html", i);
			}

			that.initPageBtns(result.data.total_count,that.allPageSize);
			target.find(".load_icon").remove();
			//如果查询到的数据长度为0；
			if (list.length == 0) {
				var dom = target.get(0);
				var nodeName = dom.nodeName;
				nodeName = nodeName.toLowerCase();
				if (nodeName == "tbody") {
					var length = target.prev().find("th").length || target.prev().find("td").length || 7;
					target.append("<tr><td colspan='"
						+ length
						+ "' class='t_a_c'>暂无数据</td></tr>");
				} else {
					target.append("<div class='no_data_div'>暂无数据</div>");
				}
			}
			if(0 == that.updateAuth){
				$(".table-update").hide();
				$(".table-manage").hide();
			}

			if (that.callback) {
				that.callback();
			}
			if(typeof(that.tmplBindEvents)!="object"){
				that.tmplBindEvents();
			}
		};

		var errorCallback = function(errorMsg){
			toastr.error(errorMsg);
		};

		Util.ajaxLoadData(url,data,moduleId,"POST",true,callback, errorCallback);
	};
	return Page;
})();