package com.hachatml.zenix.signin

data class SignInState(
    val isSignSuccesful:Boolean = false,
    val signInError: String? = null
)
