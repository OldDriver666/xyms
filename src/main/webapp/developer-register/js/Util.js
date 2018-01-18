var Util = Util ? Util : {};

Util.ajaxLoadData = function(url,data,type,async,callback,errorCallback){
    async = typeof(async)!="undefined" ? async : true;
    type = typeof(type)!="undefined" ? type : "get";
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-Type":"application/json;charset=UTF-8"
        },
        url:url,
        data:JSON.stringify(data),
        type:type,
        dataType:"json",
        async:async,
        cache:false,
        success:function(result){
            result = result || "";
            if(callback){
                if(result != "") {
                    // 未登录直接跳转至登录
                    if(result.code == ReturnCode.EXPIRED_ACCESS_TOKEN) {
                        alert("登录已失效或无此访问权限，请重新登录后尝试！");
                        location.href = ctx + "/login.html";
                    }
                    if(result.code == ReturnCode.REQUEST_HEADER_PARAM_ERROR) {
                        alert("您还未登录，请登录后访问！");
                        location.href = ctx + "/login.html";
                    }
                }
                callback(result);
            }
        },
        error:function(errorMsg){
            var msg = "连接服务器失败";
            if(errorMsg.responseText){
                if(typeof(errorMsg.responseText)=="string"){
                    try{
                        msg = eval('('+errorMsg.responseText+')');
                        msg = msg.msg;
                    }catch(e){

                    }

                }else{
                    msg = errorMsg.responseText.msg;
                }
            }
            if(errorCallback){
                errorCallback(msg);
            }
        }
    });
};

//日期转换
Date.prototype.format = function(format){
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
};

Util.formatDateTime = function(inputTime) {
    var date = new Date(inputTime*1000);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
}

Util.showTit = function(){

}

Util.localStorage = {
    init : function(){
        Util.localStorage.localtype = false;
    },
    add : function(name,value,expires){
        if(Util.localStorage.localtype){
            if(Util.localStorage.obj){
                try{
                    if(typeof(value) == "object"){
                        value = JSON.stringify(value);
                    }
                    Util.localStorage.obj.setItem(name,value);
                }catch(e){
                    Util.localStorage.remove(name);
                }

            }
        }else{
            if(expires){
                Util.cookieStorage.setCookie(name,value,expires);
            }else{
                Util.cookieStorage.setCookie(name,value);
            }

        }

    },
    get : function(name){
        if(Util.localStorage.localtype){
            if(Util.localStorage.obj){
                return Util.localStorage.obj.getItem(name);
            }
        }else{
            return Util.cookieStorage.getCookie(name);
        }
    },
    remove : function(name){
        if(Util.localStorage.localtype){
            if(Util.localStorage.obj){
                Util.localStorage.obj.removeItem(name);
            }
        }else{
            Util.cookieStorage.clearCookie(name);
        }
    }


};

Util.getDate = function(strDate) {
    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
    date = date/1000;
    return date;
};

Util.cookieStorage ={
    getCookie:function(name){
        var start = document.cookie.indexOf(name + "=");
        var len = start + name.length + 1;
        if ((!start) && (name != document.cookie.substring(0, name.length))) {
            return "";
        }
        if (start == - 1) {
            return "";
        }
        var end = document.cookie.indexOf(';', len);
        if (end == - 1) {
            end = document.cookie.length;
        }
        return unescape(document.cookie.substring(len, end));
    },
    setCookie:function(name, value, expires){
        var exp = new Date();
        exp.setTime(exp.getTime() + 60 * 1000 * expires);
        /*document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();*/
        document.cookie = name + "=" + escape(value);
    },
    clearCookie:function(name){
        Util.cookieStorage.setCookie(name,"",-1);
    }
};

//获取url参数方法
Util.getParameter = function(name){
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
};

/**
 * 弹出dialog
 *
 * @param config  json对象
 * {title : '提示', msg: '确定要执行当前操作吗?', okBtn: '确定', cancelBtn: '取消', ok: function() {}, cancel: function() {}}
 */
Util.showDialog = function(config) {
    if(typeof config != 'object') {
        config = {title : '提示', msg: '确定要执行当前操作吗?', okBtn: '确定', cancelBtn: '取消', ok: function() {}, cancel: function() {}};
    }
    var title = config.title || "提示";
    var msg = config.msg || "确定要执行当前操作吗";
    var okBtn = config.okBtn || "";
    var cancelBtn = config.cancelBtn || "";
    var hasBtn = (okBtn != "" || cancelBtn != "");
    var dialog = '<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">' +
        '<div class="modal-dialog modal-sm">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>' +
        '<h4 class="modal-title">' + title + '</h4>' +
        '</div>' +
        '<div class="modal-body">' + msg + '</div>' +
        '<div class="modal-footer">' +
        (okBtn != '' ? '<button class="btn btn-primary" data-loading-text="处理中...">' + okBtn + '</button>' : '') +
        (cancelBtn != '' ? '<button id="modal-footer-cancel-btn" class="btn btn-default" data-dismiss="modal" aria-hidden="true">' + cancelBtn + '</button>' : '') +
        (!hasBtn ? '<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">确定</button>' : '') +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';
    var $dialog = $(dialog);
    $dialog.one('show.bs.modal', function() {
        if(okBtn) {
            $(this).off('click', '.modal-footer > .btn:first');
            $(this).on('click', '.modal-footer > .btn:first', function() {
                $dialog.modal('hide');
                try {
                    config.ok();
                } catch(e) {
                    // do something...
                }
            });
        }
        if(cancelBtn) {
            $(this).off('click', '.modal-footer > .btn#modal-footer-cancel-btn');
            $(this).on('click', '.modal-footer > .btn#modal-footer-cancel-btn', function() {
                $dialog.modal('hide');
                try {
                    config.cancel();
                } catch(e) {
                    // do something...
                }
            });
        }
    });
    $dialog.one('hidden.bs.modal', function() {
        $(this).off().find('.btn').off().end().remove();
    });
    $dialog.modal();
}

/**
 * 弹出dialog
 *
 * @param config  json对象
 * {title : '提示', msg: '确定要执行当前操作吗?', okBtn: '确定', cancelBtn: '取消', ok: function() {}, cancel: function() {}}
 */
Util.showDialog2 = function(config) {
    if(typeof config != 'object') {
        config = {title : '提示', msg: '确定要执行当前操作吗?', okBtn: '确定', cancelBtn: '取消', ok: function() {}, cancel: function() {}};
    }
    var title = config.title || "提示";
    var msg = config.msg || "确定要执行当前操作吗";
    var okBtn = config.okBtn || "";
    var cancelBtn = config.cancelBtn || "";
    var hasBtn = (okBtn != "" || cancelBtn != "");
    var dialog = '<div class="modal fade" role="dialog" aria-hidden="true" style="z-index:99999;">' +
        '<div class="modal-dialog modal-sm">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>' +
        '<h4 class="modal-title">' + title + '</h4>' +
        '</div>' +
        '<div class="modal-body">' + msg + '</div>' +
        '<div class="modal-footer">' +
        (okBtn != '' ? '<button class="btn btn-primary" data-loading-text="处理中...">' + okBtn + '</button>' : '') +
        (cancelBtn != '' ? '<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">' + cancelBtn + '</button>' : '') +
        (!hasBtn ? '<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">确定</button>' : '') +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';
    var $dialog = $(dialog);
    $dialog.one('show.bs.modal', function() {
        if(okBtn) {
            $(this).off('click', '.modal-footer > .btn:first');
            $(this).on('click', '.modal-footer > .btn:first', function() {
                $dialog.modal('hide');
                try {
                    config.ok();
                } catch(e) {
                    // do something...
                }
            });
        }
    });
    $dialog.one('hidden.bs.modal', function() {
        $(this).off().find('.btn').off().end().remove();
    });
    $dialog.modal();
}

Util.clearForm = function($form) {
    $(':input', $form)
        .not(':button, :submit, :reset, :hidden')
        .val('')
        .removeAttr('checked')
        .removeAttr('selected');
}

var ReturnCode = {
    SUCCESS : 0,
    SYSTEM_ERROR : 101,
    EXPIRED_ACCESS_TOKEN : 2001,
    REQUEST_HEADER_PARAM_ERROR : 3001,
    REQUSET_JSON_FIELD_ERROR : 3002,
    REQUSET_PARAM_FIELD_ERROR : 3003,
    RECORD_NOT_EXIST_ERROR : 10041,
    DEVICE_NOT_EXIST_ERROR : 10046,
    TOKEN_ERROR : 10020

};

Util.getPath = function(){
    //获取当前网址，如： http://localhost:8080/vernonshopweb/detail.html
    var cur_3w=window.document.location.href;
    //获取主机地址之后的目录，如： vernonshopweb/detail.html
    var pathName=window.document.location.pathname;
    var pos=cur_3w.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
    var localhostPaht=cur_3w.substring(0,pos);
    //获取带"/"的项目名，如：/vernonshopweb
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
};

Util.handleImagePath = function(src){
    if (src && src.indexOf("http") != 0 ) {
        src = cdnImg + src;
    }
    return src;
}


/**
 * 上传图片
 *ossFileUrl 文件上传目录
 * buBtn files File控件
 * containerId 控制器div-外层容器
 * filesCanves 显示图canves img
 * Multiselect true：是否可以多选
 * imgnum 可上传图片数量
 */
Util.loaderImg = function(ossFileUrl,buBtn,containerId,filesCanves,Multiselect,imgnum,loop,self_){
    var	accessid = ''
    var	accesskey = ''
    var	host = ''
    var	policyBase64 = ''
    var	signature = ''
    var	filename = ''
    var	key = ''
    var	expire = 0
    var	now = timestamp = Date.parse(new Date()) / 1000;
    var	suffixs = '';
    var	img_url = '';
    var	count = 0;
    var imgnum = imgnum ? imgnum : 3;
    console.log(ossFileUrl);
    function send_request()
    {
        var data = {};
        var url = ctx + "/oss/upload/signature";
        var callback = function(result){
            if(result.Status === 0){
                data = result.data;
            }else{
                Util.showDialog({title : '提示', msg: "获取签名失败", okBtn: '确定'});
                return;
            }
        }
        Util.ajaxLoadData(url,null,callback,"post",false);
        return data;
    };

    function get_signature()
    {
        //可以判断当前expire是否超过了当前时间,如果超过了当前时间,就重新取一下.3s 做为缓冲
        now = timestamp = Date.parse(new Date()) / 1000;
        console.log('get_signature ...');
        console.log('expire:' + expire.toString());
        console.log('now:', + now.toString())
		/*if (expire < now + 3)
		 {*/
        console.log('get new sign');
        body = send_request();
        var obj = body;
        host = obj.host;
        policyBase64 = obj.policy;
        accessid = obj.key;
        signature = obj.signature;
        expire = parseInt(obj.expire);
        key = obj.prefix;
        return true;
		/*}
		 return false;*/
    };

    function set_upload_param(up)
    {
        var ret = get_signature()
        if (ret == true)
        {
            key = ossFileUrl + key;
            new_multipart_params = {
                'key' : key+ suffixs,
                'policy': policyBase64,
                'OSSAccessKeyId': accessid,
                'success_action_status' : '200', //让服务端返回200,不然，默认会返回204
                'signature': signature
            };

            up.setOption({
                'url': host,
                'multipart_params': new_multipart_params
            });

            console.log('reset uploader' + key)
            //uploader.start();
        }
    }

    var uploader = new plupload.Uploader({
        runtimes : 'html5,flash,silverlight,html4',
        browse_button : buBtn,
        container: containerId,
        flash_swf_url : 'lib/plupload-2.1.2/js/Moxie.swf',
        silverlight_xap_url : 'lib/plupload-2.1.2/js/Moxie.xap',
        multi_selection:Multiselect,
        resize: { width: 640, quality: 90 },

        url : 'http://oss.aliyuncs.com',

        init: {
            PostInit: function(e) {
                var _this =this;
                $("#submitFileBtn").bind("click",function() {
                    set_upload_param(uploader);
                    uploader.start();
                    return false;
                });
            },

            FilesAdded: function(up, files) {
                if(files.length <= imgnum){
                    if(Multiselect){
                        var imglist = $("#"+filesCanves).find("img");
                        if(imglist.length>=imgnum){
                            Util.showDialog({title : '提示', msg: "最多只能上传"+imgnum+"张图片", okBtn: '确定'});
                            return;
                        }
                        var images=$("#"+filesCanves).find("img");
                        if(images){
                            if((images.length+files.length)>imgnum){
                                Util.showDialog({title:'提示',msg:'最多只能上传'+imgnum+'张图片',okBtn:'确定'});
                                return;
                            }
                        }
                    }
                    plupload.each(files, function(file) {
                        var fileds = file.name.substr(file.name.indexOf("."));
                        console.log(file);
                        suffixs = fileds;
                        var preloader = new mOxie.Image();
                        preloader.onload = function() {
                            var size=plupload.formatSize(file.size);
                            if(size.match(/kb/g)){
                                size=parseFloat(size.match(/[0-9|.]/g).join(""));
                            }else if(size.match(/mb/g)){
                                size=parseFloat(size.match(/[0-9|.]/g).join(""));
                                size*=1024;
                            }
                            if(size>2048){
                                Util.showDialog({title : '提示', msg : '图片不得大于2M',okBtn:'确定'});
                                return;
                            }
                            if(loop){
                                if(preloader.width != preloader.height){
                                    Util.showDialog({title : '提示', msg: "您上传的轮播图不是1:1的图片，请重新上传!", okBtn: '确定'});
                                    return;
                                }else{
                                    set_upload_param(uploader);
                                    uploader.start();
                                }
                            }
                            preloader.downsize( 300, 300 );//先压缩一下要预览的图片,宽300，高300
                            //var imgsrc = preloader.type=='image/jpeg' ? preloader.getAsDataURL('image/jpeg',80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
                            var imgsrc = "../images/加载.gif";
                            if(!Multiselect){
                                if(self_!=undefined){
                                    document.getElementById(filesCanves).innerHTML += '<div id="' + file.id
                                        + '" style="margin-right:8px;width:100px;height:100px;display: inline-block;position: relative;vertical-align: bottom;"><input type="hidden" value="'+file.name+'" data-width="'+preloader.width+'" data-height="'+preloader.height+'" data-size="'
                                        + plupload.formatSize(file.size)+'"/><b></b><img src="'+imgsrc+'" data-width="'+preloader.width+'" data-height="'+preloader.height+'" style="width:100px;height:100px;"><a class="del-img" event-del-img="" onclick="delProduct(this);"></a>'
                                        +'<div class="prompt" style="color:#19b458;text-align: center;width:100px;height:25px;z-indent:2;left:0;bottom:0;"></div></div>';
                                }else{
                                    $("#"+filesCanves).attr("src",imgsrc);
                                    $("#"+filesCanves).attr("data-src",imgsrc);
                                    $("#"+filesCanves).attr("data-width",preloader.width);
                                    $("#"+filesCanves).attr("data-height",preloader.height);
                                    $("#"+filesCanves).parent().siblings("a[class='del-img']").show();
                                }

                            }else{
                                if(imgnum<18){
                                    document.getElementById(filesCanves).innerHTML += '<div id="' + file.id
                                        + '" style="margin-right:8px;width:100px;height:100px;display: inline-block;position: relative;vertical-align: bottom;"><input type="hidden" value="'+file.name+'" data-width="'+preloader.width+'" data-height="'+preloader.height+'" data-size="'
                                        + plupload.formatSize(file.size)+'"/><b></b><img src="'+imgsrc+'" data-width="'+preloader.width+'" data-height="'+preloader.height+'" style="width:100px;height:100px;"><a class="del-img" event-del-img="" onclick="delProduct(this);"></a>'
                                        +'<div class="prompt" style="color:#19b458;text-align: center;width:100px;height:25px;z-indent:2;left:0;bottom:0;"></div></div>';
                                }else{
                                    var html = '<div id="' + file.id + '" style="position: relative;vertical-align: bottom;"><input type="hidden" value="'+file.name+'" data-width="'+preloader.width+'" data-height="'+preloader.height+'" data-size="'+plupload.formatSize(file.size)+'"/><b></b>'
                                        +'<div><img src="'+imgsrc+'" style="width:100px;height:100px;"><div class="prompt" style="color:#19b458;text-align: center;width:100px;height:25px;z-indent:2;left:0;bottom:0;"></div></div>'
                                        +'<textarea id="desc_'+file.id+'" class="textarea" rel="detail'+file.id+'" placeholder="选填项，可以在这里添加对改商品图的描述文字" onkeydown="checkMaxInput(this,250)"  onkeyup="checkMaxInput(this,250)" onfocus="checkMaxInput(this,250)" onblur="checkMaxInput(this,250);" maxlength="250" style="margin-top:5px;"></textarea>'
                                        +'<div class="detail'+file.id+'-num textarea-msg" style="bottom: 15px;"><sapn>0/250</sapn></div>'
                                        +'<a href="javascript:void(0);" onclick="delProduct(this);" style="position: absolute;right: 0;top: 15px;margin-right: -120px;color: red;">删除</a>'
                                        +'</div>';
                                    $(html).appendTo("#"+filesCanves);
                                    $("#d-t").show();
                                }
                            }

                            preloader.destroy();
                            preloader = null;
                        };
                        preloader.load( file.getSource() );
                    });
                    if(!loop){
                        set_upload_param(uploader);
                        uploader.start();
                    }

                }else{
                    Util.showDialog({title : '提示', msg: "最多只能上传"+imgnum+"张图片", okBtn: '确定'});
                    return;
                }
            },

            UploadProgress: function(up, file) {
				/*var d = document.getElementById(file.id);
				 d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";

				 var prog = d.getElementsByTagName('div')[0];
				 var progBar = prog.getElementsByTagName('div')[0]
				 //progBar.style.width= 2*file.percent+'px';
				 progBar.style.width= '100%';
				 progBar.setAttribute('aria-valuenow', file.percent);*/
            },

            FileUploaded: function(up, file, info) {
                console.log('uploaded')
                console.log(info.status)

                if (info.status >= 200 || info.status < 200)
                {
                    var imgsrc = cdnImg+key+suffixs;
                    //document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '成功！';
                    if(!Multiselect){
                        if(self_!=undefined){
                            $("#"+containerId+" #"+file.id).find("img").attr("src",imgsrc);
                            $("#"+containerId+" #"+file.id).find("input").val(key+ suffixs);
                            $("#"+containerId+" #"+file.id).find(".prompt").text("上传成功！");
                        }else{
                            var type = $("#"+filesCanves).attr("data-type");
                            if(type=="file"){
                                $("#"+filesCanves).attr("value",key+ suffixs);
                                $("#"+filesCanves).parent().siblings(".fileName").text(file.name);
                                $("#"+filesCanves).parent().siblings(".prompt").text("上传成功！");
                            }else{
                                $("#"+filesCanves).attr("data-src",key+ suffixs);
                                $("#"+filesCanves).attr("src",imgsrc);
                                $("#"+filesCanves).parent().siblings(".prompt").text("上传成功！");
                            }
                        }

                    }else{
                        $("#"+containerId+" #"+file.id).find("input").val(key+ suffixs);
                        $("#"+containerId+" #"+file.id).find("img").attr("src",imgsrc);
                        $("#"+containerId+" #"+file.id).find(".prompt").text("上传成功！");
                    }
                    var $error = null;
                    if(containerId=="simpleGoodsDescRespList"){
                        $error = $("#"+containerId).find("#releaseDetailCover").attr("aria-describedby");
                    }else{
                        $error = $("#"+containerId).find("input").attr("aria-describedby");
                    }

                    $("#"+$error).remove();
                    Util.uploadStatus = info.status;
                    console.log('ossfile' + key)
                }
                else
                {
                    if(!Multiselect){
                        if(self_!=undefined){
                            $("#"+containerId+" #"+file.id).find("img").attr("src","../images/加载失败.jpg");
                            $("#"+containerId+" #"+file.id).find(".prompt").text("上传失败！");
                        }else{
                            $("#"+filesCanves).attr("src","../images/加载失败.jpg");
                            $("#"+filesCanves).parent().siblings(".prompt").text("上传失败！");
                        }

                    }else{
                        $("#"+containerId+" #"+file.id).find("img").attr("src","../images/加载失败.jpg");
                        $("#"+containerId+" #"+file.id).find(".prompt").text("上传失败！");
                    }
                    Util.uploadStatus = info.status;
                    //document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
                    // document.getElementById(fileId).value = file.name;
                }
                set_upload_param(up);
            },

            Error: function(up, err) {
                //set_upload_param(up);
                $("#console").appendTo(document.createTextNode("\nError xml:" + err.response));
            }
        }
    });

    uploader.init();

};

Util.initTabPage = function(options,type){
    var opt = options ? options : {};
    var tabClickCallback = opt.tabClickCallback ? opt.tabClickCallback : null;
    var tabClass = opt.tabClass ? opt.tabClass : "join-tab";//点检的对象
    var content = opt.content ? opt.content : $(document);
    var lineHightClass = opt.lineHightClass ? opt.lineHightClass : "active";//点击后给的样式
    content.find("."+tabClass).bind("click",function(){
        var rel = $(this).attr("rel");
        $(this).siblings("."+tabClass).each(function(){
            var sibRel = $(this).attr("rel");
            $(this).removeClass(lineHightClass);
            $("#"+sibRel).hide();
        });

        $("#"+rel).show();
        if(type){
            var href = $("#"+rel).find("li.active a").attr("rel-href");
            if(href){
                $("#main_load").attr("src",href);
            }
        }
        $(this).addClass(lineHightClass);
        if(tabClickCallback){
            tabClickCallback($(this),rel);
        }

    });
};
Util.regionArgumentsDetail = function(regionlist){
    var list = regionlist;
    var regionList = [];
    for(var i = 0;i<list.length;i++){
        var obj = {};
        obj.id = list[i].regionId;
        obj.zip_code = list[i].regionId;
        obj.name = list[i].localName;
        obj.children = [];
        for(var j = 0;j<list[i].children.length;j++){
            var chidObj = {};
            chidObj.id = list[i].children[j].regionId;
            chidObj.zip_code = list[i].children[j].regionId;
            chidObj.name = list[i].children[j].localName;
            obj.children.push(chidObj);
        }
        regionList.push(obj);
    }
    return regionList;
}

//获取当前域名
Util.pathName = function(){
    //ctx = "http://10.252.252.250:8787/";        //test version
	ctx = "http://xiaoyu.fise-wi.com:8787/";        //test version
    Util.localStorage.add("ctx",ctx);
};

//获取资源文件
/*Util.Properties = function(){
    var url = ctx + "/init/boss/config";
    var callback = function(result){
        if(result.Status === 0){
            var config = result.data.bossConfig;
            config = JSON.parse(config);
            h5Url = config.string_h5+"/vernonshopweb";
            ctx = config.string_context;
            productsUrl = config.products_url;
            avatarUrl = config.avatar_url;
            snsUrl = config.sns_url;
            filesUrl = config.files_url;
            notesUrl = config.notes_url;
            noticeUrl = config.notice_url;
            cdnImg = config.cdn_img;
            Util.localStorage.add("h5Url",h5Url);
            Util.localStorage.add("ctx",ctx);
            Util.localStorage.add("productsUrl",productsUrl);
            Util.localStorage.add("avatarUrl",avatarUrl);
            Util.localStorage.add("snsUrl",snsUrl);
            Util.localStorage.add("filesUrl",filesUrl);
            Util.localStorage.add("notesUrl",notesUrl);
            Util.localStorage.add("noticeUrl",noticeUrl);
            Util.localStorage.add("cdnImg",noticeUrl);
        }
    };
    Util.ajaxLoadData(url,null,callback,"post",null,false);
};*/

$(function(){
    //初始化localStorage
    Util.localStorage.init();

    //获取当前域名
    Util.pathName();
    //获取资源文件
    /*Util.Properties();*/
});


//后台服务器访问路径
ctx = Util.localStorage.get("ctx");

// cdn url
/*cdnImg = Util.localStorage.get("cdnImg");

h5Url = Util.localStorage.get("h5Url");
productsUrl = Util.localStorage.get("productsUrl");
avatarUrl = Util.localStorage.get("avatarUrl");
avatarDefaultUrl = Util.localStorage.get("avatarDefaultUrl");
snsUrl = Util.localStorage.get("snsUrl");
filesUrl = Util.localStorage.get("filesUrl");
notesUrl = Util.localStorage.get("notesUrl");
noticeUrl = Util.localStorage.get("noticeUrl");*/


// 提供一些IQuery增强
(function($){
	/*
	 * 限制文本框输入非数字
	 * @author: 水墨
	 * @date: 2015-05-19
	 */
    $.fn.numberOnly = function(){
        $(this).bind('keypress',function(event) {
            var keyCode = event.which;
            if (keyCode == 46 || (keyCode >= 48 && keyCode <=57) || keyCode == 8)//8是删除键
                return true;
            else
                return false;
        }).focus(function() {
            this.style.imeMode='disabled';
        });
        return $(this);
    };
    $.fn.serializeJson=function(){
        var serializeObj={}; // 目标对象
        var array=this.serializeArray(); // 转换数组格式
        // var str=this.serialize();
        $(array).each(function(){ // 遍历数组的每个元素 {name : xx , value : xxx}
            if(serializeObj[this.name]){ // 判断对象中是否已经存在 name，如果存在name
                if($.isArray(serializeObj[this.name])){
                    serializeObj[this.name].push(this.value); // 追加一个值 hobby : ['音乐','体育']
                }else{
                    // 将元素变为 数组 ，hobby : ['音乐','体育']
                    serializeObj[this.name]=[serializeObj[this.name],this.value];
                }
            }else{
                serializeObj[this.name]=this.value; // 如果元素name不存在，添加一个属性 name:value
            }
        });
        return serializeObj;
    };
})(jQuery);