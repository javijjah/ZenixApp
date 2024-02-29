package com.hachatml.zenix

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.hachatml.zenix.Screens.CuentaColumn
import com.hachatml.zenix.Screens.LoginColumn
import com.hachatml.zenix.Screens.MainScreen
import com.hachatml.zenix.Screens.MeditationColumn
import com.hachatml.zenix.Screens.PlayingColumn
import com.hachatml.zenix.Screens.QuotesColumn
import com.hachatml.zenix.Screens.SplashScreen
import com.hachatml.zenix.model.MeditationRoomVM
import com.hachatml.zenix.signin.GoogleAuthUIClient
import com.hachatml.zenix.signin.SignInVM
import com.hachatml.zenix.ui.theme.ZenixTheme
import kotlinx.coroutines.launch

/**
 * DOCUMENTACIÓN AVANZADA EN EL README.MD
 */
class MainActivity : ComponentActivity() {
    //Este será nuestro token de inicio de sesión en google
    private val googleAuthUiClient by lazy {
        GoogleAuthUIClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    //Nuestro VM sobre la screen que utiliza RTDB
    private val MeditationVM = MeditationRoomVM()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZenixTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.SessionPlaying.route
                    ) {
                        composable(Routes.SplashScreen.route) { SplashScreen(navController) }
                        composable(Routes.MainScreen.route) { MainScreen(navController,MeditationVM,googleAuthUiClient.getSignedInUser()) }
                        //Nuestro composable de login es tan largo debido a que tiene que gestionar la llamada principal a los
                        //intentos de login, y cargar una screen u otra en función de si el usuario está logeado
                        composable(Routes.LoginPage.route) {
                            //NUestro VM sobre el inicio de sesión
                            val viewModel = viewModel<SignInVM>()
                            val state by viewModel.state.collectAsState()
                            LaunchedEffect(key1 = Unit) {
                                if (googleAuthUiClient.getSignedInUser() != null) {
                                    navController.navigate(Routes.AccountPage.route)
                                }
                            }
                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.getSignInIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )
                            LaunchedEffect(key1 = state.isSignSuccesful) {
                                if (state.isSignSuccesful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign in successful",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.navigate(Routes.MainScreen.route)
                                    viewModel.resetState()
                                }
                            }
                            LoginColumn(
                                navController = navController,
                                state = state,
                                signInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                })
                        }
                        composable(Routes.MeditationPage.route) { MeditationColumn(navController,MeditationVM,googleAuthUiClient.getSignedInUser())}
                        composable(Routes.QuotePage.route) { QuotesColumn(navController) }
                        composable(Routes.SessionPlaying.route) { PlayingColumn(navController) }
                        composable(Routes.AccountPage.route) {
                            CuentaColumn(
                                navController,
                                googleAuthUiClient.getSignedInUser()
                            ) {
                                navController.navigate(Routes.MainScreen.route)
                                lifecycleScope.launch {
                                    googleAuthUiClient.signOut()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
