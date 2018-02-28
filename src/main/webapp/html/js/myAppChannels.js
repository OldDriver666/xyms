$(function(){
    var userName = Util.cookieStorage.getCookie("username");
    var token_value = Util.cookieStorage.getCookie("accesstoken");
    var depart_id = Util.cookieStorage.getCookie("departId");
    var companyId = Util.cookieStorage.getCookie("companyId");
    var role_level = Util.cookieStorage.getCookie("userLevel");
	var admin_id = Util.cookieStorage.getCookie("adminId");
    var nick_name = Util.cookieStorage.getCookie("nickname");

    //页面加载左侧Menu菜单栏
	var Index = {
        //获取全部公司团体数据
        myAppChannels: function(){
            var search_channelname = "";
            var url = ctx + "xiaoyusvr/app/channel/query";
            var data = new Object();
            data.page_no = 1;
            data.page_size = 20;
            data.param = {
                "channel_name":search_channelname,
                "status": 1
            };
            var moduleId = 0;
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS && result.data != ""){
                    localStorage.setItem("myAppChannels", JSON.stringify(result.data.result))
                } else {
                }
            },function() {
            });
        },
	};
    Index.myAppChannels();
});


