package com.hachatml.zenix.signin

/**
 * Datos devueltos por el inicio de sesión
 */
data class SignInResult(
        val data:UserData?,
        val errorMessage:String?
    )

/**
 * Los datos del usuario de una forma más estructurada y utilizable
 */
    data class UserData(
        val userId: String,
        val username: String?,
        val profilePictureUrl: String?
    ){
        constructor() : this("", null, null)
    }
