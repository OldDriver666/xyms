[TOC]

###阅读说明

####回包格式
```
数据传输采用json协议。回包通用格式如下，后续接口说明仅提供默认正常处理情况下返回的数据格式
{
    "code":INTEGER,    //错误码
    "msg":"STRING",    //错误提示
    "data":jsonObject  //返回的数据,一下所有接口回复说明里面就是该data字段内容,其他code 和code_msg未列出
}
```

####请求主机
```
http://192.168.2.196:8787/
```

###提问处理
####发布提问
|   接口地址    |   xiaoyusvr/problem/insert    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

####请求
可以上传图片文件
```
{
     "name":"",       //必填-用户名 
     "title":"",      //必填-话题
     "school_id":x,   //必填-用户学校id
     "content":""     //选填-提问内容
}
```
####回复
```
null 没有数据返回 看code是否成功
```

####查询问题
|   接口地址    |   xiaoyusvr/problem/queryall    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

####请求
```
采用分页查询
{
     "param":{
     	          "school_id":x      //必填-用户学校id	  
     		  },	
     "pageNo":x                      //选填-默认1     
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "page_no": 1,
      "page_size": 10,
      "total_count": 3,
      "total_page_count": 1,
      "param": null,
      "extra_param": null,
      "result": [
         {
            "id": 1,
            "name": "1",
            "title": "2",
            "content": "",
            "picture": "",
            "created": 2017,
            "answer_num": 0,
            "browse_num": 0
         },
         {
            "id": 5,
            "name": "3",
            "title": "4",
            "content": "",
            "picture": "",
            "created": 2016,
            "answer_num": 0,
            "browse_num": 0
         }
         ]
      }
}         
```

####图片查询
|   接口地址    |   xiaoyusvr/problem/picture    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

####请求
```
{
     "picture":""            //必填-图片路径url
}
```

####回复
```
返回一张图片
```

####话题查询
|   接口地址    |   xiaoyusvr/problem/titlequery    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              | 

####请求
```
{
     "param":{
                  "title":"",            //必填-模糊查询话题
                  "school_id":x          //必填-学校id
              },
     "pageNo":x                          //选填-默认1              
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "page_no": 1,
      "page_size": 10,
      "total_count": 2,
      "total_page_count": 1,
      "param": null,
      "extra_param": null,
      "result": [
         {
            "id": 5,
            "name": "3",
            "title": "bcd",
            "content": "",
            "picture": "",
            "created": 2016,
            "answer_num": 0,
            "browse_num": 0
         },
         {
            "id": 4,
            "name": "2",
            "title": "abc",
            "content": "",
            "picture": "",
            "created": 2015,
            "answer_num": 0,
            "browse_num": 0
         }
      ]
   }
}
```

####查询我的问题     获取更新的回答数量信息
|   接口地址    |   xiaoyusvr/problem/myproblem    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

####请求
```
{
     "param":{
                  "name":x            //必填-用户名
              },
     "pageNo":x                       //选填-默认1          
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "page_no": 1,
      "page_size": 10,
      "total_count": 1,
      "total_page_count": 1,
      "param": null,
      "extra_param": null,
      "result": [
         {
            "addAnswerCount": 0,
            "addBrowseCount": 0,
            "id": 9,
            "name": "5",
            "title": "试一试",
            "content": "sss",
            "picture": "",
            "status": 1,
            "created": 1502957592,
            "answer_num": 0,
            "browse_num": 0
         }
      ]
   }
}
```

####问题详情  会更新redis
|   接口地址    |   xiaoyusvr/problem/query    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |                 

####请求
```
{
     "problem_id":x,          //必填-问题id
     "name":""                //必填-用户名
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "id": 9,
      "name": "5",
      "title": "试一试",
      "content": "sss",
      "picture": "",
      "status": 1,
      "created": 1502957592,
      "answer_num": 0,
      "browse_num": 0
   }
}
```     

###回答系统
####添加回答
|   接口地址    |   xiaoyusvr/answer/insert    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

####请求
```
{
     "name":"",             //必填-用户名
     "problem_id":x,        //必填-问题id
     "content":""           //必填-内容
}
```
####回复
```
无回复内容，查看返回码
```

####查询我的回答
|   接口地址    |   xiaoyusvr/answer/querymy    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              | 

####请求
```
{
     "param":{
                  "name":""              //必填-用户名
             },
     "pageNo":x                          //选填-默认1         
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "page_no": 1,
      "page_size": 10,
      "total_count": 1,
      "total_page_count": 1,
      "param": null,
      "extra_param": null,
      "result": [
         {
            "addAgreeCount": 0,
            "addCommentCount": 0,
            "id": 1,
            "name": "1",
            "content": "22",
            "status": 1,
            "created": 0,
            "problem_id": 2,
            "agree_num": 0,
            "comment_num": 2
         }
      ]
   }
}
```

####查询问题的回答
|   接口地址    |   xiaoyusvr/answer/querybyid    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

####请求
```
{
     "page_no":"x",        //必填-查询当前页数     
     "problem_id":"x",     //必填-问题Id
     "order":"created"     //必填-created  表示按时间查询     agree_num   表示按热度查询 
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "page_no": 1,
      "page_size": 10,
      "total_count": 1,
      "total_page_count": 1,
      "param": null,
      "extra_param": null,
      "result": [
         {
            "id": 1,
            "name": "1",
            "content": "22",
            "created": 0,
            "problem_id": 2,
            "agree_num": 0,
            "comment_num": 0
         }
      ]
   }
}
```

####根据回答id，查询更新消息
|   接口地址    |   xiaoyusvr/answer/query    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              | 

####请求
```
{
     "answer_id":x,         //必填-回答id
     "name":""              //必填-用户名
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "id": 1,
      "name": "1",
      "content": "22",
      "status": 1,
      "created": 0,
      "problem_id": 2,
      "agree_num": 0,
      "comment_num": 2
   }
}
```     

####回答点赞
|   接口地址    |   xiaoyusvr/agree    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              | 

####请求
```
{
     "name":"",        //必填-用户名
     "answer_id":x     //必填-回答ID
}
```
####回复
```
msg  为已点赞或 已取消点赞
{
   "code": 0,
   "msg": "已取消点赞",
   "data": null
}
```

####点击关注
|   接口地址    |   xiaoyusvr/concern/change    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |  

####请求
```
{
     "name":"",            //必填-用户名
     "problem_id":x        //必填-问题ID
}
```
####回复
```
msg  为已关注或已取消关注
{
   "code": 0,
   "msg": "已关注",
   "data": null
}
```

####查询关注
|   接口地址    |   xiaoyusvr/concern/isconcern    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

####请求
```
{
     "name":"",       //必填-用户名
     "problem_id":x   //必填-问题ID
}
```
####回复
```
msg 为已关注或未关注
{
   "code": 0,
   "msg": "已关注",
   "data": null
}
```

####查询用户关注问题
|   接口地址    |   xiaoyusvr/concern/queryconcerns    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              | 

####请求
```
{
     "name":""         //必填-用户名
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "page_no": 1,
      "page_size": 10,
      "total_count": 1,
      "total_page_count": 1,
      "param": null,
      "extra_param": null,
      "result": [
         {
            "addAnswerCount": 0,
            "addBrowseCount": 0,
            "id": 2,
            "name": "2",
            "title": "abc",
            "content": "",
            "picture": "",
            "status": 1,
            "created": 2015,
            "answer_num": 1,
            "browse_num": 0
         }
      ]
   }
}
```

####根据问题ID，查询关注问题详情
|   接口地址    |   xiaoyusvr/concern/query    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              | 

####请求
```
{
     "problem_id":"",            //必填-问题id
     "name":""                   //必填-用户名
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "id": 2,
      "name": "2",
      "title": "abc",
      "content": "",
      "picture": "",
      "status": 1,
      "created": 2015,
      "answer_num": 1,
      "browse_num": 0
   }
}
```     

####添加评论
|   接口地址    |   xiaoyusvr/comment/add    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              | 

####请求
```
{
     "from_name":"",          //必填-用户名
     "to_name":"",            //选填-回复人用户名
     "answer_id":x,           //必填-回答id
     "comment_id":x,          //选填-评论id
     "problem_id":x,          //必填-问题id
     "content":""             //必填-内容
}
```
####回复
```
无返回信息  查看返回码
```

####查询评论
|   接口地址    |   xiaoyusvr/comment/querycomment    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              | 

####请求
```
{
     "param":{
                  "answer_id":x        //必填-回答id
              },
     "pageNo":x                        //选填-默认1          
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "page_no": 1,
      "page_size": 10,
      "total_count": 3,
      "total_page_count": 1,
      "param": null,
      "extra_param": null,
      "result": [
         {
            "id": 4,
            "content": "go",
            "status": 1,
            "updated": 1503047436,
            "created": 1503047436,
            "from_name": "22",
            "to_name": "",
            "answer_id": 1,
            "comment_id": 0,
            "problem_id": 2
         },
         {
            "id": 2,
            "content": "测试",
            "status": 1,
            "updated": 1502962563,
            "created": 1502962563,
            "from_name": "wo",
            "to_name": "",
            "answer_id": 1,
            "comment_id": 4,
            "problem_id": 2
         },
         {
            "id": 3,
            "content": "1",
            "status": 1,
            "updated": 0,
            "created": 0,
            "from_name": "b",
            "to_name": "c",
            "answer_id": 1,
            "comment_id": 2,
            "problem_id": 2
         }
      ]
   }
}
```

####查询我的评论
|   接口地址    |   xiaoyusvr/comment/querymy    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |    

####请求
```
{
     "param":{
                  "from_name":""       //必填-用户名
             },
     "page_no":x                       //选填-当前页
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "page_no": 1,
      "page_size": 10,
      "total_count": 1,
      "total_page_count": 1,
      "param": null,
      "extra_param": null,
      "result": [
         {
            "addreply": 0,
            "id": 4,
            "content": "go",
            "status": 1,
            "updated": 1503047436,
            "created": 1503047436,
            "from_name": "22",
            "to_name": "",
            "answer_id": 1,
            "comment_id": 0,
            "problem_id": 2,
            "count":100
         }
      ]
   }
}
```

####查询某评论下的评论
|   接口地址    |   xiaoyusvr/comment/query    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

####请求
```
{
     "comment_id":x,             //必填-评论id
     "page_no":x,                //必填-当前页面数
     "fromname":""               //必填-用户名
}
```

####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "page_no": 1,
      "page_size": 20,
      "total_count": 1,
      "total_page_count": 1,
      "param": null,
      "extra_param": null,
      "result": [
         {
            "id": 2,
            "content": "测试",
            "status": 1,
            "updated": 1502962563,
            "created": 1502962563,
            "from_name": "wo",
            "to_name": "",
            "answer_id": 1,
            "comment_id": 4,
            "problem_id": 2
         }
      ]
   }
}
```

####根据id查询评论
|   接口地址    |   xiaoyusvr/comment/querybyid    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

####请求
```
{
     "comment_id":x          //必填-评论id
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "id": 2,
      "content": "测试",
      "status": 1,
      "updated": 1502962563,
      "created": 1502962563,
      "from_name": "wo",
      "to_name": "",
      "answer_id": 1,
      "comment_id": 4,
      "problem_id": 2
   }
}
```

####用户信息查询
|   接口地址    |   xiaoyusvr/user/query    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

####请求
```
{
     "name":""            //必填-用户名
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "id": 2,
      "sex": true,
      "name": "2",
      "domain": "user2",
      "nick": "2",
      "password": "800db50a2241bcf4f681ff577864dd1e",
      "salt": "1736",
      "province": "北京",
      "city": "北京",
      "country": "中国",
      "phone": "",
      "email": "",
      "avatar": "g0/default/default.png",
      "height": 0,
      "weight": 0,
      "birthday": "0",
      "type": 18,
      "departid": 3,
      "status": 0,
      "created": 1492676615,
      "updated": 1490941999,
      "pushShieldStatus": false,
      "signInfo": "b",
      "lng": "113.991055",
      "lat": "22.681686",
      "battery": 39,
      "sq": 0,
      "friendNeedAuth": true,
      "loginSafeSwitch": false,
      "searchAllow": true,
      "onlineStatus": false,
      "lastOnlineTime": 1499150910
   }
}
```

####查询学校 
|   接口地址    |   xiaoyusvr/school/queryschool    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

####请求
```
{
     "school_id":x          //必填-学校id
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "updated": 1503652816,
      "created": 1503652816,
      "school_id": 1352,
      "school_name": "北京市丰台区丰台第二中学",
      "district_id": 1,
      "school_addr": ""
   }
}
```                                                                                                