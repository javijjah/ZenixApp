package com.hachatml.zenix.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.relay.compose.RelayColumn
import com.hachatml.zenix.actioncards.Action
import com.hachatml.zenix.actioncards.ActionCards
import com.hachatml.zenix.guidedmeditation.GuidedMeditation
import com.hachatml.zenix.helpbutton.HelpButton
import com.hachatml.zenix.readybutton.ReadyButton
import com.hachatml.zenix.usercard.UserCard
import com.hachatml.zenix.welcome.WelcomeText

@Composable
fun MeditationColumn(){
        RelayColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF9DD1F6)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HelpButton(modifier = Modifier.size(63.dp,63.dp))
            GuidedMeditation(modifier = Modifier.size(421.dp,145.dp))
        RelayColumn(
            modifier = Modifier.size(2000.dp,200.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            //todo variar por num users con if y un foreach para añadir una UserCard por login
            //todo arreglar espacio entre users card
            UserCard(modifier = Modifier.size(324.dp,51.dp), "User 1")
            UserCard(modifier = Modifier.size(324.dp,51.dp), "Sample Text")
            UserCard(modifier = Modifier.size(324.dp,51.dp), "SampleName")
        }
            ReadyButton(modifier = Modifier.size(120.dp,55.dp))
        }
}