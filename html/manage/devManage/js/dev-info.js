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
            var url = ctx + "xiaoyusvr/boss/fisedevice/addfisedevice";
            var data = new Object();
            data.ime = $("#input-devIME").val();
            data.account = $("#input-devXW").val();
            data.depart_id = parseInt(companyId);
            data.type = parseInt($('#input-devType option:selected').val());
            data.mobile = $("#input-phoneNo").val();
            data.mark = $("#input-Mark").val();
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if (result.code == ReturnCode.SUCCESS) {
                    $("#addTempl-modal").modal('hide');
                    toastr.success("添加成功!");
                    action.loadPageData();
                }else{
                	alert(result.msg);
				}
            });
		},
		//获取所有数据
		loadPageData : function() {
            //var search_departID = $("#input-search-departID").val();
			var search_IME = $("#input-search-IME").val();
            var search_XW = $("#input-search-XW").val();
            var search_status = $("#input-search-status option:selected").val();

            var td_len = $("#table thead tr th").length;//表格字段数量
            $("#pagination").hide();
            var url = ctx + "xiaoyusvr/boss/fisedevice/queryfisedevice";
            var data = new Object();
                data.page_no = 1;
                data.page_size = 20;
                data.param = {
                    "ime":search_IME,
                    "account":search_XW,
                    "depart_id":parseInt(companyId),
                    "status":parseInt(search_status)
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
        //获取设备类型列表数据
        loadDevTypeData : function() {
            var myDevTypeArray = JSON.parse(localStorage.getItem("myDevTypeArray"));
            $("#pageDevType").tmpl(myDevTypeArray).appendTo('#input-devType');
            $("#pageDevType").tmpl(myDevTypeArray).appendTo('#input-devType2');
        },
		//编辑数据
		edit : function() {
            var url = ctx + "xiaoyusvr/boss/fisedevice/updatefisedevice";
            var data = new Object();
            data.fise_id = $("#input-id").val();
            data.ime = $("#input-devIME").val();
            data.status = parseInt($("input[name=status]:checked").val());
            data.account = $("#input-devXW").val();
            data.depart_id = parseInt(companyId);
            data.type = parseInt($("#input-devTypeNo").val());
            data.mobile = $("#input-phoneNo").val();
            data.mark = $("#input-Mark").val();
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if (result.code == ReturnCode.SUCCESS) {
                    $("#addTempl-modal").modal('hide');
                    toastr.success("编辑成功!");
                    action.loadPageData();
                }else{
                    alert(result.msg);
                }
            });
		},
		//删除数据
        deleteItem : function(id) {
			if (confirm("删除后不可恢复，确定删除" + name + "？")) {
				var url = ctx + "xiaoyusvr/boss/fisedevice//delfisedevice";
				var data = new Object();
                data.fise_id = id;
				Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
					if (result.code == ReturnCode.SUCCESS) {
                        toastr.success("删除成功!");
                        action.loadPageData();
					}else{
                        alert(result.msg);
                    }
				});
			}
		},
        //获取并处理批量插入的数据
        getDevTxtInfo : function () {
            var addCount = 10;
            var txtDevInfoList = $(".fileContentTxt").text();
            var txtDevType = $("#input-devType2").val();
            var txtDevInfo_arr = txtDevInfoList.split(/\s+/);//文件里一共有多少条数据
            var txt_IME_arr = [];
            var txt_XW_arr = [];
            for(i=0; i<txtDevInfo_arr.length; i++){
                var txt_dev_item_arr = txtDevInfo_arr[i].split(",");
                txt_IME_arr.push(txt_dev_item_arr[1]);//得到所有IME参数值的数组
                txt_XW_arr.push(txt_dev_item_arr[3]);//得到所有XW参数值的数组
            };
            var add_data_count = Math.ceil(txtDevInfo_arr.length/addCount);//计算得到要通过执行几次插入
            var add_data_last =  txtDevInfo_arr.length % addCount;//计算得到最后一次插入时，还剩几条数据

            //每隔一定时间调用一次插入
            var s = null;
            var k = -1;
            s = setInterval(function(){
                k++;
                var flag = null;
                var n = k * addCount;
                var num = 10;//每次插入几条数据的参数
                if(k == add_data_count-1){
                    var m = n + add_data_last;
                    flag = 1;
                    num = add_data_last;//如果执行到最后一次调用，最后一次剩下的数据条数赋给num,作为对应的参数值
                    clearInterval(s);//执行完最后一次调用后关闭计时器
                }else{
                    var m = n + addCount;
                }
                var param_arr = [];
                param_arr.splice(0, param_arr.length);//每执行完一次后清空下该数组
                //遍历分批次插入时，每一次要插入的数据
                for (l = n; l < m; l++) {
                    var param = new Object();
                    param.ime = txt_IME_arr[l];
                    param.account = txt_XW_arr[l];
                    param.depart_id = parseInt(companyId);
                    param.type = parseInt(txtDevType);
                    param_arr.push(param);
                }
                action.addDevFile(param_arr,flag,num)
            },1000);

        },
        //批量添加设备信息
        addDevFile : function (param_arr,flag,num) {
            var url = ctx + "xiaoyusvr/boss/fisedevice/excel_import";
            var data = {
                UserName: userName,
                AuthenticCode: token_value,
                DeviceNo: num,//每次插入几条数据
                DeviceInfo: param_arr
            };
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if (result.code == ReturnCode.SUCCESS) {
                    //通过函数传参得到flag,如果值为1时说明已经执行到最后一次调用，然后关闭弹窗，提示添加完成，获取最新数据
                    if(parseInt(flag) == 1){
                        $("#modal-loading").modal('hide');
                        toastr.success("添加完成!");
                        action.loadPageData();
                        // action.getDevStatusData();
                    }
                }else if(result.code == 1){
                    toastr.error(result.ErrorInfo);
                    $("#modal-loading").modal('hide');
                }else{
                    alert("添加失败！");
                    $("#modal-loading").modal('hide');
                }
            })
        },
        //批量添加设备信息
        addDevInfoFile : function () {
            var userid = Util.cookieStorage.getCookie("id");
            var str =  "5|5|5|" + userid + "|" + moduleId;
            var access_Token = Util.cookieStorage.getCookie("accessToken");

            var form = document.getElementById('upload-form');
            var formData = new FormData(form);
            var xhr = new XMLHttpRequest();
            xhr.open('POST', form.action);
            /*xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("FISE-UA", str);
            xhr.setRequestHeader("FISE-AccessToken", access_Token);
            xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");*/
            /*xhr.onload = function () {
                if (xhr.status === 200) {
                    $("#modal-loading").modal('hide');
                    toastr.success("添加完成!");
                    action.loadPageData();
                } else {
                    $("#modal-loading").modal('hide');
                    toastr.success("出错了!");
                }
            };*/
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var resJson = JSON.parse(xhr.responseText)
                    $("#modal-loading").modal('hide');
                    toastr.success("添加完成!");
                    action.loadPageData();
                } else {
                    $("#modal-loading").modal('hide');
                    toastr.success("出错了!");
                }
            };
            xhr.send(formData);

            /*var getXmlHttpRequest = function() {
                if (window.XMLHttpRequest) {
                    return new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    return new ActiveXObject("Microsoft.XMLHttpRequest");
                }
            };
            var xhr = getXmlHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var resJson = JSON.parse(xhr.responseText)
                    //cb(resJson);
                }
            };
            xhr.open("post", $(this).attr("http://192.168.2.250:8787/xiaoyusvr/boss/fisedevice/excel_import"), true);
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("FISE-UA", str);
            xhr.setRequestHeader("FISE-AccessToken", access_Token);
            xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

           /!* var dataStr = '';
            for (var i in data) {
                if (dataStr) {
                    dataStr += '&';
                }
                dataStr += i + '=' + data[i];
            }*!/
            xhr.send(formData);*/
        }
	};
	window.action = action;
    action.init();
	action.loadPageData();
	action.loadDevTypeData();

    //编辑获取数据数据
    $("#pageContent").on("click",".table-edit-btn",function(){
        var that = $(this).parent().parent();
        var check_status = $.trim(that.find("td").eq(6).text());
        var status_val = null;
        if(check_status === "未激活"){
            status_val = 0;
        }else if(check_status === "激活"){
            status_val = 1;
        }

        $("#input-id").val(that.find("td").eq(0).text());
        $("#input-devIME").val(that.find("td").eq(1).text());
        $("#input-devXW").val(that.find("td").eq(2).text());
        $("#input-devTypeNo").val(that.find("td").eq(9).text());
        $("#input-devType-txt").val(that.find("td").eq(3).text());
        $("input[name=status]").filter("[value=" + status_val + "]").prop('checked', true);
        $("#input-phoneNo").val(that.find("td").eq(7).text());
        $("#input-Mark").val(that.find("td").eq(8).text());
        $("#addTempl-modal").modal("show");
    });

	$("#addTempl-modal").on('show.bs.modal', function(e) {
		// 处理modal label显示及表单重置
		var $form = $("form#form-addTempl");
		if (!e.relatedTarget) {
			$("h4#addTempl-modal-label").text("编辑设备信息");
            $("#input-phoneNo-wrap").show();
            $("#input-devType-wrap").hide();
            $("#input-devTypeNo-wrap").hide();
            $("#input-devType-txt-wrap").show();
            $("#input-status-wrap").hide();
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add") {
			$("h4#addTempl-modal-label").text("添加设备信息");
			//$("#input-phoneNo-wrap").hide();
            $("#input-devType-wrap").show();
            $("#input-devTypeNo-wrap").hide();
            $("#input-devType-txt-wrap").hide();
            $("#input-status-wrap").hide();
			$form.data("action", "add");
			$form[0].reset();
		}
	});

    $("#addTempl-modal2").on('show.bs.modal', function(e) {

    });

	//验证表单
    $("#form-addTempl").validate({
        rules : {
            devIME : {
                required : true
            },
            devXW : {
                required : true
            }
        }
    });

    $("#dev-query-condition").validate({
        rules : {

        }
    });
    $("#input-devIME").change(function(){
        if($(this).val() != ""){
            $(this).parent().parent().removeClass("has-error");
            $(this).next().remove();
        }
    });
    $("#input-devXW").change(function(){
        if($(this).val() != ""){
            $(this).parent().parent().removeClass("has-error");
            $(this).next().remove();
        }
    });
    $("#input-devType").change(function(){
        if($(this).val() != ""){
            $(this).parent().parent().removeClass("has-error");
            $(this).next().remove();
        }
    });
    $("#btn-add-submit").on('click', function() {
        var action = $("form#form-addTempl").data("action");
        if(action == "add"){
            if (!$("#form-addTempl").valid()) {
                return;
            }else if($('#input-devType option:selected').val() == "") {
                $("#input-password").parent().parent().addClass("has-error");
                var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
                $("#input-password").parent().append(err_html);
                return;
            }else {
                window.action.add();
            }
        }else if(action == "edit"){
            if (!$("#form-addTempl").valid()) {
                return;
            }else{
                window.action.edit();
            }
        }
    });

    $("#btn-add-submit2").click(function(){
        if($("#filepath").val() != '' && $("#input-devType2").val() != ''){
            $("#addTempl-modal2").modal('hide');
            $("#modal-loading").modal({backdrop: 'static', keyboard: false, show: true});
            action.addDevInfoFile();
        }else if($("#filepath").val() == ''){
            alert("请选择文件！");
        }else if($("#input-devType2").val() == ''){
            alert("请选择设备类型！");
        }
    });

	$("#btn-search").on('click', function() {
        action.loadPageData();
	});
	$("#input-search-IME").on('keydown', function(e) {
        if (e.keyCode == 13) {
            action.loadPageData();
        }

	});
    $("#input-search-XW").on('keydown', function(e) {
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
                alert("记录不存在");
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

/*Util.ajaxLoadData(url,data,"POST",true,function(result) {
 if(result.Status == ReturnCode.SUCCESS && result.AuthenticCode != ""){
 $('#pageContent').find("tr").remove();
 $("#pageTmpl").tmpl(result.DeviceInfo).appendTo('#pageContent');

 if($('#pageContent tr').length == 0 || result.Status == 6){
 $('#pageContent').append("<tr><td  colspan='" + td_len + "' class='t_a_c'>暂无数据</td></tr>");
 }
 }else if(result.Status == 6){
 $('#pageContent').find("tr").remove();
 $('#pageContent').append("<tr><td  colspan='" + td_len + "' class='t_a_c'>暂无数据</td></tr>");
 }else {
 alert("请求出错！");
 }
 },function() {
 alert("服务器开个小差，请稍后重试！")
 });
 */