package com.hachatml.zenix.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import com.google.relay.compose.RelayColumn
import com.google.relay.compose.RelayRow
import com.hachatml.zenix.MeditationRoomVM
import com.hachatml.zenix.Routes
import com.hachatml.zenix.actioncards.Action
import com.hachatml.zenix.actioncards.ActionCards
import com.hachatml.zenix.helpbutton.HelpButton
import com.hachatml.zenix.howarewerelaxingtoday.HowAreWeRelaxingToday
import com.hachatml.zenix.login.Login
import com.hachatml.zenix.signin.UserData

@Composable
fun MainScreen(navController: NavController,meditationVM:MeditationRoomVM, userData: UserData?){
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog){
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Main Room") },
            text = { Text("Welcome to Zenix, the best group Meditation app.\n" +
                    "On this page, click the Zenix logo to get to the login page.\n" +
                    "On the \"Join Session\" button, you can get to the Group Meditation Room\n") },
            confirmButton = { Button(onClick = { showDialog = false }) { Text("OK") } },
        )
    }
RelayColumn(
    verticalArrangement = Arrangement.SpaceEvenly,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF9DD1F6)),
){
    HelpButton(modifier = Modifier.clickable { showDialog=!showDialog }.size(63.dp,63.dp).clip(RoundedCornerShape(50)))
    Login(modifier = Modifier.size(111.dp,111.dp).clip(RoundedCornerShape(25)).clickable { navController.navigate(Routes.LoginPage.route) })
    HowAreWeRelaxingToday(modifier = Modifier.size(442.dp,160.dp))
    RelayRow(
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        ActionCards(modifier = Modifier.size(192.dp,328.dp).clickable {
            meditationVM.userEntered(userData)
            navController.navigate(Routes.MeditationPage.route)
        })
        Spacer(modifier = Modifier.size(20.dp))
        ActionCards(modifier = Modifier.size(192.dp,328.dp).clickable{navController.navigate(Routes.QuotePage.route)},action = Action.Respiration)
    }
}
}