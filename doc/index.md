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
     "user_id":x,       //必填-用户id 
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
     "page_no":x                      //选填-默认1     
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
            "id": 23,
            "user_id":x,
            "nick": "3",
            "avatar": "g0/default/default.png",
            "schoolname": "北京市延庆县第一中学",
            "title": "dd",
            "content": "ff",
            "picture": "",
            "status": 1,
            "updated": 1503903049,
            "created": 1503385377,
            "answer_num": 6,
            "browse_num": 1631,
            "school_id": 353
         },
         {
            "id": 22,
            "user_id":x
            "nick": "3",
            "avatar": "g0/default/default.png",
            "schoolname": "北京市延庆县第一中学",
            "title": "sdfdsf",
            "content": "ddddddddddddddddddddddd",
            "picture": "",
            "status": 1,
            "updated": 1503903049,
            "created": 1503381289,
            "answer_num": 6,
            "browse_num": 1085,
            "school_id": 353
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
     "page_no":x                          //选填-默认1              
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
            "user_id":x,
            "title": "bcd",
            "content": "",
            "picture": "",
            "created": 2016,
            "answer_num": 0,
            "browse_num": 0
         },
         {
            "id": 4,
            "user_id":x,
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
                  "user_id":x            //必填-用户id
              },
     "page_no":x                       //选填-默认1          
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
            "addBrowseCount": 293,
            "id": 33,
            "user_id":x,
            "nick": "3",
            "avatar": "g0/default/default.png",
            "schoolname": null,
            "title": "good",
            "content": "very good very good",
            "picture": "",
            "status": 1,
            "updated": 1503903049,
            "created": 1503655218,
            "answer_num": 0,
            "browse_num": 724,
            "school_id": 0
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
     "user_id":x                //必填-用户id
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "addAnswerCount": 0,           //没有意义
      "addBrowseCount": 0,           //没有意义
      "id": 5,
      "user_id":x,
      "nick": "3",
      "avatar": "g0/default/default.png",
      "schoolname": null,
      "title": "bcd",
      "content": "",
      "picture": "",
      "status": 1,
      "updated": 1503571247,
      "created": 2016,
      "answer_num": 0,
      "browse_num": 66,
      "school_id": 353
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
     "user_id":x,             //必填-用户id
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
                  "user_id":x              //必填-用户id
             },
     "page_no":x                          //选填-默认1         
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
            "id": 33,
            "user_id":x,
            "nick": "3",
            "avatar": "g0/default/default.png",
            "content": "阿里郎",
            "status": 1,
            "created": 1503634945,
            "problem_id": 32,
            "agree_num": 1,
            "comment_num": 4
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
            "addAgreeCount": 0,
            "addCommentCount": 0,
            "id": 9,
            "user_id":x,
            "nick": "3",
            "avatar": "g0/default/default.png",
            "content": "df",
            "status": 1,
            "created": 1503452962,
            "problem_id": 22,
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
     "user_id":x              //必填-用户id
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
     "user_id":x,        //必填-用户id
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
     "user_id":x,            //必填-用户名
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
     "user_id":x,       //必填-用户id
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
     "user_id":x         //必填-用户id
     "page_no":x         //必填-页面数  
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
            "addAnswerCount": 3,
            "addBrowseCount": 3,
            "id": 20,
            "user_id":x,
            "nick": "3",
            "avatar": "g0/default/default.png",
            "schoolname": null,
            "title": "5555555555555",
            "content": "555555555555555555555555555555555555555555555gddddddd",
            "picture": "",
            "status": 1,
            "updated": null,
            "created": 1503374009,
            "answer_num": 63,
            "browse_num": 857,
            "school_id": null
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
     "id":x,                       //必填-问题id
     "user_id":x                   //必填-用户id
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "addAnswerCount": 0,
      "addBrowseCount": 0,
      "id": 22,
      "user_id":x,
      "nick": "3",
      "avatar": "g0/default/default.png",
      "schoolname": null,
      "title": "sdfdsf",
      "content": "ddddddddddddddddddddddd",
      "picture": "",
      "status": 1,
      "updated": null,
      "created": 1503381289,
      "answer_num": 6,
      "browse_num": 1090,
      "school_id": null
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
     "from_userid":"",          //必填-用户id
     "to_userid":"",            //选填-回复人用户id
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
     "page_no":x                        //选填-默认1          
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
            "addreply": 0,
            "count": 0,
            "id": 167,
            "fromNick": "3",
            "toNick": "3",
            "content": "ddddddddddddd",
            "status": 1,
            "updated": 1503655279,
            "created": 1503655279,
            "from_userid": 3,
            "to_userid": 3,
            "answer_id": 17,
            "comment_id": 166,
            "problem_id": 20
         },
         {
            "addreply": 0,
            "count": 0,
            "id": 166,
            "fromNick": "3",
            "toNick": "3",
            "content": "dddddddddddd",
            "status": 1,
            "updated": 1503655270,
            "created": 1503655270,
            "from_userid": 3,
            "to_userid": 3,
            "answer_id": 17,
            "comment_id": 115,
            "problem_id": 20
         },
         {
            "addreply": 0,
            "count": 0,
            "id": 151,
            "fromNick": "3",
            "toNick": "3",
            "content": "fffffffffffffffff44444444444",
            "status": 1,
            "updated": 1503654411,
            "created": 1503654411,
            "from_userid": 3,
            "to_userid": 3,
            "answer_id": 17,
            "comment_id": 148,
            "problem_id": 20
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
                  "from_userid":""       //必填-用户id
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
            "count": 0,
            "id": 167,
            "fromNick": "3",
            "toNick": "3",
            "content": "ddddddddddddd",
            "status": 1,
            "updated": 1503655279,
            "created": 1503655279,
            "from_userid": 3,
            "to_userid": 3,
            "answer_id": 17,
            "comment_id": 166,
            "problem_id": 20
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
     "id":x,                     //必填-评论id
     "page_no":x,                //必填-当前页面数
     "from_userid":x             //必填-用户id
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
            "addreply": 0,
            "count": 0,
            "id": 37,
            "fromNick": "3",
            "toNick": "3",
            "content": "1",
            "status": 1,
            "updated": 1503545860,
            "created": 1503545860,
            "from_userid": 3,
            "to_userid": 3,
            "answer_id": 22,
            "comment_id": 28,
            "problem_id": 25
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
     "id":x          //必填-评论id
}
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": {
      "addreply": 0,
      "count": 0,
      "id": 5,
      "fromNick": "3",
      "toNick": "3",
      "content": "1",
      "status": 1,
      "updated": 1503483792,
      "created": 1503483792,
      "from_userid": 3,
      "to_userid": 3,
      "answer_id": 17,
      "comment_id": 0,
      "problem_id": 20
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
     "user_id":x            //必填-用户id
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
     "school_id":[x,x...]          //必填-学校id
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
         "school_id": 363,
         "school_name": "灯市口小学",
         "district_id": 1,
         "school_addr": ""
   }
}
```

####上传图片 
|   接口地址    |   xiaoyusvr/file/fileupload    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

####请求
```
上传图片
```
####回复
```
{
     "code": 0,
     "msg": "ok",  
     "data":{
     		     "pictureURL":""
             }
}                                                                                                           