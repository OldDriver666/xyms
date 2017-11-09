[TOC]

### 问答阅读说明

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
http://192.168.2.250:8787/
```


### 问答系统

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
     	          "user_id":x        //必填-当前登录用户id
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
     "user_id":"x",        //必填-登录用户ID
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
      "status": 1,              //1表示已点赞   0表示未点赞
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
                  "id":x               //必填-登录用户ID
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
``` 

####上传图片 
|   接口地址    |   xiaoyusvr/file/filedown    |
|   ---         |   ---          |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

####请求
```
{
	"filedown":""         //必填-图片的名字
}
```
####回复
```
{
     "code": 0,
     "msg": "ok",  
     "data":null
}

### 后台管理阅读说明
#### 请求格式
```
接口分为需要鉴权的和不需要鉴权两类，除了登录接口只要都需要鉴权，鉴权规则如下，请求头中必须包含如下值：
FISE-UA：platform|system|udid|userid|version
//使用|分隔的字符串其中userid为登录成功之后得到的用户id,其他字段酌情填写不可不填,目前阶段强校验userid字段
FISE-AccessToken:dfdd2a47b176443ba7d062307248e25a
//登录成功之后返回的accessToken值，该值和UA中的userid 唯一对应且强关系校验
```
#### 回包格式
```
数据传输采用json协议。回包通用格式如下,后续接口说明仅提供默认正常处理情况下返回的数据格式
{
    "code":INTEGER,      //错误码
    "msg":"STRING", //错误提示
    "data":JsonObject,   //返回的数据,一下所有接口回复说明里面就是该data字段内容,其他code 和code_msg未列出
}
```
#### 请求主机
```
http://boss.fise-wi.com
```

### 小雨后台管理系统

####管理员登录
|   接口地址    |   xiaoyusvr/boss/admin/login         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

09-07 新增home字段为该管理员登录成功后默认打开的主页

```
//请求
{
    "account":"#userphone#",
    "password":"#md5#"
}
//回复
{
      "accessToken": "5282b69acfdf495aa07db874473a6659",
      "account": "Administrator",
      "companyId": 1,
      "departId": 0,
      "id": 2,
      "nickName": "Administrator",
      "phone": "",
      "home": "index.html"
}
``` 

####管理员退出
|   接口地址    |   xiaoyusvr/boss/admin/logout         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

```
//请求
{
    "admin_id":x    // 这个值是登陆中返回的id获字段值
}

//回复
看code是否成功
```

####实现单点登录
|   接口地址    |   xiaoyusvr/boss/admin/islogin         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

```
//请求
{
    "accessToken":""     //必填-管理员的accessToken
}
  
//回复
看code是否成功
```       

####新增管理员
|   接口地址    |   xiaoyusvr/boss/admin/insert         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

```
//请求
{
    "admin_id":X,       //必填-这个值是登陆中返回的id获字段值-调用者id
    "account":"",       //必填-新增管理员账号
    "password":"",      //必填-密码
    "nick_name":"",     //昵称
    "role_id":x,        //必填-角色
    "phone":"",
    "email":"",
    "company_id":x      //必填-组织id
    "depart_id":x       //选填-部门ID
}

//回复
{
   "code": 0,
   "msg": "ok",
   "data": {
      "id": null,
      "account": "18271681841",
      "salt": "9458",
      "password": "123456",
      "nickName": "熊猫",
      "roleId": 3,
      "companyId": 1,
      "departId": 0,
      "phone": "",
      "email": "",
      "accessToken": "",
      "status": 1,
      "lastLogin": 0,
      "created": 1505894583,
      "updated": 1505894583
   }
}
```  

####管理员查询
|   接口地址    |   xiaoyusvr/boss/admin/query         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST         |
|   参数格式    |   JSON              |

```
//请求
{
    "admin_id":X,       //必填-这个值是登陆中返回的id获字段值-调用者id
    "account":"",       //管理员账号
    "role_id":x,        //角色
    "company_id":x      //公司组织id
}

//回复
[
    {
        "id": 1,
        "account": "18601735176",
        "salt": "3678",
        "password": "",
        "nickName": "廖国顺",
        "roleId": 3,
        "companyId": 1,
        "departId":x,
        "phone": "",
        "email": "",
        "accessToken": "",
        "status": 1,
        "lastLogin": 1498122594,
        "created": 1225404661,
        "updated": 1498547576
    },
    {
        "id": 3,
        "account": "chenzhongchao",
        "salt": "1540",
        "password": "",
        "nickName": "陈钟超",
        "roleId": 3,
        "departId":x,
        "companyId": 1,
        "phone": "",
        "email": "",
        "accessToken": null,
        "status": 1,
        "lastLogin": null,
        "created": 1496315408,
        "updated": 1496396165
    }
]
``` 

####管理员修改
|   接口地址    |   xiaoyusvr/boss/admin/update         |
|   ---         |   ---                   |

```
//请求
{
    "login_id":x,       //必填-登录者id
    "admin_id":X,       //必填-需要修改者id
    "account":"",       //增管理员账号
    "password":"",      //密码
    "nick_name":"",     //昵称
    "status":1,         //0-不可用，1-可用  2-删除 
    "role_id":x,        //角色
    "organization_id":x,//公司组织id
    "phone":"",
    "email":""
}

//回复
null 没有数据返回 看code是否成功
```

####新增角色
|   接口地址    |   xiaoyusvr/boss/role/insert        |
|   ---         |   ---                   |

```
//请求
{
 "role_level":x,                        //必填-角色权值
 "role_name":"",                        //必填-角色名称
 "company_id":x                         //必填-公司id
 "desc":"",                             //选填-角色描述
 "depart_id":X                          //选填-角色部门
}

//回复
无内容，直接查看返回码
```  

####查询角色
|   接口地址    |   xiaoyusvr/boss/role/query        |
|   ---         |   ---                   |

```
//请求
{
 "role_id":x,                 //必填-自己角色
 "company_id":x               //必填-自己公司
}

//回复
[
      {
         "id": 1,
         "authLevel": 999,
         "name": "BOSS",
         "description": "沸石智能管理员账号",
         "organizationId": 1
      },
      {
         "id": 2,
         "authLevel": 800,
         "name": "超级管理员",
         "description": "合作公司管理员账号",
         "organizationId": 1
      },
      {
         "id": 3,
         "authLevel": 700,
         "name": "管理员",
         "description": "管理员账号",
         "organizationId": 1
      }
]
```  

####修改角色
|   接口地址    |   xiaoyusvr/boss/role/update        |
|   ---         |   ---                   |

```
//请求
{
 "id":x                                 //必填-从role/query返回数据中的id字段值
 "role_level":x,                        //选填-角色权值
 "role_name":"",                        //选填-角色名称
 "company_id":x                         //选填-公司id
 "desc":"",                             //选填-角色描述
 "depart_id":X                          //选填-角色部门
}

//回复
无内容，直接查看返回码
```             

####查询角色权限
|   接口地址    |   xiaoyusvr/boss/role/queryAuth        |
|   ---         |   ---                   |

```
//请求
{
 "role_id":x,       //必填-自己角色
 "company_id":x     //必填-自己公司
 "include_all":x    //选填-0或者不传:自己的权限 1:所有权限/用于上级查询管理下级权限
}

//回复
[
      {
         "module_id": 2,
         "module_name": "Broadcast",
         "url": "manage/main/main.html",
         "module_type": 0,
         "priority": 2300,
         "parent_id": 0,
         "status": 0,
         "permiss_id": 16,
         "insert_auth": 1,
         "update_auth": 1,
         "query_auth": 1
      },
      {
         "module_id": 3,
         "module_name": "设备管理",
         "url": "",
         "module_type": 0,
         "priority": 1900,
         "parent_id": 0,
         "status": 1,
         "permiss_id": 88,
         "insert_auth": 1,
         "update_auth": 1,
         "query_auth": 1
      }
]
``` 

####修改角色权限
|   接口地址    |   xiaoyusvr/boss/role/updateAuth        |
|   ---         |   ---                   |

```
//请求
{
    "key_id":x,                    //必填 角色权限ID 从接口queryAuth返回中的permiss_id字段值
    "status":x,                    //选填-1-可见 0-不可见
    "insert_auth":x,               //选填-新增权限
    "update_auth":x,               //选填-更新权限
    "query_auth":x                 //选填-查询可见权限
}

//回复
无回复 看结果
```

####添加角色权限
|   接口地址    |   xiaoyusvr/boss/role/insertAuth        |
|   ---         |   ---                   |  

```
//请求
{
    "role_id":x,                   //必填 权限ID 
    "module_id":x,                 //必填-模块ID
    "company_id":x,                //必填-公司ID
    "status":x,                    //必填-1-可见 0-不可见
    "insert_auth":x,               //必填-权限
    "update_auth":x,               //必填-权限
    "query_auth":x                 //必填-权限
}

//回复
无回复 看结果
```

####获取菜单列表
|   接口地址    |   xiaoyusvr/boss/module/query         |
|   ---         |   ---                   |

```
//请求
{
    "company_id":x -必填
}
以上值从登陆中获取

//回复
 [
      {
         "id": 2,
         "name": "设备管理",
         "moduleType": 0,
         "belongCompany": 1,
         "description": "",
         "priority": 1900,
         "status": 1,
         "sn": "shebeiguanli",
         "url": "",
         "parentId": 0
      },
      {
         "id": 4,
         "name": "系统管理",
         "moduleType": 0,
         "belongCompany": 1,
         "description": "",
         "priority": 1700,
         "status": 1,
         "sn": "xitongguanli",
         "url": "",
         "parentId": 0
      }
]
``` 

####新增菜单
|   接口地址    |   xiaoyusvr/boss/module/insert         |
|   ---         |   ---                   |


```
//请求
{
    "name":"title",        //必填-菜单名称
    "company_id":x,        //必填-公司id
    "description":"",      //选填-菜单描述说明
    "priority":x,          //必填-菜单权限决定排序位置
    "sn":"desc",           //必填-菜单名称的全拼字符串
    "url":"",              //选填-是否需要配置跳转的url
    "module_type":x        //选填-模块类型
    "parent_id":x          //必填-菜单父节点，如果是顶级菜单填写0
    "status":1             //选填-1 可用   0  不可用
}
```
#####回复
```
无内容，直接查看返回码
```           

####修改菜单
|   接口地址    |   xiaoyusvr/boss/module/update         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
    "admin_id":x,          //必填-登陆管理员ID 
    "module_id":x,         //必填-module主键id
    "company_id":x,        //必填-公司id
    "name":"title",        //必填-菜单名称
    "description":"",      //选填-菜单描述说明
    "priority":x,          //必填-菜单权限决定排序位置
    "sn":"desc",           //必填-菜单名称的全拼字符串
    "status":x,            //选填-0或者1,1表示新增后可可见 0-不可见
    "url":"",              //选填-是否需要配置跳转的url
    "parent_id":x          //必填-菜单父节点，如果是顶级菜单填写0
} 
```
#####回复
```
无内容，直接查看返回码
```         

####删除菜单
|   接口地址    |   xiaoyusvr/boss/module/delete         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
 "module_id":x         //必填-module主键id
} 
```
#####回复
```
无内容，直接查看返回码
```     

####新增系统设置
|   接口地址    |   xiaoyusvr/boss/systemconf/addsystemconf         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
    "type":"x",            //必填-配置类型
    "name":"",             //必填-配置名称 
    "value":"",            //选填-配置具体值
    "action":"",           //选填-配置目标action
    "status":1,            //选填-配置状态   0-弃用  1-启用  
    "parent_id":0          //选填-菜单父节点，如果是顶级菜单填写0
}
```
#####回复
```
无内容，直接查看返回码
```            

####查询系统设置
|   接口地址    |   xiaoyusvr/boss/systemconf/querysystemconf         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
type和name都是选填，如果都不填，则查询所有信息
{
    "type":"",           //选填-配置类型 
    "name":""            //选填-配置名称  
}
```
#####回复
```
返回一个系统设置信息
{
    "config_id":x，
    "type":"ios",
    "name":"version",
    "value":"1.0.3",
    "action":"",
    "status":1,
    "parent_id":0,
    "updated":0,
    "created":0
}
```     

####删除系统设置
|   接口地址    |   xiaoyusvr/boss/systemconf/delsystemconf         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |   

#####请求
```
{
    "config_id":x           //必填-配置id 
}
```
#####回复
```
无内容，直接查看返回码
```          

####修改系统设置
|   接口地址    |   xiaoyusvr/boss/systemconf/updatesystemconf         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 
  
#####请求
```
{
    "config_id":x,            //必填-配置id 
    "type":"",                //选填-配置类型  
    "name":"",                //选填-配置名称
    "value":"",               //选填-配置具体值
    "action":"",              //选填-配置目标action
    "status":1,               //选填-配置状态  0-弃用 1-启用
    "parent_id":x             //选填-菜单父节点，如果是顶级菜单填写0
}
```
#####回复
```
无内容，直接查看返回码
```              

####新增设备信息
|   接口地址    |   xiaoyusvr/boss/fisedevice/addfisedevice         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
    "ime":"x"，                                                      //必填-设备IME号
    "account":"",               //必填-小位号-账号
    "depart_id":x,              //必填-公司/团体ID
    "type":x,                   //必填-设备类型 
    "mobile":"",                //选填-手机号
    "mark":""                   //选填-备注信息  
}
```
#####回复
```
无内容，直接查看返回码
```      

####新增设备信息
|   接口地址    |   xiaoyusvr/boss/fisedevice/excel_import         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |  
  
#####请求
```
上传Excel的文档
```
#####回复
```
无内容，直接查看返回码
```  

####查询设备信息(分页查询)
|   接口地址    |   xiaoyusvr/boss/fisedevice/queryfisedevice         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
    "page_no":x,                            //选填-当前页, 默认为第1页
    "page_size":x,                          //选填-每页记录数，默认20
    "param":
    {
        "ime":"",                      //选填-设备IME号
        "account":"",                 //选填-小位号-账号 
        "depart_id":x,                 //必填-公司/团体ID
        "status":x                     //选填-设备状态   0-出厂 1-激活  
    }
}
```
#####回复
```
{
    "page_no": 1,
    "page_size": 20,
    "total_count": 89,
    "total_page_count": 5,
    "param": null,
    "extra_param": null,
    "result": [
        {
            "ime": "135790246811229",
            "mac": "49:04:22:f8:62:66",
            "code": "E5217ED8D7D4B40AF34FE02905CC39EC",
            "status": true,
            "account": "test_dev8",
            "type": 19,
            "mobile": "test_dev8",
            "mark": "",
            "updated": 1480406086,
            "created": 1480406086,
            "fise_id": 9,
            "depart_id": 1
        },
        {
            "ime": "135790246811230",
            "mac": "",
            "code": "",
            "status": true,
            "account": "test_dev9",
            "type": 20,
            "mobile": "test_dev9",
            "mark": "",
            "updated": 0,
            "created": 0,
            "fise_id": 15,
            "depart_id": 1
        },
        {
            "ime": "135790246811231",
            "mac": "",
            "code": "",
            "status": true,
            "account": "test_dev10",
            "type": 20,
            "mobile": "test_dev10",
            "mark": "",
            "updated": 0,
            "created": 0,
            "fise_id": 16,
            "depart_id": 1
        }
    ]
}
```

####删除设备信息
|   接口地址    |   xiaoyusvr/boss/fisedevice/delfisedevice         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |  

#####请求
```
{
    "fise_id":x    //必填-设备ID
}
```
#####回复
```
无内容，直接查看返回码
```

####修改设备信息
|   接口地址    |   xiaoyusvr/boss/fisedevice/updatefisedevice         |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
    "fise_id":x,                //必填-设备ID
    "ime":"x"，                                                     //选填-设备IME号
    "status":0,                 //选填-设备状态  0-出厂 1-激活 2-删除
    "account":"",               //必填-小位号-账号
    "depart_id":x,              //选填-公司/团体ID
    "type":x,                   //选填-设备类型 
    "mobile":"",                //选填-手机号
    "mark":""                   //选填-备注信息   	
}
```
#####回复
```
无内容，直接查看返回码
```  

####设备激活情况统计
|   接口地址    |   xiaoyusvr/boss/devicecount        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
    "depart_id":x //必填-公司/团体ID
}
```
#####回复
```
{
    "onlineDevice":8,                //在线的设备数
    "activeDevice":38,               //激活的设备数
    "onlineXM":2,                    //在线的小位
    "activeXM":70                    //激活的小位
}
```

####新增配置
|   接口地址    |   xiaoyusvr/boss/departconf/addimdepartconfig        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
    "depart_id":x,                   //必填-公司/团体ID  
    "client_type":x,                 //必填-设备类型
    "avatar":""                      //选填-默认头像
}
```
#####回复
```
无内容，直接查看返回码
```

####查询部门配置 
|   接口地址    |   xiaoyusvr/boss/departconf/queryimdepartconfig        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
都不填则全部查询
{
    "depart_id":x,                   //选填-公司/团体ID  
    "client_type":x                  //选填-设备类型
}
```
#####回复
```
[
    {
      "config_id":x,                   
      "depart_id":x,
      "client_type":x,
      "avatar":"",
      "created":x
    },
    {
      "config_id":x,                   
      "depart_id":x,
      "client_type":x,
      "avatar":"",
      "created":x
    }
] 
```  

####删除配置 
|   接口地址    |   xiaoyusvr/boss/departconf/delimdepartconfig        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
    "config_id":x    //必填-公司设备配置ID 
}
```
#####回复
```
无内容，直接查看返回码
```

####修改配置 
|   接口地址    |   xiaoyusvr/boss/departconf/updateimdepartconfig        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
    "config_id":x                    //必填-公司设备配置ID
    "depart_id":x,                   //选填-公司/团体ID  
    "client_type":x,                 //选填-设备类型
    "avatar":""                      //选填-默认头像
} 
```
#####回复
```
无内容，直接查看返回码
```

####添加配置
|   接口地址    |   xiaoyusvr/boss/clienttype/addclienttype        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
 "client_type":x,                   //必填-设备类型
 "client_name":""                   //必填-设备类型名称
}
```
#####回复
```
无内容，直接查看返回码
```

####查询配置
|   接口地址    |   xiaoyusvr/boss/clienttype/queryclienttype        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
都不填则全部查询
{
 "client_type":x,                   //选填-设备类型
 "client_name":""                   //选填-设备类型名称
}
```
#####回复
```
[
    {
        "type_id":x,
        "client_type":x,
        "client_name":"",
        "created":x
    },
    {
        "type_id":x,
        "client_type":x,
        "client_name":"",
        "created":x
    }
]
```

####删除配置
|   接口地址    |   xiaoyusvr/boss/clienttype/delclienttype        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
    "type_id":x    //必填-配置ID
}
```
#####回复
```
无内容，直接查看返回码
```

####修改配置
|   接口地址    |   xiaoyusvr/boss/clienttype/updateclienttype        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
 "type_id":x,                     //必填-配置ID
 "client_type":x,                 //选填-设备类型
 "client_name":""                 //选填-设备类型名称
}
```
#####回复
```
无内容，直接查看返回码
```

####设备事件查询(分页查询)
|   接口地址    |   xiaoyusvr/boss/event/query        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |  

#####请求
```
{
    "page_no":x,                            //选填-当前页, 默认为第1页
    "page_size":x,                          //选填-每页记录数，默认20 
    "param":
    {
        "admin_id":x,                  //必填-管理员ID
        "account":""	                 //必填-设备拼音
    }
}
``` 
#####回复
```
{
    "page_no": 3,
    "page_size": 20,
    "total_count": 388,
    "total_page_count": 20,
    "param": null,
    "extra_param": null,
    "result": [
        {
        "id": 352,
        "userId": 105392,
        "eventKey": 0,
        "locationX": "22.6850304676964",
        "locationY": "113.985455450724",
        "locationFrom": 2,
        "battery": 1,
        "sq": 4,
        "eventLevel": 2,
        "param": "",
        "updated": 1496476310,
        "created": 1496476310
        },
        {
        "id": 351,
        "userId": 105392,
        "eventKey": 9,
        "locationX": "",
        "locationY": "",
        "locationFrom": 0,
        "battery": 0,
        "sq": 0,
        "eventLevel": 1,
        "param": "",
        "updated": 1496472540,
        "created": 1496472540
        }
    ]
}
```

####添加设备新版本
|   接口地址    |   xiaoyusvr/boss/deviceversion/add        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                |         

#####请求
```
{
 "depart_id":x,                     //必填-公司ID
 "dev_type":x,                     //必填-设备类型
 "dev_version":"",                 //必填-最新设备固件版本号
 "update_url":"",                  //必填-更新下载地址
 "version_info":""                 //选填-版本信息
}
```
#####回复
```
无内容，直接查看返回码
```

####查询设备新版本
|   接口地址    |   xiaoyusvr/boss/deviceversion/query        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
 "depart_id":x,                    //必填-公司ID
 "dev_type":x                      //选填-设备类型
}
```
#####回复
```
[
    {
    "version_id":x,
    "depart_id":x,
    "dev_type":x,
    "dev_version":"",
    "status":0,
    "version_info":"",
    "update_url":""
    },
    {
    "version_id":x,
    "depart_id":x,
    "dev_type":x,
    "dev_version":"",
    "status":0,
    "version_info":"",
    "update_url":""
    }
]
```

####删除设备新版本
|   接口地址    |   xiaoyusvr/boss/deviceversion/del        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
    "version_id":x                  //必填-设备版本ID
}
```
#####回复
```
无内容，直接查看返回码
``` 

####修改设备新版本
|   接口地址    |   xiaoyusvr/boss/deviceversion/update        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |  

#####请求
```
{
 "version_id":x,                  //必填-设备版本ID
 "dev_version":"",				  //选填-最新设备固件版本号	
 "status":0,                      //选填-0 不可用    1 可用
 "version_info":"",               //选填-版本信息
 "update_url":""                  //选填-更新下载地址
} 
```
#####回复
```
无内容，直接查看返回码
```

####新增用户意见
|   接口地址    |   xiaoyusvr/boss/suggest/add        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |  

#####请求
```
{
 "user_id":x,                     //必填-用户id
 "uname":"",                      //必填-用户名
 "suggestion":"",                 //选填-意见内容 
 "contact":""                     //选填-联系方式
}
```
#####回复
```
无内容，直接查看返回码
```

####查询用户意见
|   接口地址    |   xiaoyusvr/boss/suggest/query        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
不填，则查询所有
{
    "param":
    {
        "uname":""               //选填-用户名
    }
}
```
#####回复
```
[
    {
    "suggest_id":x,
    "user_id":x,
    "uname":"",
    "status":0,
    "suggestion":"",
    "contact":"",
    "created":x,
    "updated":x
    },
    {
    "suggest_id":x,
    "user_id":x,
    "uname":"",
    "status":0,
    "suggestion":"",
    "contact":"",
    "created":x,
    "updated":x
    }
]
```

####删除用户意见
|   接口地址    |   xiaoyusvr/boss/suggest/del        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |  

#####请求
```
{
    "suggest_id":x                  //必填-ID
}
```
#####回复
```
无内容，直接查看返回码
``` 

####修改用户意见
|   接口地址    |   xiaoyusvr/boss/suggest/update        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |  

#####请求
```
{
 "suggest_id":x,                  //必填-ID
 "user_id":x,                     //必填-用户id
 "uname":"",                      //必填-用户名
 "status":0,                      //选填-记录状态 0 :初始正常 1:已经回复
 "suggestion":"",                 //选填-意见内容   
 "contact":""                     //选填-联系方式 
 }
```
#####回复
```
无内容，直接查看返回码
```

####查询
|   接口地址    |   xiaoyusvr/boss/user/query        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
不填，则查询所有
{
 "page_no":x,                             //选填-当前页, 默认为第1页
 "page_size":x,                           //选填-每页记录数，默认20
 "param":{
         "domain":"",                    //选填-用户小位号
         "nick":"",                       //选填-用户昵称
         "user_id":x,                     //选填-用户ID
         "online_status":x                //选填-用户在线状态   1-在线 0-离线
         }
}
```
#####回复
```
{
    "page_no": 1,
    "page_size": 20,
    "total_count": 107,
    "total_page_count": 6,
    "param": null,
    "extra_param": null,
    "result": 
    [
        {
        "id": 105319,
        "sex": 1,
        "name": "13714738507",
        "domain": "0",
        "nick": "铭记在心",
        "password": "c2cb6c5c0f2bfc2af2a479fdf9f07b5b",
        "salt": "8551",
        "province": "",
        "city": "",
        "country": "",
        "phone": "13714738507",
        "email": "123@123.com",
        "avatar": "g0/000/000/1484449958650018_139730919216.jpg",
        "height": 0,
        "weight": 0,
        "birthday": "0",
        "type": 18,
        "departid": 3,
        "status": 0,
        "created": 1480383837,
        "updated": 1487580230,
        "pushShieldStatus": 0,
        "signInfo": "",
        "lng": "113.990849",
        "lat": "22.681539",
        "battery": 90,
        "sq": 0,
        "friendNeedAuth": 1,
        "loginSafeSwitch": 0,
        "searchAllow": 1,
        "onlineStatus": 0,
        "lastOnlineTime": 0
        },
        {
        "id": 105320,
        "sex": 1,
        "name": "18565806571",
        "domain": "18565806571",
        "nick": "18",
        "password": "10cf1d003cf153547d6aa7905e8547fa",
        "salt": "4502",
        "province": "广东省",
        "city": "深圳市",
        "country": "中国",
        "phone": "18565806571",
        "email": "704834364@qq.com",
        "avatar": "g0/000/000/1493197615818373_140172801484.jpg",
        "height": 0,
        "weight": 0,
        "birthday": "0",
        "type": 1,
        "departid": 3,
        "status": 0,
        "created": 1480384505,
        "updated": 1495877091,
        "pushShieldStatus": 0,
        "signInfo": "hhjjj",
        "lng": "113.990832",
        "lat": "22.681959",
        "battery": 37,
        "sq": 4,
        "friendNeedAuth": 1,
        "loginSafeSwitch": 0,
        "searchAllow": 1,
        "onlineStatus": 0,
        "lastOnlineTime": 1495883709
        }
    ]
}
```

####公司新增
|   接口地址    |   xiaoyusvr/boss/organization/insert        |
|   ---         |   ---                   |

```
//请求
{
 "name":"",                    //必填-公司名称
 "address":"",                 //选填-公司地址
 "contact":"",                 //选填-公司联系方式
 "email":"",                   //选填-公司email
 "describtion":"",             //选填-公司简介   
 "home":""                     //选填-公司主页 默认为index.html        
}

//回复
无内容，直接查看返回码
```

####公司查询
|   接口地址    |   xiaoyusvr/boss/organization/query        |
|   ---         |   ---                   |

```
//请求
{
    "name":""                    //选填-不填，则查询所有[名称支持模糊查询]
}

//回复
[
    {
        "id": 1,
        "name": "沸石智能有限公司",
        "address": "沸石智能有限公司",
        "contact": "100",
        "email": "0",
        "describtion": "",
        "status": 1,
        "created": 0,
        "updated": 0
    }
]
```

####公司删除
|   接口地址    |   xiaoyusvr/boss/organization/del        |
|   ---         |   ---                   |


```
//请求
{
    "id":x                        //必填-公司id
}

//回复
无内容，直接查看返回码
```

####公司修改
|   接口地址    |   xiaoyusvr/boss/organization/update        |
|   ---         |   ---                   |

```
//请求
{
 "id":x,                       //必填-公司id
 "name":"",                    //选填-公司名称
 "address":"",                 //选填-公司地址
 "contact":"",                 //选填-公司联系方式
 "email":"",                   //选填-公司email
 "describtion":"",             //选填-公司简介 
 "home":""                     //选填-公司主页
}

//回复
无内容，直接查看返回码
```

####新增设备
|   接口地址    |   xiaoyusvr/boss/accountmanage/add        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
 "depart_id":x,                //必填-公司iD 
 "description":"",             //选填-描述
 "begin_account":"",           //选填-设备起始编号
 "end_account":"",             //选填-设备结束编号
 "status":1                    //选填-0-初始 1-已经出厂    
}
```
#####回复
```
无内容，直接查看返回码
```

####查询设备
|   接口地址    |   xiaoyusvr/boss/accountmanage/query        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
没有，则查询所有
{
    "depart_id":x                 //选填-公司id
}
```
#####回复
```
[
    {
        "id": 1,
        "description": "试产100小位号",
        "status": 1,
        "created": 0,
        "depart_id": 1,
        "begin_account": "W0101000001",
        "end_account": "W0101000099"
    },
    {
        "id": 2,
        "description": "正式产小位号段",
        "status": 0,
        "created": 0,
        "depart_id": 1,
        "begin_account": "W0101003000",
        "end_account": "W0101006100"
    }
]
```

####删除设备
|   接口地址    |   xiaoyusvr/boss/accountmanage/del        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
    "id":x                    //必填-id
}
```

#####回复
```
无内容，直接查看返回码
```

####修改设备
|   接口地址    |   xiaoyusvr/boss/accountmanage/update        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
 "id":2,                                //必填-id
 "description": "",                     //选填-描述
 "status": 0,                           //选填-设备状态
 "depart_id": 1,                        //选填-公司id
 "begin_account": "W0101003000",        //选填-设备起始编号  
 "end_account": "W0101006100"           //选填-设备结束编号 
} 
```

#####回复
```
无内容，直接查看返回码
```

####每天注册/激活数
|   接口地址    |   xiaoyusvr/boss/report/activate        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
    "organ_id":1,           //所属公司
    "begin_date":"20160706",//开始查询日期
    "end_date":"20170630"   //结束查询日期
}
```
#####回复
```
{
    "20161129": 8,
    "20170306": 2,
    "20170320": 1,
    "20170328": 2,
    "20170329": 2,
    "20170405": 1,
    "20170406": 1,
    "20170413": 1,
    "20170414": 1,
    "20170419": 2,
    "20170421": 1,
    "20170425": 1,
    "20170512": 1,
    "20170515": 2,
    "20170517": 1,
    "20170520": 5,
    "20170523": 1,
    "20170524": 4
}
```

####统计概况
|   接口地址    |   xiaoyusvr/boss/report/dashboard        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
    "organ_id":1,           //所属公司
}
```
#####回复
```
{
    "province": 
    {
        "未知": 37
    },
    "sex": 
    {
        "男": 28,
        "未知": 9
    },
    "type": 
    {
        "19": 15,
        "20": 2,
        "21": 5,
        "25": 15
    }
}
```

####统计日消息总数
|   接口地址    |   xiaoyusvr/boss/report/messagecount        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
    "daytime":""              //必填-日期字符串  例如 2017-07-20
}    
```
####回复
```
{
    "count":x            
}
```

####统计消息类型分布
|   接口地址    |   xiaoyusvr/boss/report/messagetypecount        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |    

####请求
```
{}               //不需要请求参数
```
####回复
```
{
    "code": 0,
    "msg": "ok",
    "data": 
        [
            {
                "keyName": 17,
                "keyValue": 31
            },
            {
         		"keyName": 1,
         		"keyValue": 138
            },
            {
         		"keyName": 23,
         		"keyValue": 30
            },
            {
         		"keyName": 24,
         		"keyValue": 3
      		},
            {
         		"keyName": 25,
        		"keyValue": 42
      		}    
       ]
}
```

####添加短信模板
|   接口地址    |   xiaoyusvr/boss/sms/add        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
 "platfrom_id":x,                   //必填-短信平台ID
 "action":x,                        //选填-对应场景号
 "action_name":"",                  //选填-场景名称
 "template_name":""                 //选填-短信模板
 }
```
#####回复
```
无内容，直接查看返回码
```

####查询短信模板
|   接口地址    |   xiaoyusvr/boss/sms/query        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |  

#####请求
```
不填，则查询所有
{
    "action":x                       //选填-对应场景号
}
```
#####回复
```
[
    {
        "id": 5,
        "action": 2,
        "updated": 1499130741,
        "created": 1499130741,
        "platfrom_id": 1,
        "action_name": "ww",
        "template_name": "123"
    }
]
```

####修改短信模板
|   接口地址    |   xiaoyusvr/boss/sms/update        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
 "id":x,                           //必填-短信模板ID
 "platfrom_id":x,                  //必填-短信平台ID
 "action":x,                       //选填-对应场景号
 "action_name":"",                 //选填-场景名称
 "template_name":""                //选填-短信模板
 }
```
#####回复
```
无内容，直接查看返回码
```

####删除短信模板
|   接口地址    |   xiaoyusvr/boss/sms/del        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |  

#####请求
```
{
    "id":x                          //必填-短信模板ID
}
```
#####回复
```
无内容，直接查看返回码
```

####新增短信平台
|   接口地址    |   xiaoyusvr/boss/smsplatfrom/add        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
 "platfrom_name":"",               //必填-短信平台名称
 "status":1,                       //选填-0-弃用 1-使用  默认为1
 "config":""                       //选填-短信平台配置 
 }
``` 
#####回复
```
无内容，直接查看返回码
```

####查询短信平台
|   接口地址    |   xiaoyusvr/boss/smsplatfrom/query        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
不填则查询所有
{
    "platfrom_name":""                //选填-短信平台名称
}
```
#####回复
```
[
    {
        "status": false,
        "config": "{\"liuzhaoxin\":\"lihai\"}\n",
        "updated": 0,
        "created": 0,
        "smsplatfrom_id": 8,
        "platfrom_name": "1112"
    },
    {
        "status": true,
        "config": "{}\n",
        "updated": 0,
        "created": 0,
        "smsplatfrom_id": 9,
        "platfrom_name": "afds"
    }
]
```

####修改短信平台
|   接口地址    |   xiaoyusvr/boss/smsplatfrom/update        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |   

#####请求
```
{
 "smsplatfrom_id":x,                //必填-短信平台ID
 "platfrom_name":"",                //必填-短信平台名称
 "status":1,                        //选填--0-弃用 1-使用
 "config":""                        //选填-短信平台配置
 }   
```
#####回复
```
无内容，直接查看返回码
```

####删除短信平台
|   接口地址    |   xiaoyusvr/boss/smsplatfrom/del        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |  

#####请求
```
{
    "smsplatfrom_id":x               //必填-短信平台ID
}
```
#####回复
```
无内容，直接查看返回码
```

####添加通讯号码
|   接口地址    |   xiaoyusvr/boss/devicecontrol/add        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
 "device_id":x,                   //必填-设备号       
 "auth_type":x,                   //必填-权限类型:0-管理员 1-亲情 2-白名单 3-紧急号码
 "mobile":"",                     //必填-电话号码
 "status":x,                      //选填-1  可用    0  不可用
 "name":""                        //选填-称呼
 }              
```
#####回复
```
无内容，直接查看返回码
```

####查询通讯号码
|   接口地址    |   xiaoyusvr/boss/devicecontrol/query        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
 "device_id":x,                  //必填-设备ID
 "mobile":""                     //选填-电话号码
}
```
#####回复
```
[
    {
        "id": 402,
        "mobile": "123",
        "status": 1,
        "name": "哥哥",
        "updated": 1499651248,
        "created": 1499651248,
        "device_id": 1,
        "auth_type": 1
    }
]
```

####查询通讯号码
|   接口地址    |   xiaoyusvr/boss/devicecontrol/update        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
 "id":x,                        //必填-设备通讯ID
 "device_id":x,                 //必填-设备号
 "auth_type":x,                 //必填-权限类型:0-管理员 1-亲情 2-白名单 3-紧急号码
 "mobile":"",                   //必填-电话号码
 "status":x,                    //选填-1  可用    0  不可用
 "name":""                      //选填-称呼
 }
```
#####回复
```
无内容，直接查看返回码
```

####查询通讯号码
|   接口地址    |   xiaoyusvr/boss/devicecontrol/del        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
    "id":x                        //必填-设备通讯ID 
}
```
#####回复
```
无内容，直接查看返回码
```

####添加闹钟
|   接口地址    |   xiaoyusvr/boss/devicecrontab/add        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

#####请求
```
{
 "device_id":x,                  //必填-设备号       
 "task_type":1,                  //选填-任务类型 0-上课记录 1-关爱记录
 "task_name":"",                 //选填-任务标题
 "task_param":"",                //选填-任务参数
 "begin_time":"",                //选填-开始时间
 "end_time":"",                  //选填-结束时间
 "status":1,                     //选填-响铃模式 0-关闭 1-开启 3-删除
 "repeat_mode":1,                //选填-重复模式0-关闭 1-开启  
 "repeat_value":""               //选填-重复时间字符串
 }
```
#####回复
```
无内容，直接查看返回码
```

####查询闹钟
|   接口地址    |   xiaoyusvr/boss/devicecrontab/query        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
    "device_id":x                      //必填-设备id
}
```
#####回复
```
[
    {
        "status": 3,
        "updated": 1492067209,
        "created": 1491788486,
        "task_id": 14,
        "device_id": 105370,
        "task_type": 1,
        "task_name": "吃饭",
        "task_param": "14:28",
        "begin_time": "18:59,19:00",
        "end_time": "18:59,19:00",
        "repeat_mode": 0,
        "repeat_value": ""
    }
]
```

####修改闹钟
|   接口地址    |   xiaoyusvr/boss/devicecrontab/update        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |  

#####请求
```
{
 "task_id":x,                    //必填-闹钟ID
 "device_id":x,                  //必填-设备id
 "task_type":1,                  //选填-任务类型 0-上课记录 1-关爱记录
 "task_name":"",                 //选填-任务标题
 "task_param":"",                //选填-任务参数
 "begin_time":"",                //选填-开始时间
 "end_time":"",                  //选填-结束时间
 "status":1,                     //选填-响铃模式 0-关闭 1-开启 3-删除
 "repeat_mode":1,                //选填-重复模式0-关闭 1-开启  
 "repeat_value":""               //选填-重复时间字符串
 }
```
#####回复
```
无内容，直接查看返回码
```

####删除闹钟
|   接口地址    |   xiaoyusvr/boss/devicecrontab/del        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        | 

#####请求
```
{
    "task_id":x                         //必填-闹钟ID
}
```
#####回复
```
无内容，直接查看返回码
```

####消息类型查询
|   接口地址    |   xiaoyusvr/boss/messagetype/query        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
不填，则查询所有
{
    "type":x               //选填-消息类型对应ID
}    
```
####回复
```
{
   "code": 0,
   "msg": "ok",
   "data": [
      {
         "msg_type": 1,
         "msg_name": "文字-单"
      },
      {
         "msg_type": 2,
         "msg_name": "语音-单"
      },
      {
         "msg_type": 4,
         "msg_name": "小视频-单"
      },
      {
         "msg_type": 5,
         "msg_name": "通知-单"
      },
      {
         "msg_type": 6,
         "msg_name": "通知-群"
      },
      {
         "msg_type": 7,
         "msg_name": "抓拍-单"
      },
      {
         "msg_type": 8,
         "msg_name": "抓拍-群"
      },
      {
         "msg_type": 9,
         "msg_name": "录音-单"
      },
      {
         "msg_type": 16,
         "msg_name": "录音-群"
      },
      {
         "msg_type": 17,
         "msg_name": "文字-群"
      },
      {
         "msg_type": 18,
         "msg_name": "语音-群"
      }
   ]
}
```

####问答圈问题查询
|    接口地址       |  xiaoyusvr/problem/queryback   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
user_id 不填则查询所有，分页查询
{
     "param":{
     	            "user_id":250060      //选填-要查询的用户ID
     		  },	
     "page_no":1                      
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
      "total_count": 5,
      "total_page_count": 1,
      "param": null,  
      "extra_param": null,
      "result": [
         {
            "id": 71,
            "title": "Ewwwwwww",
            "content": "Wwwwww",
            "picture": "",
            "status": 1,
            "updated": 1505802100,
            "created": 1504236766,
            "user_id": 250060,
            "answer_num": 0,
            "browse_num": 6,
            "school_id": 62490
         }
       ]
     } 
}      
```

####问题更新
|    接口地址       |  xiaoyusvr/problem/update   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
   "id":x,                    //必填-更新的问题ID
   "user_id":x,               //选填-发布问题的用户ID
   "title":"",                //选填-问题的话题 
   "content":"",              //选填-问题的内容
   "answer_num":x,            //选填-问题的回答数
   "browse_num":x,            //选填-问题的浏览数
   "status":x                 //选填-问题状态  1--可用   0--不可用
}
```
####回复
```
无内容，直接查看返回码
```

####回答查询
|    接口地址       |  xiaoyusvr/answer/queryback   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
user_id 和  problem_id 都不填则查询所有
{
   "param":{
                   "user_id":1,       //选填-回答的用户ID
                   "problem_id":2     //选填-回答的问题ID
                },
   "page_no":x
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
            "content": "22",
            "status": 1,
            "updated": 0,
            "created": 0,
            "user_id": 1,
            "problem_id": 2,
            "agree_num": 0,
            "comment_num": 5
         }
      ]
   }
}
```

####回答更新
|    接口地址       |  xiaoyusvr/answer/update   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
   "id":1,                     //必填-回答的id
   "user_id":11,               //选填-回答的用户id
   "problem_id":11,            //选填-回答的问题id
   "content":"国庆快到了",        //选填-回答内容
   "agree_num":0,              //选填-赞同数
   "comment_num":5,            //选填-评论数
   "status":0                  //选填-回答状态  1--可用  0--不可用
}
```
####回复
```
无内容，直接查看返回码
```

####关注查询
|    接口地址       |  xiaoyusvr/concern/queryback   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
user_id 和   problem_id 都不填则查询所有
{
    "param":{
                     "user_id":3,                 //选填-用户ID
                     "problem_id":30              //选填-关注的问题ID
            },
    "page_no":x             
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
            "id": 13,
            "status": 0,
            "updated": 0,
            "created": 1503577744,
            "user_id": 3,
            "problem_id": 30
         }
      ]
   }
}
```

####关注更新
|    接口地址       |  xiaoyusvr/concern/update   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
   "id":x,                //必填-关注id
   "user_id":x,           //选填-用户id  
   "problem_id":x,        //选填-问题id
   "status":0             //选填-1--已关注   0--未关注
}
```
####回复
```
无内容，直接查看返回码
```

####评论查询
|    接口地址       |  xiaoyusvr/comment/queryback   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
answer_id comment_id problem_id 都不填则查询所有
{
  "param":{
                "answer_id":1,               //选填-回答id
                "comment_id":4,              //选填-评论id
                "problem_id":2               //选填-问题id   
           },
   "page_no":1               //选填-页码数  默认为1
}
```
####回复
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
            "from_userid": 22,
            "to_userid": 0,
            "answer_id": 1,
            "comment_id": 2,
            "problem_id": 2
         }
      ]
   }
}
```

####评论更新
|    接口地址       |  xiaoyusvr/comment/update   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
    "id":2,                           //必填-评论id
    "from_userid":1,                  //选填-发起评论的人id
    "to_userid":1,                    //选填-接收评论的人id  
    "answer_id":1,                    //选填-评论的回答id
    "comment_id":1,                   //选填-评论的评论id  
    "problem_id":1,                   //选填-评论的问题id
    "content":1,                      //选填-内容 
    "status":0                        //选填-评论状态   0--不可用  1--可用
}
```
####回复
```
无内容，直接查看返回码
```

####点赞查询
|    接口地址       |  xiaoyusvr/agree/queryback   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
user_id 和  answer_id 都不填则查询所有
{"param":
           {
				"user_id":3,          //选填-用户id
				"answer_id":1         //选填-问题id
            },
 "page_no":1                          //选填-页码数   默认为1
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
            "status": 0,
            "updated": 0,
            "created": 1502877000,
            "user_id": 3,
            "answer_id": 1
         }
      ]
   }
}
```

####点赞查询
|    接口地址       |  xiaoyusvr/agree/update   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
    "id":1,                  //必填-点赞表id
    "user_id":0,             //选填-用户id
    "answer_id":0,           //选填-回答id
    "status":0               //选填-状态    1--可用   0--不可用  
}
```
####回复
```
无内容，直接查看返回码
```

####应用商城   广告查询
|    接口地址       |  xiaoyusvr/app/advert/query   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
adc_name 不填则查询所有
{
    "param":{
                 "adc_name":"";              //选填-广告名字
             },
    "page_no":1                              //选填-当前页面       
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
            "status": 1,
            "prority": 12,
            "created": 324132415,
            "updated": 222235555,
            "description": "实里",
            "adv_index": "tiantianxiuxixu",
            "adc_name": "天天秀秀",
            "adv_url": "http://img.yingyonghui.com/card/com.ss.android.ugc.aweme/img_6_1501662610941.jpg",
            "delay_time": 1000,
            "inner_type": "app",
            "inner_id": 1,
            "inner_name": "修图"
         }
      ]
   }
}
```

####应用商城   广告更新
|    接口地址       |  xiaoyusvr/app/advert/update   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
    "id":3,                  //必填-广告id
    "adv_index":"",          //选填 
    "adv_name":"",           //选填
    "adv_url":"",            //选填
    "status":1,              //选填 0-待审核 1-发布 2-下架
    "prority":x,             //选填
    "delay_time":x,          //选填  
    "description":"",        //选填
    "inner_type":"",         //选填
    "inner_id":x,            //选填
    "inner_name":""          //选填
}
```
####回复
```
无内容，直接查看返回码
```

####应用商城   新增广告
|    接口地址       |  xiaoyusvr/app/advert/insert   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
      "adv_index":"",
      "adc_name":"mingz",
      "adv_url":"dddd",
      "status":0,
      "prority":11,
      "delay_time":1000,
      "description":"mafan",
      "inner_type":"222",
      "inner_id":22,
      "inner_name":"222"
}
```
####回复
```
无内容，直接查看返回码
```

####应用商城   频道查询
|    接口地址       |  xiaoyusvr/app/channel/query   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |  

####请求
```
channel_name 不填则查询所有
{
    "param":{
                 "channel_name":"男生频道"         //选填-频道名字
            },
    "page_no":1                                 
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
            "description": "男生专用",
            "status": 1,
            "updated": 0,
            "created": 0,
            "prority": 1,
            "image": "http://appimg.hicloud.com/hwmarket/files/portallinkimage/5e12fa89ceb4446f9112102a80a529bb.png",
            "channel_name": "男生频道",
            "back_ground": "#ffae00",
            "text_color": "#ffffff"
         }
      ]
   }
}
```

####应用商城   频道更新
|    接口地址       |  xiaoyusvr/app/channel/update   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求    
```
{
     "id":5,                      //必填-频道id
     "channel_name":"",           //选填-频道名字
     "back_ground":"",            //选填-背景颜色
     "description":"",            //选填-频道描述
     "status":1,                  //选填- 0-关闭 1-正常
     "prority":x,                 //选填-权值
     "text_color":"",             //选填-文字颜色
     "image":""                   //选填-图片
}
```
####回复
```
无内容，直接查看返回码
```

####应用商城   新增频道
|    接口地址       |  xiaoyusvr/app/channel/insert   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
      "channel_name":"xxx",
      "back_ground":"mingz",
      "description":"dddd",
      "status":0,
      "prority":11,
      "text_color":"11",
      "image":"mafan"      
}
```
####回复
```
无内容，直接查看返回码
```

####应用商城   频道应用查询
|    接口地址       |  xiaoyusvr/app/channellist/query   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
都不填则查询所有
{
    "param":{
                 "channel_id":5,     //选填-频道id
                 "app_id":2          //选填-应用id 
             }
    "page_no":1
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
            "id": 2,
            "status": 1,
            "updated": 1,
            "prority": 0,
            "channel_id": 5,
            "app_id": 2
         }
      ]
   }
}
```

####应用商城   频道应用查询
|    接口地址       |  xiaoyusvr/app/channellist/update   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
     "id":x,                 //必填-关系id
     "channel_id":x,         //选填-频道id 
     "app_id":x,             //选填-应用id 
     "status":x,             //选填- 1--可用  0--不可用
     "prority":x             //选填-权值
}
```
####回复
```
无内容，直接查看返回码
```

####应用商城   新增频道应用
|    接口地址       |  xiaoyusvr/app/channellist/insert   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
      "channel_id":6,           //必填-频道ID
      "app_id":5,               //必填-应用id
      "status":0,
      "prority":0          
}
```
####回复
```
无内容，直接查看返回码
```

####应用商城   应用信息查询
|    接口地址       |  xiaoyusvr/appinformation/query   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
不填则查询所有
{
     "param":{
                       "app_name":"超级"    //选填
                   },
     "page_no":1
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
            "id": 5,
            "category": "日常工具",
            "status": 1,
            "description": "课程学习",
            "version": "9.1.2",
            "icon": "http://p18.qhimg.com/t0165cf86f621865736.png",
            "images": "http://p17.qhimg.com/dm/180_300_/t0175b5305e91387007.jpg;http://p16.qhimg.com/dm/180_300_/t01b32d85a47074f32a.jpg;http://p16.qhimg.com/dm/180_300_/t018ec0d9633156b02b.jpg",
            "download": "http://shouji.360tpcdn.com/170831/4fda99a5a2e9a414708babeb308b93bc/com.xtuone.android.syllabus_107.apk",
            "size": "19.32M",
            "updated": 0,
            "created": 0,
            "prority": 60,
            "remarks": "你是",
            "label": "asdas",
            "star": "4.2",
            "orientation": 0,
            "app_index": "chaoji",
            "app_name": "超级",
            "app_spell": "chaoji",
            "package_name": "com.fise.app",
            "dev_id": 0,
            "dev_name": "广州超级周末科技有限公司",
            "top_category": "软件",
            "version_code": "853",
            "icon_type": 1
         }
      ]
   }
}
```

####应用商城   应用信息更新
|    接口地址       |  xiaoyusvr/appinformation/update   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
      "id":8,                       //必填-id
      "app_index":"",
      "app_name":"",
      "app_spell":"",
      "package_name":"",
      "dev_id":x,
      "dev_name":"",
      "top_category":"",
      "category":"",
      "status":x,
      "description":"",
      "version":"",
      "version_code":"",
      "icon":"",
      "icon_type":x,
      "images":"",
      "download":"",
      "size":"",
      "prority":x,
      "remarks":"",
      "label":"",
      "star":"",
      "orientation":"" 
}
```
####回复
```
无内容，直接查看返回码
```

####应用商城   应用信息更新
|    接口地址       |  xiaoyusvr/appinformation/insert   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
      "app_index":"6",
      "app_name":"5",
      "app_spell":"0",
      "package_name":"0",
      "dev_id":1,
      "dev_name":"222",
      "top_category":"211",
      "category":"223",
      "status":0,
      "description":"dsdas",
      "version":"2.35",
      "version_code":"78",
      "icon":"356",
      "icon_type":3,
      "images":"fdf",
      "download":"ewqewq",
      "size":"dwdwd",
      "prority":33,
      "remarks":"wewqe",
      "label":"dasdi",
      "star":"dda",
      "orientation":1          
}
```
####回复
```
无内容，直接查看返回码
```

####应用商城   appsplash查询
|    接口地址       |  xiaoyusvr/appsplash/query   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
     "param":{
                    "name":""      //选填             
             },
     "page_no":1                   //选填
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
            "name": "自拍神器",
            "url": "http://p0.qhimg.com/t01e44dbaa14c1be685.jpg",
            "prority": 100,
            "status": 1,
            "delay": 3,
            "updated": 0,
            "action_url": "app:2"
         },
         {
            "id": 2,
            "name": "课程表",
            "url": "http://p8.qhimg.com/t01080e733044551360.png",
            "prority": 90,
            "status": 1,
            "delay": 3,
            "updated": 0,
            "action_url": "app:3"
         },
         {
            "id": 3,
            "name": "遇见软件",
            "url": "http://p8.qhimg.com/t0146a917f3f71eb7ca.jpg",
            "prority": 50,
            "status": 1,
            "delay": 3,
            "updated": 0,
            "action_url": "channel:5"
         }
      ]
   }
}
```

####应用商城   appsplash更新
|    接口地址       |  xiaoyusvr/appsplash/update   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
     "id":3,                     //必填
     "name":"",                  //选填
     "url":"",                   //选填
     "action_url":"",            //选填
     "prority":x,                //选填
     "status":0,                 //选填
     "delay":x                   //选填
}
```
####回复
```
无内容，直接查看返回码
```

####应用商城   新增appsplash
|    接口地址       |  xiaoyusvr/appsplash/insert   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
     "name":"",                  //选填
     "url":"",                   //选填
     "action_url":"",            //选填
     "prority":x,                //选填
     "status":0,                 //选填
     "delay":x                   //选填
}
```
####回复
```
无内容，直接查看返回码
```

####敏感词添加
|    接口地址       |  xiaoyusvr/sensitiveword/insert   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
    "sensitive_word":""          //必填-敏感词
}
```
####回复
```
无内容，直接查看返回码
```

####敏感词查询
|    接口地址       |  xiaoyusvr/sensitiveword/query   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
sensitive_word 不填则查询所有
{
     "param":{
             	"sensitive_word":""          //选填-敏感词 
              },
	 "page_no":1                             //选填-页码数   默认为1
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
      "total_count": 1063,
      "total_page_count": 107,
      "param": null,
      "extra_param": null,
      "result": [
         {
            "id": 1,
            "sensitive_word": "阿扁推翻"
         },
         {
            "id": 2,
            "sensitive_word": "阿宾"
         },
         {
            "id": 3,
            "sensitive_word": "阿賓"
         },
         {
            "id": 4,
            "sensitive_word": "挨了一炮"
         },
         {
            "id": 5,
            "sensitive_word": "爱液横流"
         },
         {
            "id": 6,
            "sensitive_word": "安街逆"
         },
         {
            "id": 7,
            "sensitive_word": "安局办公楼"
         },
         {
            "id": 8,
            "sensitive_word": "安局豪华"
         },
         {
            "id": 9,
            "sensitive_word": "安门事"
         },
         {
            "id": 10,
            "sensitive_word": "安眠藥"
         }
      ]
   }
}
```

####敏感词删除
|    接口地址       |  xiaoyusvr/sensitiveword/delete   |
|    ---     |      ---          |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
	"id":x           //必填-敏感词ID
}
```
####回复
```
无内容，直接查看返回码
```

####查询设备配置 
|   接口地址    |   xiaoyusvr/boss/querydevice        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
device_id 和  master_id 都不填则查询所有
{
    "param":{
                  "device_id":x,          //选填-设备id
                  "master_id":x           //选填-管理员id
             },
    "page_no":1                           //选填-页码数
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
            "mobile": "13423720455",
            "electricize": 0,
            "mode": 1,
            "updated": 1504770239,
            "created": 1502772239,
            "device_id": 250033,
            "master_id": 1000,
            "group_id": 369,
            "alarm_tokenoff": 1,
            "alarm_battery": 1,
            "alarm_poweroff": 1,
            "listen_silent": 1,
            "step_mode": 1,
            "alarm_call": 1,
            "bell_mode": 2,
            "school_id": 63025,
            "light_time": 15
         },
         {
            "mobile": "13423720455",
            "electricize": 0,
            "mode": 1,
            "updated": 1504769615,
            "created": 1503401356,
            "device_id": 250044,
            "master_id": 1000,
            "group_id": 370,
            "alarm_tokenoff": 1,
            "alarm_battery": 1,
            "alarm_poweroff": 1,
            "listen_silent": 1,
            "step_mode": 1,
            "alarm_call": 1,
            "bell_mode": 2,
            "school_id": 63025,
            "light_time": 30
         },
         {
            "mobile": "13423720455",
            "electricize": 0,
            "mode": 1,
            "updated": 1505811287,
            "created": 1504597875,
            "device_id": 250065,
            "master_id": 1000,
            "group_id": 375,
            "alarm_tokenoff": 1,
            "alarm_battery": 1,
            "alarm_poweroff": 1,
            "listen_silent": 1,
            "step_mode": 1,
            "alarm_call": 1,
            "bell_mode": 2,
            "school_id": 86098,
            "light_time": 60
         }
      ]
   }
}
```

####新增发送邮件接口 
|   接口地址    |   xiaoyusvr/boss/admin/send_identity_code        |
|   ---         |   ---                   |
|   请求方式    |   HTTP POST             |
|   参数格式    |   JSON                        |

####请求
```
{
	"emailaddress":"",          //必填-收件人邮箱地址
	"code":""                   //必填-验证码
}
```
####回复
```
无内容，直接查看返回码
```