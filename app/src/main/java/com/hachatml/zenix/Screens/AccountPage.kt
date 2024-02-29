package com.hachatml.zenix.Screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hachatml.zenix.Routes
import com.hachatml.zenix.readybutton.ReadyButton
import com.hachatml.zenix.readybutton.ReadyOrNot
import com.hachatml.zenix.signin.UserData
import com.hachatml.zenix.username.UserName
import kotlinx.coroutines.Job

/**
 * Columna principal de la Screen de cuenta cuando la sesión ya está iniciada
 * @param navController Nav controlador del movimiento en la app
 * @param userData los datos del usuario para mostrarlos en el apartado "Cuenta"
 * @param onSignOut Lambda al ejecutar para solicitar el LogOut
 */
@Composable
fun CuentaColumn(
    navController: NavController,
    userData: UserData?,
    onSignOut: () -> Job
) {
    //Esto se realiza así ya que si no puede bugearse con la secuencia al logear al usuario
    BackHandler {
        navController.navigate(Routes.MainScreen.route)
    }
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF9DD1F6))
            .padding(0.dp, 150.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(284.dp)
                .height(275.dp)
                .clip(shape = RoundedCornerShape(20))
                .background(color = Color(0xFFD9D9D9))
                .padding(start = 67.dp, end = 67.dp)
        ) {
            //Esto utiliza Coil para cargar de forma asíncrona la imagen del usuario
            if (userData?.profilePictureUrl != null) {
                AsyncImage(
                    model = userData.profilePictureUrl,
                    contentDescription = "User profile picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(100))
                )
            }
            if (userData?.username != null) {
                UserName(Modifier, userData.username)
            }
        }
        ReadyButton(
            modifier = Modifier.size(120.dp, 55.dp).clickable { onSignOut() }, readyOrNot = ReadyOrNot.SignOut
        )
    }
}