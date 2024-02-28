package com.hachatml.zenix.signin

    data class SignInResult(
        val data:UserData?,
        val errorMessage:String?
    )
    data class UserData(
        val userId: String,
        val username: String?,
        val profilePictureUrl: String?
    ){
        constructor() : this("", null, null)
    }
