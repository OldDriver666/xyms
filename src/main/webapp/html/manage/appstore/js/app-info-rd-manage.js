$(function() {
    var departId = Util.cookieStorage.getCookie("departId");
    var companyId = Util.cookieStorage.getCookie("companyId");
    var roleId = Util.cookieStorage.getCookie("roleId");
    var id = Util.cookieStorage.getCookie("id");
    var creatorId = Util.cookieStorage.getCookie("creatorId");
    var nickName = Util.cookieStorage.getCookie("nickName");

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
            var topcategory_Val = parseInt($("input[name=topcategory]:checked").val());
            if (topcategory_Val == 1) {
                var topcategory_txt = "软件";
                var category_txt = $('#category1 option:selected').text();
            }else if (topcategory_Val == 2) {
                var topcategory_txt = "游戏";
                var category_txt = $('#category2 option:selected').text();
            }else if (topcategory_Val == 3) {
                var topcategory_txt = "电子书";
                var category_txt = $('#category3 option:selected').text();
            }

            var url = ctx + "xiaoyusvr/appinformation/appInsert";
            var data = new FormData();
            data.append("appIndex", $("#input-appindex").val());
            data.append("appName",$("#input-appname").val());
            data.append("appSpell", $("#input-appspell").val());
            data.append("packageName", $("#input-packagename").val());

            data.append("devId", 2);
            //data.append("devId", parseInt(creatorId));
            data.append("devName", nickName);

            data.append("topCategory", topcategory_txt);
            data.append("category", category_txt);

            data.append("description", $("#input-description").val());
            data.append("version", $("#input-version").val());
            data.append("versioncode", parseInt($("#input-versioncode").val()));
            data.append("app_icon", $("#input-icon")[0].files[0], $("#input-icon")[0].files[0].name);
            data.append("iconType", parseInt($("#input-icontype").val()));
            data.append("app_images", $("#input-images")[0].files[0], $("#input-images")[0].files[0].name);
            data.append("app", $("#input-download")[0].files[0], $("#input-download")[0].files[0].name);
            data.append("size", $("#input-size").val());
            data.append("prority", parseInt($("#input-prority").val()));
            data.append("label", $("#input-label").val());
            data.append("star", $("#input-star").val());
            data.append("orientation", parseInt($('#orientation option:selected').val()));

            var userid = Util.cookieStorage.getCookie("id");
            var str =  "5|5|5|" + userid + "|" + moduleId;
            var access_Token = Util.cookieStorage.getCookie("accessToken");

            $.ajax({
                headers: {
                    /*"Accept": "application/json",*/
                    "FISE-UA": str,
                    "FISE-AccessToken": access_Token
                    /*"Content-Type":"application/json;charset=UTF-8"*/
                },
                url:url,
                type:"post",
                data:data,
                /*dataType: 'json',*/
                processData:false,
                contentType: false,
                success:function(result){
                    if (result.code == ReturnCode.SUCCESS) {

                    }else{
                        alert(result.msg);
                    }
                },
                error:function(e){
                    alert("错误！！");
                }
            });
        },
		//获取所有数据
		loadPageData : function() {
			var search_appname = $("#input-search-appname").val();
            var search_status = parseInt($('#input-search-status option:selected').val());

            var td_len = $("#table thead tr th").length;//表格字段数量
            $("#pagination").hide();
            var url = ctx + "xiaoyusvr/appinformation/appQuery";
            var data = new Object();
                data.page_no = 1;
                data.page_size = 20;
                data.param = {
                    "dev_id":2,
                    "app_name":search_appname,
                    "status":search_status
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
            var topcategory_val = $("input[name=topcategory]:checked").val();
            var topcategory_txt = "";
            var category_txt = "";
            if(topcategory_val == "1"){
                topcategory_txt = "软件";
                category_txt = $('#category1 option:selected').text();
            }else if(topcategory_val == "2"){
                topcategory_txt = "游戏";
                category_txt = $('#category2 option:selected').text();
            }else if(topcategory_val == "3"){
                topcategory_txt = "电子书";
                category_txt = $('#category3 option:selected').text();
            }

            var url = ctx + "xiaoyusvr/appinformation/appModify";
            var data = new FormData();
            data.append("id", parseInt($("#input-id").val()));
            data.append("appIndex", $("#input-appindex").val());
            data.append("appName",$("#input-appname").val());
            data.append("appSpell", $("#input-appspell").val());
            data.append("packageName", $("#input-packagename").val());
            data.append("devId", parseInt($("#input-devid").val()));
            data.append("devName", $("#input-devname").val());
            data.append("topCategory", topcategory_txt);
            data.append("category", category_txt);
            data.append("description", $("#input-description").val());
            data.append("version", $("#input-version").val());
            data.append("versioncode", parseInt($("#input-versioncode").val()));
            data.append("app_icon", $("#input-icon")[0].files[0], $("#input-icon")[0].files[0].name);
            data.append("iconType", parseInt($("#input-icontype").val()));
            data.append("app_images", $("#input-images")[0].files[0], $("#input-images")[0].files[0].name);
            data.append("app", $("#input-download")[0].files[0], $("#input-download")[0].files[0].name);
            data.append("size", $("#input-size").val());
            data.append("prority", parseInt($("#input-prority").val()));
            data.append("label", $("#input-label").val());
            data.append("star", $("#input-star").val());
            data.append("orientation", parseInt($('#orientation option:selected').val()));

            var userid = Util.cookieStorage.getCookie("id");
            var str =  "5|5|5|" + userid + "|" + moduleId;
            var access_Token = Util.cookieStorage.getCookie("accessToken");

            $.ajax({
                headers: {
                    /*"Accept": "application/json",*/
                    "FISE-UA": str,
                    "FISE-AccessToken": access_Token
                    /*"Content-Type":"application/json;charset=UTF-8"*/
                },
                url:url,
                type:"post",
                data:data,
                /*dataType: 'json',*/
                processData:false,
                contentType: false,
                success:function(result){
                    if (result.code == ReturnCode.SUCCESS) {
                        $("#addTempl-modal").modal('hide');
                        toastr.success("编辑成功!");
                        action.loadPageData();
                    }else{
                        alert(result.msg);
                    }
                },
                error:function(e){
                    alert("错误！！");
                }
            });
		},
        //删除数据
        deleteConfig : function(deleteid) {
            if (confirm("删除后不可恢复，确定删除" + name + "？")) {
                var url = ctx + "xiaoyusvr/appinformation/appDelete";
                var data = new Object();
                data.app_id = parseInt(id);


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
        //获取所有数据
        loadChannelData : function() {
            var search_channelname = $("#input-search-channelname").val();

            var td_len = $("#table thead tr th").length;//表格字段数量
            $("#pagination").hide();
            var url = ctx + "xiaoyusvr/app/channel/query";
            var data = new Object();
            data.page_no = 1;
            data.page_size = 20;
            data.param = {
                "channel_name":search_channelname
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
                    $("#pageChannels").tmpl(result.data.result).appendTo('#input-channels');
                   /* //return result.data.result;*/
                },
                "param" : data
            };
            this.page = new Util.Page(opt);
        },
        //新增数据
        addToChannel : function() {
            var url = ctx + "xiaoyusvr/app/channellist/insert";
            var data = new Object();
            data.channel_id = parseInt($('#input-channels option:selected').val());
            data.app_id = parseInt($("#input-app-id").val());
            data.status = parseInt($("input[name=status-txt]:checked").val());
            data.prority = parseInt($("#input-prority-txt").val());

            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if (result.code == ReturnCode.SUCCESS) {
                    $("#addTempl2-modal").modal('hide');
                    toastr.success("添加成功!");
                }else{
                    alert(result.msg);
                }
            });
        },
	};
	window.action = action;
    action.init();
	action.loadPageData();
    action.loadChannelData();

    //编辑获取数据数据
    $("#pageContent").on("click",".table-edit-btn",function(){
        var that = $(this).parent().parent().parent();
        var check_topcategory = $.trim(that.find("td").eq(7).text());
        var check_category = $.trim(that.find("td").eq(8).text());
        var topcategory_val = null;
        if(check_topcategory === "软件"){
            topcategory_val = 1;
        }else if(check_topcategory === "游戏"){
            topcategory_val = 2;
        }else if(check_topcategory === "电子书"){
            topcategory_val = 3;
        }
        if (topcategory_val =="1"){
            $("#category1").show();
            $("#category2").hide();
            $("#category3").hide();
            $("#category1 option").each(function(){
                if($(this).text() == check_category) {
                    $(this).attr("selected", true);
                }
            });
        }else if (topcategory_val =="2"){
            $("#category1").hide();
            $("#category2").show();
            $("#category3").hide();
            $("#category2 option").each(function(){
                if($(this).text() == check_category) {
                    $(this).attr("selected", true);
                }
            });
        }else if (topcategory_val =="3"){
            $("#category1").hide();
            $("#category2").hide();
            $("#category3").show();
            $("#category3 option").each(function(){
                if($(this).text() == check_category) {
                    $(this).attr("selected", true);
                }
            });
        }

        var orientation_val = $.trim(that.find("td").eq(22).text());

        $("#input-id").val(that.find("td").eq(0).text());
        $("#input-appindex").val(that.find("td").eq(2).text());
        $("#input-appname").val(that.find("td").eq(1).text());
        $("#input-appspell").val(that.find("td").eq(3).text());
        $("#input-packagename").val(that.find("td").eq(4).text());
        $("#input-devid").val(that.find("td").eq(5).text());
        $("#input-devname").val(that.find("td").eq(6).text());

        $("input[name=topcategory]").filter("[value=" + topcategory_val + "]").prop('checked', true);


        $("#input-description").val(that.find("td").eq(9).text());
        $("#input-version").val(that.find("td").eq(10).text());
        $("#input-versioncode").val(that.find("td").eq(11).text());

        //$("#input-icon").val(that.find("td").eq(12).text());
        $("#input-icontype").val(that.find("td").eq(13).text());
        //$("#input-images").val(that.find("td").eq(14).text());
        //$("#input-download").val(that.find("td").eq(15).text());

        $("#input-size").val(that.find("td").eq(16).text());
        $("#input-prority").val(that.find("td").eq(17).text());

        $("#input-label").val(that.find("td").eq(20).text());
        $("#input-star").val(that.find("td").eq(21).text());
        $("#orientation option[value= '"+ orientation_val +"']").attr('selected',true);

        $("#addTempl-modal").modal("show");
    });

    //编辑获取数据数据
    $("#pageContent").on("click",".table-add-btn",function(){
        var that = $(this).parent().parent().parent();

        $("#input-app-id").val(that.find("td").eq(0).text());
        $("#input-app-name").val(that.find("td").eq(1).text());

        $("#addTempl2-modal").modal("show");
    });

	$("#addTempl-modal").on('show.bs.modal', function(e) {
		// 处理modal label显示及表单重置
		var $form = $("form#form-addTempl");
		if (!e.relatedTarget) {
			$("h4#addTempl-modal-label").text("编辑应用信息");
            $("#add-developerid-wrap").hide();
            $("#add-developername-wrap").hide();
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add") {
            $("h4#addTempl-modal-label").text("添加应用信息");
            $("#add-developerid-wrap").hide();
            $("#add-developername-wrap").hide();
            $form.data("action", "add");
            $form[0].reset();
		}
	});
    $("#addTempl2-modal").on('show.bs.modal', function(e) {
        // 处理modal label显示及表单重置
        var $form = $("form#form-addTempl2");
        if (!e.relatedTarget) {
            $("h4#addTempl2-modal-label").text("添加频道应用");
            $form.data("action", "edit");
        } else if (e.relatedTarget.id = "btn-add-userRoles") {
            $("h4#addTempl2-modal-label").text("添加频道应用");
            $form.data("action", "add");
            $form[0].reset();
        }
    });

	//验证表单
    $("#form-addTempl").validate({
        rules : {
        }
    });
    //验证表单
    $("#form-addTempl2").validate({
        rules : {
        }
    });

    $("#dev-query-condition").validate({
        rules : {

        }
    });
    $("#input-userID").change(function(){
        if($(this).val() != ""){
            $(this).parent().parent().removeClass("has-error");
            $(this).next().remove();
        }
    });
    $("#input-title").change(function(){
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
    $("#input-channels").change(function(){
        if($(this).val() != ""){
            $(this).parent().parent().removeClass("has-error");
            $(this).next().remove();
        }
    });

    $("input:radio[name=topcategory]").change(function(){
        var v = $(this).val();
        if (v =="1"){
            $("#category1").show();
            $("#category2").hide();
            $("#category3").hide();
        }else if (v =="2"){
            $("#category1").hide();
            $("#category2").show();
            $("#category3").hide();
        }else if (v =="3"){
            $("#category1").hide();
            $("#category2").hide();
            $("#category3").show();
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
                $("#input-password").append(err_html);
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

    $("#btn-add-submit2").on('click', function() {
        var action = $("form#form-addTempl2").data("action");
        if(action == "edit"){
            if (!$("#form-addTempl2").valid()) {
                return;
            }else if($('#input-channels option:selected').val() == "") {
                $("#input-channels").parent().parent().addClass("has-error");
                var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
                $("#input-channels").append(err_html);
                return;
            }else {
                window.action.addToChannel();
            }
        }
    });

	$("#btn-search").on('click', function() {
        action.loadPageData();
	});
	$("#input-search-appname").on('keydown', function(e) {
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


