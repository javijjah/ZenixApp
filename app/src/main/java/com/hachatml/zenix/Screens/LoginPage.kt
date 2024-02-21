package com.hachatml.zenix.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.relay.compose.RelayColumn
import com.hachatml.zenix.actioncards.Action
import com.hachatml.zenix.actioncards.ActionCards
import com.hachatml.zenix.helpbutton.HelpButton
import com.hachatml.zenix.welcome.WelcomeText

@Composable
fun LoginColumn() {
    RelayColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF9DD1F6)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    HelpButton(modifier = Modifier.size(63.dp,63.dp))
    WelcomeText(modifier = Modifier.size(428.dp,99.dp))
    ActionCards(action = Action.GoogleLogin)
    Spacer(modifier = Modifier.size(100.dp,100.dp))
    }
}