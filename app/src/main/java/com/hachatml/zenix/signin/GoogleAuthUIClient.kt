package com.hachatml.zenix.signin

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hachatml.zenix.R
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

/**
 * Encargado de conectar la aplicación con la llamada a Google de que el usuario quiere iniciar sesión
 * @param context utilizado para recibir el string webpage de R.strings
 * @param oneTapClient es una entidad que conseguiremos a través del Sign In de los Google Play Services
 */
class GoogleAuthUIClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val auth = Firebase.auth

    /**
     * Realiza el inicio de sesión como tal, o manda una excepción si esta falla o se cancela
     */
    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    /**
     * Esta función comienza la "intención" (Intent) del usuario de realizar el sign in per sé
     * @param intent La llamada que realiza el usuario para iniciar sesión
     */
    suspend fun getSignInIntent(intent: Intent): SignInResult {
        //Estas son las credenciales del usuario y del token de intento de usuario pasadas a variables
        //para poder trabajar con ellas
        val credencial = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credencial.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        //Una vez más, un tryCatch que recoge un posible error y pasa los datos de inicio de sesión como
        //un userData, que se devolverá como una clase SignInResult
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    /**
     * Entrega los datos del usuario iniciado como UserData(datos utilizables)
     */
    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }

    /**
     * Llama al signOut del oneTapClient y de firebase.auth
     */
    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.webclient))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}