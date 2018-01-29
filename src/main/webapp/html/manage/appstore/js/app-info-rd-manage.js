$(function() {
    var departId = Util.cookieStorage.getCookie("departId");
    var companyId = Util.cookieStorage.getCookie("companyId");
    var roleId = Util.cookieStorage.getCookie("roleId");
    var uid = Util.cookieStorage.getCookie("id");
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
            var imgLen = $("img[class=up-img]").size();
            var imgurl = uploadUrl + "upload";
            var imgArray = [];
            var imgCount = 0;
            for(var k=0; k <imgLen; k++){
                var ff = $("img[class=up-img]")[k]
                var base64Data = getBase64Image(ff)
                //var base64Data = ff.src
                var blobs = dataURItoBlob(base64Data)
                var ffName = $("p[class=img-name-p]")[k].innerHTML
                var imgdata = new FormData();
                imgdata.append('file', blobs, ffName)
                $.ajax({
                    headers: {
                    },
                    url:imgurl,
                    type:"post",
                    data:imgdata,
                    processData:false,
                    contentType: false,
                    success:function(result){
                        if (result.ret == true) {
                            imgArray.push(uploadUrl + result.info.md5);
                            if (++imgCount == imgLen){
                                var imgStr = ''
                                var arrLen = imgArray.length
                                if(1 == arrLen){
                                    imgStr = imgArray[0]
                                } else if(arrLen > 1){
                                    for (var n=0; n < arrLen - 1; n++){
                                        imgStr = imgStr + imgArray[n] + ';'
                                    }
                                    imgStr = imgStr + imgArray[arrLen - 1]
                                }
                                var url = ctx + "xiaoyusvr/appinformation/appInsert";
                                var data = new FormData();
                                //data.append("appIndex", $("#input-appindex").val());
                                data.append("appName",$("#input-appname").val());
                                data.append("appSpell", $("#input-appspell").val());
                                data.append("devId", parseInt(uid));
                                data.append("devName", nickName);
                                data.append("topCategory", topcategory_txt);
                                data.append("category", category_txt);
                                data.append("channelId", parseInt($('#searchchannels option:selected').val()));
                                data.append("description", $("#input-description").val());
                                data.append("images", imgStr);
                                data.append("app", $("#input-download")[0].files[0], $("#input-download")[0].files[0].name);
                                var userid = Util.cookieStorage.getCookie("id");
                                var str =  "5|5|5|" + userid + "|" + moduleId;
                                var access_Token = Util.cookieStorage.getCookie("accessToken");
                                $.ajax({
                                    headers: {
                                        "FISE-UA": str,
                                        "FISE-AccessToken": access_Token
                                    },
                                    url:url,
                                    type:"post",
                                    data:data,
                                    processData:false,
                                    contentType: false,
                                    xhr: function(){
                                        var xhr = $.ajaxSettings.xhr();
                                        if(onprogress && xhr.upload) {
                                            xhr.upload.addEventListener("progress" , onprogress, false);
                                            return xhr;
                                        }
                                    },
                                    success:function(result){
                                        if (result.msg == "新增应用成功") {
                                            $("#addTempl-modal").modal('hide');
                                            toastr.success("新增应用成功!");
                                            action.loadPageData();
                                            $("#iconShow").empty();
                                            $("#imgShow").empty();
                                            $(".up-section").remove();
                                            $("#son").html( 0 +"%" );
                                            $("#son").css("width" , 0 +"%");
                                        }else{
                                            alert(result.msg);
                                        }
                                    },
                                    error:function(e){
                                        alert("错误！！");
                                    }
                                });
                            }
                        }else{
                            alert(result.ret);
                        }
                    },
                    error:function(e){
                        alert("错误！！");
                    }
                });
            }
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
                    "dev_id":parseInt(uid),
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
		},
        //删除数据
        deleteConfig : function(deleteid) {
            if (confirm("删除后不可恢复，确定删除" + name + "？")) {
                var url = ctx + "xiaoyusvr/appinformation/appDelete";
                var data = new Object();
                data.app_id = parseInt(deleteid);


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
                "channel_name":search_channelname,
                "status": 1
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
                    $("#pageChannels").tmpl(result.data.result).appendTo('#searchchannels');
                    $("#pageChannels").tmpl(result.data.result).appendTo('#input-channels');
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
                    toastr.error(result.msg);
                }
            });
        }
	};
	window.action = action;
    action.init();
	action.loadPageData();
    action.loadChannelData();

    //编辑获取数据数据
    $("#pageContent").on("click",".table-edit-btn",function(){
        var that = $(this).parent().parent();
        /*var check_topcategory = $.trim(that.find("td").eq(7).text());
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
        }*/

        var orientation_val = $.trim(that.find("td").eq(23).text());

        var iconList = $.trim(that.find("td").eq(13).text()).split(";");
        var myDiv1 = document.getElementById("iconShow");
        for(var i=0; i < iconList.length; i++){
            var img1 = document.createElement("img");
            img1.setAttribute("class", "newIcon");
            img1.width =120;
            img1.src = iconList[i];
            img1.onclick=function() {
                var _this = $(this);//将当前的pimg元素作为_this传入函数
                imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
                /*if(this.width == 420){
                 this.width+=200;
                 } else {
                 this.width = 420;
                 }*/
                /*this.style.zoom= 2;
                 window.open(this.src);*/
            };
            myDiv1.appendChild(img1);
        }

        var imgList = $.trim(that.find("td").eq(15).text()).split(";");
        var myDiv2 = document.getElementById("imgShow");
        for(var i=0; i < imgList.length; i++){
            var img = document.createElement("img");
            img.setAttribute("class", "newImg");
            img.width =420;
            img.src = imgList[i];
            img.onclick=function() {
                var _this = $(this);//将当前的pimg元素作为_this传入函数
                imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
                /*if(this.width == 420){
                 this.width+=200;
                 } else {
                 this.width = 420;
                 }*/
                /*this.style.zoom= 2;
                 window.open(this.src);*/
            };
            myDiv2.appendChild(img);
        }

        $("#input-id").val(that.find("td").eq(0).text());
        //$("#input-appindex").val(that.find("td").eq(2).text());
        $("#input-appname").val(that.find("td").eq(1).text());
        $("#input-appspell").val(that.find("td").eq(3).text());
        $("#input-packagename").val(that.find("td").eq(4).text());
        $("#input-devid").val(that.find("td").eq(5).text());
        $("#input-devname").val(that.find("td").eq(6).text());

        //$("input[name=topcategory]").filter("[value=" + topcategory_val + "]").prop('checked', true);
        $("#input-topcategory").val(that.find("td").eq(7).text());
        $("#input-category").val(that.find("td").eq(8).text());
        $("#input-appchannel").val(that.find("td").eq(9).text());
        $("#input-description").val(that.find("td").eq(10).text());
        $("#input-version").val(that.find("td").eq(11).text());
        $("#input-versioncode").val(that.find("td").eq(12).text());

        //$("#input-icon").val(that.find("td").eq(12).text());
        $("#input-icontype").val(that.find("td").eq(14).text());
        //$("#input-images").val(that.find("td").eq(14).text());
        //$("#input-download").val(that.find("td").eq(15).text());

        $("#input-size").val(that.find("td").eq(17).text());
        $("#input-prority").val(that.find("td").eq(18).text());

        $("#input-label").val(that.find("td").eq(21).text());
        $("#input-star").val(that.find("td").eq(22).text());
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
			$("h4#addTempl-modal-label").text("查看应用");
            $("#add-developerid-wrap").hide();
            $("#add-developername-wrap").hide();
            $("#showIcon").show();
            $("#showIconprompt").show();
            $("#showImage").show();
            $("#showImageprompt").show();
            $("#addImgUrl").hide();
            $("#addApk").hide();
            $("#showProgress").hide();
            $("#btn-add-submit").hide();
            $("#topcategorySelect").hide();
            $("#topcategoryName").show();
            $("#categorySelect").hide();
            $("#categoryName").show();
            $("#appchannelSelect").hide();
            $("#appchannelName").show();
            $("#son").html( 0 +"%" );
            $("#son").css("width" , 0 +"%");
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add") {
            $("h4#addTempl-modal-label").text("添加应用");
            $("#add-developerid-wrap").hide();
            $("#add-developername-wrap").hide();
            $("#showIcon").hide();
            $("#showIconprompt").hide();
            $("#showImage").hide();
            $("#showImageprompt").hide();
            $("#addImgUrl").show();
            $("#addApk").show();
            $("#showProgress").show();
            $("#btn-add-submit").show();
            $("#topcategorySelect").show();
            $("#topcategoryName").hide();
            $("#categorySelect").show();
            $("#categoryName").hide();
            $("#appchannelSelect").show();
            $("#appchannelName").hide();
            $("#son").html( 0 +"%" );
            $("#son").css("width" , 0 +"%");
            $form.data("action", "add");
            $form[0].reset();
		}
	});
    /*$("#addTempl2-modal").on('show.bs.modal', function(e) {
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
    });*/

    //关闭或者hide弹出框清空插入的图片
    $("#addTempl-modal .close").on('click', function() {
        $("#iconShow").empty();
        $("#imgShow").empty();
    });

    $('#addTempl-modal button[data-dismiss = "modal"]').on('click', function() {
        $("#iconShow").empty();
        $("#imgShow").empty();
    });

	//验证表单
    $("#form-addTempl").validate({
        rules : {
            devappName : {
                required : true
            },
            devappSpell : {
                required : true
            },
            devappdesp : {
                required : true
            }
        }
    });
 /*   //验证表单
    $("#form-addTempl2").validate({
        rules : {
        }
    });*/

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

    $("#categorySelectItem").change(function(){
        if($('#category1 option:selected').val() != "" || $('#category2 option:selected').val() != "" || $('#category3 option:selected').val() != ""){
            $("#categorySelectItem").parent().removeClass("has-error");
            $("#categorySelectItem .error").remove();
        }
    });

    $("#searchchannels").change(function(){
        if($(this).val() != ""){
            $(this).parent().parent().removeClass("has-error");
            $(this).next().remove();
        }
    });
    $("#addImgUrl").change(function(){
        if($("img[class=up-img]").size() > 0){
            $("#addImgUrl").removeClass("has-error");
            $("#addImgUrl .error").remove();
        }
    });
    $("#input-download").change(function(){
        if($(this).val() != ""){
            $(this).parent().parent().removeClass("has-error");
            $("#addApk .error").remove();
        }
    });


    $("#btn-add-submit").on('click', function() {
        var action = $("form#form-addTempl").data("action");
        if(action == "add"){
            window.action.add();
            /*if (!$("#form-addTempl").valid()) {
                return;
            }else if($('#category1 option:selected').val() == "" && $('#category2 option:selected').val() == "" && $('#category3 option:selected').val() == "") {
                $("#categorySelectItem").parent().addClass("has-error");
                var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
                $("#categorySelectItem").append(err_html);
                return;
            }else if($('#searchchannels option:selected').val() == "") {
                $("#searchchannels").parent().parent().addClass("has-error");
                var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
                $("#searchchannels").append(err_html);
                return;
            }else if($("img[class=up-img]").size() == 0) {
                $("#addImgUrl").addClass("has-error");
                var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
                $("#addImgUrl").append(err_html);
                return;
            }else if($('#input-download').val() == "") {
                $("#input-download").parent().parent().addClass("has-error");
                var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
                $("#input-download").parent().parent().append(err_html);
                return;
            }else {
                window.action.add();
            }*/
        }else if(action == "edit"){
            if (!$("#form-addTempl").valid()) {
                return;
            }else{
                window.action.edit();
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


function getBase64Image(img) {
    var canvas = document.createElement("canvas");
    canvas.width = img.naturalWidth;
    canvas.height = img.naturalHeight;
    var ctxx = canvas.getContext("2d");
    ctxx.drawImage(img, 0, 0, canvas.width, canvas.height);
    var dataURL = canvas.toDataURL("image/png");
    return dataURL
}


function dataURItoBlob(base64Data) {
    var byteString;
    if (base64Data.split(',')[0].indexOf('base64') >= 0)
        byteString = atob(base64Data.split(',')[1]);
    else
        byteString = unescape(base64Data.split(',')[1]);
    var mimeString = base64Data.split(',')[0].split(':')[1].split(';')[0];
    var ia = new Uint8Array(byteString.length);
    for (var i = 0; i < byteString.length; i++) {
        ia[i] = byteString.charCodeAt(i);
    }
    return new Blob([ia], {type:mimeString});
}

function onprogress(evt){
    var loaded = evt.loaded;     //已经上传大小情况
    var tot = evt.total;      //附件总大小
    var per = Math.floor(100*loaded/tot);  //已经上传的百分比
    $("#son").html( per +"%" );
    $("#son").css("width" , per +"%");
}


