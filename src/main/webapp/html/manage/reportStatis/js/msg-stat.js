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

            }
            if(0 == queryAuth){
                $("#btn-search").hide();
            }
            if(0 == updateAuth){

            }
        },
		//获取用户和设备在线及注册人数的数据
		getMsgData : function() {
            var search_date = $("#input-search-date").val();
            var url = ctx + "xiaoyusvr/boss/report/messagecount";
            var data = new Object();
            data.daytime = search_date;

            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS){
                    $('#xwUserOnlineInfo').empty();
                    $("#pageUserOnline").tmpl(result).appendTo('#xwUserOnlineInfo');

                } else {
                    toastr.error("请求出错！");
                }
            },function() {
                toastr.error("服务器开个小差，请稍后重试！");
            });
		},

        //获取消息类型的数据
        getMsgTypeData : function() {
            var url = ctx + "xiaoyusvr/boss/report/messagetypecount";
            var data = {};
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS && result.data != ""){
                    //客户端类型信息
                    action.myMsgTypeQuery(JSON.stringify(result.data));
                } else {
                    toastr.error(result.msg);
                }
            },function() {
                toastr.error("服务器开个小差，请稍后重试！")
            });
        },
        myMsgTypeQuery: function(nameParamArray){
            var msgTypes = JSON.parse(nameParamArray);
            var data_arr = [];
            var nameArray = [];
            var countArray = [];
            var myTypeArray = [];
            for(items in msgTypes){
                nameArray.push(msgTypes[items].keyName);
                countArray.push(msgTypes[items].keyValue);
            }

            var url = ctx + "xiaoyusvr/boss/messagetype/query";
            var data = new Object();
            data.type = null;
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS && result.data != ""){
                    var allMsgTypeArray = result.data;
                    var Lens1 = nameArray.length;
                    var Lens2 = allMsgTypeArray.length;
                    for(var i=0; i< Lens1; i++){
                        for(var j=0; j< Lens2; j++){
                            if(allMsgTypeArray[j].msg_type == nameArray[i]){
                                var typeName = allMsgTypeArray[j].msg_name;
                                myTypeArray.push(typeName);
                            }
                        }
                    }

                    //客户端类型信息

                    for(var i=0; i < countArray.length; i++){
                         var name = myTypeArray[i];
                         var value = countArray[i];
                         var list_item = '{"name":"'+name+'","value":"'+value+'"}';
                         data_arr.push(list_item);
                    }

                    var data_str = "["+data_arr+"]";
                    var data = JSON.parse(data_str);

                    //用户客户端类型
                    option = {
                        title : {
                            text: '消息类型分布统计',
                            x:'center'
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        series : [
                            {
                                name: '消息类型',
                                type: 'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data: data,
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };
                    userClientChart.setOption(option);

                } else {
                    toastr.error(result.msg);
                }
            },function() {
                toastr.error("服务器开个小差，请稍后重试！")
            });
        }
	};
	window.action = action;
    action.init();
	action.getMsgTypeData();

    $("#btn-search").on('click', function() {
        action.getMsgData();
    });

    $('#input-search-date').datetimepicker({
        lang:'ch',
        format: 'Y-m-d',
        timepicker:false
    });


    var nowTime = getNowFormatDate();//当前日期
    var init_days = 3;//初始时的天数
    var init_starDate = changeDate(nowTime, init_days);//初始时的开始日期
    action.getUseRegData(init_starDate,nowTime,init_days);//初始统计数据
    //点击日期选择天数，显示相应天数的数据
    $(".group li").on("click",function(){
        $(this).addClass("curr").siblings().removeClass("curr");
        var days = $(this).data("days");
        var starDate = changeDate(nowTime, days);
        action.getUseRegData(starDate,nowTime,days);
    });
    //计算当前日期减去天数=目标日期
    function changeDate(date, days) {
        var d = new Date(date);
        d.setDate(d.getDate() - days);
        var month = d.getMonth() + 1;
        var day = d.getDate();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        var starDate = d.getFullYear() + "-" + month + "-" + day;
        return starDate;
    }
    //获取当前时间，格式YYYY-MM-DD
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }
    //获取时间格式YYYYMMDD
    function getReqFormatDate(date) {
        var b = date.split("-");
        var currentdate = b[0] + b[1] +b[2]; //日期字符串
        return currentdate;
    }
});