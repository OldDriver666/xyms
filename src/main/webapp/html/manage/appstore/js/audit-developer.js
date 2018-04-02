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
		//获取所有数据
		loadPageData : function() {
            var td_len = $("#table thead tr th").length;//表格字段数量
            var page_content_num = parseInt($("#input-page-content-num").val());

			var url = ctx + "xiaoyusvr/boss/developer/query";
			var data = new Object();
			data.page_no = 1;
			data.page_size = page_content_num;
			data.param = {
				"developer_id": null,
				"account":$("#input-search-account").val(),
				"status":parseInt($('#input-search-status option:selected').val())
			};

			var opt = {
				"targetContentId" : "pageContent",
				"url" : url,
				"forAuth2" : true,
				"updateAuth" : updateAuth,
				"moduleId" : moduleId,
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
			var url = ctx + "xiaoyusvr/boss/developer/checkup";
			var data = new Object();
			data.developer_id = parseInt($("#input-id").val());
			data.status = parseInt($("input[name=status]:checked").val());
			data.remarks = $("#input-remarks").val();

			Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
				if (result.code == ReturnCode.SUCCESS) {
			 		$("#addTempl-modal").modal('hide');
                    toastr.success("编辑成功!");
                    action.loadPageData();
					$("#modify-password-wrap").val("");
					$("#imgShow").empty();
				}else{
					$("#modify-password-wrap").val("");
					toastr.error(result.msg);
				}
			});
		}
	};
	window.action = action;
	action.init();
	action.loadPageData();

	$("#addTempl-modal").on('show.bs.modal', function(e) {
		// 处理modal label显示及表单重置
		var $form = $("form#form-addTempl");
		if (!e.relatedTarget) {
			$("h4#addTempl-modal-label").text("审核开发者信息");
			/*$("#input-account-wrap").hide();
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
			$("#edit-status-wrap").show();*/
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add") {
			$("h4#addTempl-modal-label").text("添加开发者信息");
			$form.data("action", "add");
			$form[0].reset();
		}
	});


    //编辑获取数据
    $("#pageContent").on("click",".table-edit-btn",function(){
        var that = $(this).parent().parent();
		var check_status = $.trim(that.find("td").eq(4).text());
		var status_val = null;
		if(check_status === "可用"){
			status_val = 1;
		}else if(check_status === "不可用"){
			status_val = 0;
		}else if(check_status === "已删除"){
			status_val = 2;
		}

		var imgList = $.trim(that.find("td").eq(10).text()).split(";");
		var myDiv = document.getElementById("imgShow");
		for(var i=0; i < imgList.length; i++){
			var img = document.createElement("img");
			img.setAttribute("class", "newImg");
			img.width =460;
			img.src = imgList[i];
			img.onclick=function() {
				var _this = $(this);//将当前的pimg元素作为_this传入函数
				imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
				/*if(this.width == 460){
					this.width+=200;
				} else {
					this.width = 460;
				}*/
				/*this.style.zoom= 2;
				window.open(this.src);*/
			};
			myDiv.appendChild(img);
		}

        $("#input-id").val(that.find("td").eq(7).text());
        $("#input-account-txt").val(that.find("td").eq(0).text());
		$("#input-nickName").val(that.find("td").eq(1).text());
		$("#input-phone").val(that.find("td").eq(2).text());
		$("#input-email").val(that.find("td").eq(3).text());
		$("#input-description").val(that.find("td").eq(8).text());
		$("#input-idCard").val(that.find("td").eq(9).text());

		//$("#input-cardPhoto").val(that.find("td").eq(10).text());
		$("input[name=status]").filter("[value=" + status_val + "]").prop('checked', true);
		$("#input-remarks").val(that.find("td").eq(5).text());


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

	//关闭或者hide弹出框清空插入的图片
	$("#addTempl-modal .close").on('click', function() {
		$("#imgShow").empty();
	});

	$('#addTempl-modal button[data-dismiss = "modal"]').on('click', function() {
		$("#imgShow").empty();
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
    $("#input-page-content-num").change(function() {
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

		Util.ajaxLoadData(url,data,moduleId,"POST",true,callback);
	};
	return Page;
})();


function imgShow(outerdiv, innerdiv, bigimg, _this){
	var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
	$(bigimg).attr("src", src);//设置#bigimg元素的src属性

	/*获取当前点击图片的真实大小，并显示弹出层及大图*/
	$("<img/>").attr("src", src).load(function(){
		var windowW = $(window).width();//获取当前窗口宽度
		var windowH = $(window).height();//获取当前窗口高度
		var realWidth = this.width;//获取图片真实宽度
		var realHeight = this.height;//获取图片真实高度
		var imgWidth, imgHeight;
		var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

		if(realHeight>windowH*scale) {//判断图片高度
			imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
			imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
			if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
				imgWidth = windowW*scale;//再对宽度进行缩放
			}
		} else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
			imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
			imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
		} else {//如果图片真实高度和宽度都符合要求，高宽不变
			imgWidth = realWidth;
			imgHeight = realHeight;
		}
		$(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放

		var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
		var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
		$(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
		$(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
	});

	$(outerdiv).click(function(){//再次点击淡出消失弹出层
		$(this).fadeOut("fast");
	});
}