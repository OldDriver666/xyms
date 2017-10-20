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
		//获取用户和设备在线及注册人数的数据
		getUserDevData : function() {
            var url = ctx + "xiaoyusvr/boss/devicecount";
            var data = new Object();
            data.depart_id = parseInt(companyId);

            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS){
                    $('#devUserOnlineInfo').empty();
                    $('#devUserActiveInfo').empty();
                    $('#xwUserOnlineInfo').empty();
                    $('#xwUserRegInfo').empty();

                    $("#pageUserOnline").tmpl(result.data).appendTo('#xwUserOnlineInfo');
                    $("#pageUserReg").tmpl(result.data).appendTo('#xwUserRegInfo');
                    $("#pageDevOnline").tmpl(result.data).appendTo('#devUserOnlineInfo');
                    $("#pageDevActive").tmpl(result.data).appendTo('#devUserActiveInfo');
                } else {
                    /*alert("请求出错！");*/
                }
            },function() {
               /* alert("服务器开个小差，请稍后重试！");*/
            });
		},

        //获取每天注册用户统计的数据
        getUseRegData : function(startTime,endTime,days) {
            var begin_date = getReqFormatDate(startTime);
            var end_date = getReqFormatDate(endTime);
            var v_y = startTime.substr(0,4);
            var v_m = startTime.substr(5,2);
            var v_d = startTime.substr(8,2);
            var url = ctx + "xiaoyusvr/boss/report/activate";
            var data = {
                "organ_id":parseInt(companyId),
                "begin_date":begin_date,
                "end_date":end_date
            };
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS && result.data != ""){
                    var data_arr = [];
                    var data_arr1 = [];
                    var data_arr2 = [];
                    var dayCount = result.data;
                    for(items in dayCount){
                        var value = dayCount[items];
                        data_arr1.push(items);
                        data_arr2.push(value);
                    }

                    var Len_Count = Object.getOwnPropertyNames(dayCount).length;
                    for(i=0; i<Len_Count; i++){
                         var name = data_arr1[i];
                         var str_year = name.substr(0,4);
                         var str_month = name.substr(4,2);
                         var str_date = name.substr(6,2);
                         var xName = [str_year, str_month, str_date].join('/');
                         var value = data_arr2[i];
                         var list_item = '{"name":"'+name+'","value":["'+xName+'",'+value+']}';
                         data_arr.push(list_item);
                       };
                    var data_str = "["+data_arr+"]";
                    var data = JSON.parse(data_str);

                    if(roleId == 1){
                        option = {
                            title: {
                                text: '用户激活/注册数量',
                                x:'center'
                            },
                            tooltip: {
                                trigger: 'axis',
                                formatter: function (params) {
                                    params = params[0];
                                    var str = params.name;
                                    var str_year = str.substr(0, 4);
                                    var str_month = str.substr(4, 2);
                                    var str_date = str.substr(6, 2);
                                    return str_year + '/' + str_month + '/' + str_date + ' : ' + params.value[1];
                                   /* var date = new Date(params.name);
                                    return date.getFullYear() + '/' + date.getMonth() + '/' + date.getDate() + ' : ' + params.value[1];*/
                                },
                                axisPointer: {
                                    animation: false
                                }
                            },
                            xAxis: {
                                type: 'time',
                                splitLine: {
                                    show: false
                                }
                            },
                            yAxis: {
                                type: 'value',
                                boundaryGap: [0, '100%'],
                                splitLine: {
                                    show: false
                                }
                            },
                            series: [{
                                name: '用户激活/注册数量',
                                type: 'line',
                                showSymbol: true,
                                hoverAnimation: false,
                                smooth: true,
                                data: data
                            }]
                        };
                        userRegChart.setOption(option);

                    }else{
                        option = {
                            title: {
                                text: '设备激活/注册数量',
                                x:'center'
                            },
                            tooltip: {
                                trigger: 'axis',
                                formatter: function (params) {
                                    params = params[0];
                                    var str = params.name;
                                    var str_year = str.substr(0, 4);
                                    var str_month = str.substr(4, 2);
                                    var str_date = str.substr(6, 2);
                                    return str_year + '/' + str_month + '/' + str_date + ' : ' + params.value[1];
                                    /*var date = new Date(params.name);
                                    return date.getFullYear() + '/' + date.getMonth() + '/' + date.getDate() + ' : ' + params.value[1];*/
                                },
                                axisPointer: {
                                    animation: false
                                }
                            },
                            xAxis: {
                                type: 'time',
                                splitLine: {
                                    show: false
                                }
                            },
                            yAxis: {
                                type: 'value',
                                boundaryGap: [0, '100%'],
                                splitLine: {
                                    show: false
                                }
                            },
                            series: [{
                                name: '设备激活/注册数量',
                                type: 'line',
                                showSymbol: true,
                                hoverAnimation: false,
                                smooth: true,
                                data: data
                            }]
                        };
                        userRegChart.setOption(option);
                    }

                } else {
                   /* alert(result.msg);*/
                }
            },function() {
                /*alert("服务器开个小差，请稍后重试！")*/
            });

        },

        //获取注册用户客户端类型的数据
        getClientTypeData : function() {
            var url = ctx + "xiaoyusvr/boss/report/dashboard";
            var data = {
                "organ_id":parseInt(companyId)
            };
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS && result.data != ""){
                    //客户端类型信息
                    action.myDevTypeQuery(JSON.stringify(result.data.type));

                    //地域信息
                    var data_arr1 = [];
                    var devProvinces = result.data.province;
                    for(items1 in devProvinces){
                        var name = items1;
                        var value = devProvinces[items1];
                        var list_item1 = '{"name":"'+name+'","value":'+value+'}';
                        data_arr1.push(list_item1);
                    }
                    var data_str1 = "["+data_arr1+"]";
                    var data1 = JSON.parse(data_str1);


                    //客户性别信息
                    var data_arr2 = [];
                    var devSexs = result.data.sex;
                    for(items2 in devSexs){
                        var name = items2;
                        var value = devSexs[items2];
                        var list_item2 = '{"name":"'+name+'","value":"'+value+'"}';
                        data_arr2.push(list_item2);
                    }
                    var data_str2 = "["+data_arr2+"]";
                    var data2 = JSON.parse(data_str2);


                    //用户地域分布
                    option1 = {
                        title: {
                            text: '用户地域分布',
                            x:'center'
                        },
                        tooltip: {
                            trigger: 'item'
                        },
                        visualMap: {
                            min: 0,
                            max: 2500,
                            left: 'left',
                            top: 'bottom',
                            text: ['高','低'], // 文本，默认为数值文本
                            calculable: true
                        },
                        toolbox: {
                            show: true,
                            orient: 'vertical',
                            left: 'right',
                            top: 'center',
                            feature: {
                                dataView: {readOnly: false},
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        series: [
                            {
                                name: '用户地域分布',
                                type: 'map',
                                mapType: 'china',
                                roam: false,
                                label: {
                                    normal: {
                                        show: false
                                    },
                                    emphasis: {
                                        show: false
                                    }
                                },
                                data:data1
                            }
                        ]
                    };
                    userAreaChart.setOption(option1);


                    //用户性别类型
                    option2 = {
                        title : {
                            text: '用户性别类型',
                            x:'center'
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        series : [
                            {
                                name: '用户性别类型',
                                type: 'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data: data2,
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
                    userSexChart.setOption(option2);

                } else {
                   /* alert(result.msg);*/
                }
            },function() {
                /*alert("服务器开个小差，请稍后重试！")*/
            });
        },
        myDevTypeQuery: function(nameParamArray){
            var devTypes = JSON.parse(nameParamArray);
            var data_arr = [];
            var nameArray = [];
            var countArray = [];
            var myTypeArray = [];
            for(items in devTypes){
                nameArray.push(items);
                countArray.push(devTypes[items]);
            }

            var url = ctx + "xiaoyusvr/boss/clienttype/queryclienttype";
            var data = new Object();
            data.client_type = null;
            data.client_name = "";
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS && result.data != ""){
                    var allDevTypeArray = result.data;
                    var Lens1 = nameArray.length;
                    var Lens2 = allDevTypeArray.length;
                    for(var i=0; i< Lens1; i++){
                        for(var j=0; j< Lens2; j++){
                            if(allDevTypeArray[j].client_type == nameArray[i]){
                                var typeName = allDevTypeArray[j].client_name;
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
                            text: '用户客户端类型',
                            x:'center'
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        series : [
                            {
                                name: '用户客户端类型',
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
                    /*alert(result.msg);*/
                }
            },function() {
                /*alert("服务器开个小差，请稍后重试！")*/
            });
        }
	};
	window.action = action;
    action.getUserDevData();
	action.getClientTypeData();

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