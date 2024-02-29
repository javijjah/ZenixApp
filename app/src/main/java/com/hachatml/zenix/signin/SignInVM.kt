package com.hachatml.zenix.signin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Gestor del Flow de logeo a través de un StateFlow, que mantiene actualizado la variable en función
 * de lo que esté sucediendo
 */
class SignInVM : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    /**
     * actualiza _state en función de lo sucedido en el SigninResult
     * @param el SignInResult del que deseamos actualizar el estado en el StateFlow
     */
    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignSuccesful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    /**
     * Reinicia el _stat del StateFlow
     */
    fun resetState() {
    _state.update { SignInState() }
    }
}