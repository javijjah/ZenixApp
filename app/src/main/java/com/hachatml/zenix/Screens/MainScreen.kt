package com.hachatml.zenix.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.relay.compose.RelayColumn
import com.google.relay.compose.RelayRow
import com.hachatml.zenix.ImportedRelayComponents.MyHelpButton
import com.hachatml.zenix.ImportedRelayComponents.MyLogin
import com.hachatml.zenix.actioncards.Action
import com.hachatml.zenix.actioncards.ActionCards
import com.hachatml.zenix.helpbutton.HelpButton
import com.hachatml.zenix.howarewerelaxingtoday.HowAreWeRelaxingToday
import com.hachatml.zenix.login.Login

@Composable
fun MainScreen(){
RelayColumn(
    verticalArrangement = Arrangement.SpaceEvenly,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF9DD1F6)),
){
    HelpButton(modifier = Modifier.size(63.dp,63.dp))
    Login(modifier = Modifier.size(111.dp,111.dp))
    HowAreWeRelaxingToday(modifier = Modifier.size(442.dp,160.dp))
    RelayRow(
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        ActionCards(modifier = Modifier.size(192.dp,328.dp))
        Spacer(modifier = Modifier.size(20.dp))
        ActionCards(modifier = Modifier.size(192.dp,328.dp),action = Action.Respiration)
    }
}
}