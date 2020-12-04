package com.test.login_mvp.bean


data class LoginBean(
    var admin: Boolean, var chapterTops: List<*>, var collectIds: List<*>
    , var email: String?, var icon: String?, var id: String?, var nickname: String?
    , var password: String?, var publicName: String?, var token: String?
    , var type: Int, var username: String?
)
//{
//    "data": {
//    "admin": false,
//    "chapterTops": [],
//    "collectIds": [],
//    "email": "",
//    "icon": "",
//    "id": 39333,
//    "nickname": "zero_vip123",
//    "password": "",
//    "publicName": "zero_vip123",
//    "token": "",
//    "type": 0,
//    "username": "zero_vip123"
//},
//    "errorCode": 0,
//    "errorMsg": ""
//}

