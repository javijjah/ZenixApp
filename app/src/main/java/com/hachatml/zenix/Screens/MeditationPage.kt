package com.hachatml.zenix.Screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.relay.compose.RelayColumn
import com.hachatml.zenix.MeditationRoomVM
import com.hachatml.zenix.Routes
import com.hachatml.zenix.guidedmeditation.GuidedMeditation
import com.hachatml.zenix.helpbutton.HelpButton
import com.hachatml.zenix.readybutton.ReadyButton
import com.hachatml.zenix.readybutton.ReadyOrNot
import com.hachatml.zenix.signin.UserData
import com.hachatml.zenix.usercard.UserCard

@Composable
fun MeditationColumn(navController: NavController, VM: MeditationRoomVM, userData: UserData?) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog){
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Meditation Room") },
            text = { Text("You're now waiting to get inside a session.\n" +
                    "When all the users are ready, the session starts.\n" +
                    "If the room remains active for 10 minutes and all the users are not ready,\n" +
                    "the session starts itself anyways.") },
            confirmButton = { Button(onClick = { showDialog = false }) { Text("OK") } },
        )
    }
    BackHandler {
        println("called BackHandler")
        VM.userLeft(userData)
        navController.navigate(Routes.MainScreen.route)
    }
    VM.populateUserList()
    RelayColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF9DD1F6)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HelpButton(
            modifier = Modifier.clickable { showDialog=!showDialog }
                .size(63.dp, 63.dp)
                .clip(RoundedCornerShape(100))
        )
        GuidedMeditation(modifier = Modifier.size(421.dp, 145.dp))
        RelayColumn(
            modifier = Modifier.size(2000.dp, 200.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (VM.userList.size < 5) {
                LazyColumn(Modifier.size(500.dp,100.dp)){
                    itemsIndexed(VM.userList) { index, user ->
                        user.username?.let { UserCard(Modifier, it) }
                        Spacer(modifier = Modifier.size(0.dp, 40.dp))
                    }
                }
            }
            else{
                UserCard(Modifier, "Number of users: ${VM.userList.size}")
            }
            var ready by rememberSaveable {
                mutableStateOf(true)
            }
            var readyState by rememberSaveable {
                mutableStateOf(ReadyOrNot.Ready)
            }
            ReadyButton(
                modifier = Modifier
                    .size(120.dp, 55.dp)
                    .clickable {
                        if (ready) {
                            readyState = ReadyOrNot.Not
                            ready = false
                        } else {
                            readyState = ReadyOrNot.Ready
                            ready = true
                        }
                        VM.setReadyState(VM.localUserID, !ready)
                    }, readyOrNot = readyState
            )
        }
    }
}