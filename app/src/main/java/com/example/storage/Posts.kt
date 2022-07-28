package com.example.storage

data class Posts(
    var userid:Int,
    var id:Int,
    var title:String,
    var body:String
)

data class Comments (
    var id:Int,
    var postId:Int,
    var name: String,
    var email: String,
    var body : String
)

