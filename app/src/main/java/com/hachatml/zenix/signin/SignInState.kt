package com.hachatml.zenix.signin

/**
 * Gestiona el estado en el que queda el Login, en caso de quedar a medias tenemos el error y si no tenemos
 * siempre accesible el logeo correcto en el Bool
 */
data class SignInState(
    val isSignSuccesful:Boolean = false,
    val signInError: String? = null
)
