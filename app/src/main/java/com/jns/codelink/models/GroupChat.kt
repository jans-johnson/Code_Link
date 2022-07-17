package com.jns.codelink.models

data class GroupChat(
    var groupId:String="",
    var groupName:String="",
    var owner:String="",
    var users: HashMap<String, String>?
)