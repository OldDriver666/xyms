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
http://boss.fise-wi.com
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
     		      "name":""	   //选填-用户名     （不填查询所有     填写查询指定用户的问题）
     		  }         
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
                  "title":""            //必填-模糊查询话题
              }    
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