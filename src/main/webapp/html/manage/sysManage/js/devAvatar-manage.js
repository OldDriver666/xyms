$(function() {
    var companyId = Util.cookieStorage.getCookie("companyId");

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
                $(".table-update").hide();
                $(".table-manage").hide();
            }
        },
		//新增数据
		add : function() {
           /* var url = ctx + "xiaoyusvr/boss/departconf/addimdepartconfig";
            var data = new Object();
            data.depart_id = parseInt($('#input-depart_id option:selected').val());
            data.client_type = parseInt($('#input-devType option:selected').val());
            data.avatar = $("#input-avatar").val();

            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if (result.code == ReturnCode.SUCCESS) {
                    $("#addTempl-modal").modal('hide');
                    toastr.success("添加成功!");
                    action.loadPageData();
                    if(parseInt($("#input-depart_id").val()) == parseInt(depart_id)){
                        action.myDevTypeQuery();
                    }
                }
                else{
                    toastr.error(result.msg);
                }
            });*/


            var imgLen = $("img[class=up-img]").size();
            var imgurl = uploadUrl + "upload";
            var imgArray = [];
            var imgCount = 0;
            for(var k=0; k <imgLen; k++){
                var ff = $("img[class=up-img]")[k]
                var base64Data = getBase64Image(ff)
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
                                var url = ctx + "xiaoyusvr/boss/departconf/addimdepartconfig";
                                var data = new Object();
                                data.depart_id = parseInt($('#input-depart_id option:selected').val());
                                data.client_type = parseInt($('#input-devType option:selected').val());
                                data.avatar = imgStr;

                                Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                                    if (result.code == ReturnCode.SUCCESS) {
                                        $("#addTempl-modal").modal('hide');
                                        toastr.success("添加成功!");
                                        action.loadPageData();
                                        if(parseInt($("#input-depart_id").val()) == parseInt(depart_id)){
                                            action.myDevTypeQuery();
                                        }
                                    }
                                    else{
                                        alert(result.msg);
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
            var search_depart_id = parseInt($('#input-search-name option:selected').val());
            var search_client_type = parseInt($('#input-search-client_type option:selected').val());
            var page_content_num = parseInt($("#input-page-content-num").val());

            var td_len = $("#table thead tr th").length;//表格字段数量
            $("#pagination").hide();

            if($('#input-search-name option:selected').val() == "") {
                $("#input-search-name").parent().addClass("has-error");
                var err_html = "<label class='error control-label' style='padding-left: 5px;'>必填字段</label>";
                $("#input-search-name").append(err_html);
                return;
            }
            var url = ctx + "xiaoyusvr/boss/departconf/queryImdepCfgPage";
            /*var data = new Object();
            data.depart_id = search_depart_id;
            data.client_type = search_client_type;

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
                    toastr.error(result.msg);
                }
            },function(errorMsg) {
                toastr.error(errorMsg);
            });*/

            var data = new Object();
            data.page_no = 1;
            data.page_size = page_content_num;
            data.param = {
                "depart_id":search_depart_id,
                "client_type":search_client_type
            };

            /*data.extra_param = {
             "nick":search_Nick
             };*/

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
            var dataArray1 = [];
            var allDevTypeArray = [];
            var url = ctx + "xiaoyusvr/boss/clienttype/queryclienttype";
            var moduleId = 0;
            var data = new Object();
            data.client_type = null;
            data.client_name = "";
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS && result.data != ""){
                    allDevTypeArray = result.data;
                    $("#pageDevType").tmpl(allDevTypeArray).appendTo('#input-search-client_type');
                    $("#pageDevType").tmpl(allDevTypeArray).appendTo('#input-devType');
                    $("#input-devType").selectpicker('refresh');
                    $("#input-search-client_type").selectpicker('refresh');
                } else {
                }
            },function() {
            });

           /* var allDevTypeArray = JSON.parse(localStorage.getItem("allDevTypeArray"));
            $("#pageDevType").tmpl(allDevTypeArray).appendTo('#input-search-client_type');
            $("#pageDevType").tmpl(allDevTypeArray).appendTo('#input-devType');*/
        },
        //获取设备类型列表数据
        myDevTypeQuery: function(){
            var dataArray1 = [];
            var dataArray2 = [];
            var myDevTypeArray = [];
            var url = ctx + "xiaoyusvr/boss/clienttype/queryclienttype";
            var data = new Object();
            data.client_type = null;
            data.client_name = "";
            Util.ajaxLoadData(url,data,0,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS && result.data != ""){
                    dataArray1 = result.data;

                    var url_query = ctx + "xiaoyusvr/boss/departconf/queryimdepartconfig";
                    var data_query = new Object();
                    data_query.depart_id = parseInt(depart_id);
                    data_query.client_type = null;
                    Util.ajaxLoadData(url_query,data_query,0,"POST",true,function(result_query) {
                        if(result_query.code == ReturnCode.SUCCESS && result_query.data != ""){
                            dataArray2 = result_query.data;
                            var Len1 = dataArray1.length;
                            var Len2 = dataArray2.length;
                            for(var i =0; i < Len2; i++){
                                for(var j=0; j<Len1; j++){
                                    if(dataArray2[i].client_type == dataArray1[j].client_type){
                                        var str ={
                                            client_type :dataArray1[j].client_type,
                                            client_name :dataArray1[j].client_name
                                        };
                                        myDevTypeArray.push(str);
                                    }
                                }
                            }
                            localStorage.setItem("myDevTypeArray",JSON.stringify(myDevTypeArray));
                            localStorage.setItem("allDevTypeArray",JSON.stringify(dataArray1));
                        } else {
                            toastr.error(result_query.msg);
                        }
                    },function() {
                        toastr.error("服务器开个小差，请稍后重试！")
                    });

                } else {
                    toastr.error(result.msg);
                }
            },function() {
                toastr.error("服务器开个小差，请稍后重试！")
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
                    $("#input-search-name").selectpicker('refresh');
                    $("#input-depart_id").selectpicker('refresh');
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
			var url = ctx + "xiaoyusvr/boss/departconf/updateimdepartconfig";
			var data = new Object();
            data.config_id = parseInt($("#input-config_id").val());
            data.depart_id = parseInt($('#input-depart_idNo').val());
            data.client_type = parseInt($("#input-devTypeNo").val());
            data.avatar = $("#input-avatar").val();

			Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
				if (result.code == ReturnCode.SUCCESS) {
			 		$("#addTempl-modal").modal('hide');
                    toastr.success("编辑成功!");
                    action.loadPageData();
                    if(parseInt($("#input-depart_idNo").val()) == parseInt(depart_id)){
                        action.myDevTypeQuery();
                    }
				}else{
                    alert(result.msg);
                }
			});
		},
		//删除数据
		deleteConfig : function(id) {
			if (confirm("删除后不可恢复，确定删除" + name + "？")) {
				var url = ctx + "xiaoyusvr/boss/departconf/delimdepartconfig";
				var data = new Object();
                data.config_id = id;
				Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
					if (result.code == ReturnCode.SUCCESS) {
                        toastr.success("删除成功!");
                        $("#input-search-depart_id").val("");
                        $("#input-search-client_type").val("");
                        action.loadPageData();
                        action.myDevTypeQuery();
					}else{
                        alert(result.msg);
                    }
				});
			}
		}
	};
	window.action = action;
    action.init();
	//action.loadPageData();
    action.loadDevTypeData();
    action.loadCompanyInfoData();

	$("#addTempl-modal").on('show.bs.modal', function(e) {
		// 处理modal label显示及表单重置
		var $form = $("form#form-addTempl");
		if (!e.relatedTarget) {
			$("h4#addTempl-modal-label").text("查看公司设备信息");
            $("#input-devType-wrap").hide();
            $("#input-devTypeNo-wrap").hide();
            $("#input-devType-txt-wrap").show();
            $("#input-depart_id-wrap").hide();
            $("#input-depart_id-txt-wrap").show();
            $("#input-depart_idNo-wrap").hide();
            $("#addImgUrl").hide();
            $("#showImgUrl").show();
            $("#btn-add-submit").hide();
			$form.data("action", "edit");
		} else if (e.relatedTarget.id = "btn-add") {
			$("h4#addTempl-modal-label").text("添加公司设备信息");
            $("#input-devType-wrap").show();
            $("#input-devTypeNo-wrap").hide();
            $("#input-devType-txt-wrap").hide();
            $("#input-depart_id-wrap").show();
            $("#input-depart_id-txt-wrap").hide();
            $("#input-depart_idNo-wrap").hide();
            $("#addImgUrl").show();
            $("#showImgUrl").hide();
            $("#btn-add-submit").show();
			$form.data("action", "add");
			$form[0].reset();
		}
	});

    //编辑获取数据
    $("#pageContent").on("click",".table-edit-btn",function(){
        var that = $(this).parent().parent();
        /*var imgUrl = that.find("td").eq(4).find("img").prop("src");
        var arrObj = imgUrl.split("//");
        var startIndx = arrObj[1].indexOf("/");
        var relUrl = arrObj[1].substring(startIndx).slice(1);*/
        $("#iconShow").empty();
        var iconList = $.trim(that.find("td").eq(4).find("img").prop("src")).split(";");
        var myDiv1 = document.getElementById("iconShow");
        for(var i=0; i < iconList.length; i++){
            var img1 = document.createElement("img");
            img1.setAttribute("class", "newIcon");
            img1.width =70;
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

        $("#input-config_id").val(that.find("td").eq(0).text());
        $("#input-depart_id-txt").val(that.find("td").eq(2).text());
        $("#input-depart_idNo").val(that.find("td").eq(1).text());
        $("#input-devType-txt").val(that.find("td").eq(3).text());
        $("#input-devTypeNo").val(that.find("td").eq(5).text());
        /*$("#input-avatar").val(imgUrl);*/
        $("#addTempl-modal").modal("show");
    });


	//验证表单
    $("#form-addTempl").validate({
        rules : {
            depart_id : {
                required : true
            },
            devType : {
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
	$("#input-search-depart_id").on('keydown', function(e) {
        if (e.keyCode == 13) {
            action.loadPageData();
        }

	});
    $("#input-page-content-num").change(function() {
        action.loadPageData();
    });

});

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
    canvas.width = img.width;
    canvas.height = img.height;
    var ctxx = canvas.getContext("2d");
    ctxx.drawImage(img, 0, 0, img.width, img.height);
    var dataURL = canvas.toDataURL("image/png");
    return dataURL // return dataURL.replace("data:image/png;base64,", "");
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
            /* this.pageSize = 20;
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
                toastr.error(result.msg);
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