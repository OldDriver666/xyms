$(function(){
    var companyId = Util.cookieStorage.getCookie("companyId");

    //页面加载左侧Menu菜单栏
	var Index = {
        myDevTypeQuery: function(){
            var dataArray1 = [];
            var dataArray2 = [];
            var myDevTypeArray = [];
            var url = ctx + "xiaoyusvr/boss/clienttype/queryclienttype";
            var moduleId = 0;
            var data = new Object();
            data.client_type = null;
            data.client_name = "";
            Util.ajaxLoadData(url,data,moduleId,"POST",true,function(result) {
                if(result.code == ReturnCode.SUCCESS && result.data != ""){
                    dataArray1 = result.data;

                    var url_query = ctx + "xiaoyusvr/boss/departconf/queryimdepartconfig";
                    var moduleId_query = 0;
                    var data_query = new Object();
                    data_query.depart_id = parseInt(companyId);
                    data_query.client_type = null;
                    Util.ajaxLoadData(url_query,data_query,moduleId_query,"POST",true,function(result_query) {
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
                        }
                    },function() {
                    });

                } else {
                }
            },function() {
            });

        }
	};
    Index.myDevTypeQuery();
});

