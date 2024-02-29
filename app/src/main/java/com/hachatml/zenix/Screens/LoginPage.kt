package com.hachatml.zenix.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.relay.compose.RelayColumn
import com.hachatml.zenix.actioncards.Action
import com.hachatml.zenix.actioncards.ActionCards
import com.hachatml.zenix.helpbutton.HelpButton
import com.hachatml.zenix.signin.SignInState
import com.hachatml.zenix.welcome.WelcomeText
/**
 * Columna principal de la Screen de cuenta cuando la sesión aún no está iniciada
 * @param navController Nav controlador del movimiento en la app
 * @param state Observa el estado del signin por si hubiese algún error
 * @param signInClick realiza la acción del inicio de sesión
 */
@Composable
fun LoginColumn(navController: NavController, state: SignInState, signInClick: () -> Unit) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog){
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Login Room") },
            text = { Text("You're now on the login page.\n" +
                    "Zenix, at the moment, only supports Google Login.\n" +
                    "DISCLAIMER: Zenix only uses your data to identify yourself and doesn't share it\n" +
                    "with ANY third parties.") },
            confirmButton = { Button(onClick = { showDialog = false }) { Text("OK") } },
        )
    }
    //Ejecutará un toast en caso de error al hacer login
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    RelayColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF9DD1F6)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HelpButton(modifier = Modifier.clickable { showDialog=!showDialog }.size(63.dp, 63.dp).clip(RoundedCornerShape(50)))
        WelcomeText(modifier = Modifier.size(428.dp, 99.dp))
        ActionCards(modifier = Modifier.clickable { signInClick() }, action = Action.GoogleLogin)
        Spacer(modifier = Modifier.size(100.dp, 100.dp))
    }
}