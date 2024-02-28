package com.hachatml.zenix.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.relay.compose.RelayColumn
import com.hachatml.zenix.guidedmeditation.GuidedMeditation
import com.hachatml.zenix.helpbutton.HelpButton
import com.hachatml.zenix.inspiringquotes.InspiringQuotes
import com.hachatml.zenix.quotebox.QuoteBox
import com.hachatml.zenix.readybutton.ReadyButton
import com.hachatml.zenix.readybutton.ReadyOrNot
import com.hachatml.zenix.usercard.UserCard

@Composable
fun QuotesColumn(navController: NavController){
    RelayColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF9DD1F6)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HelpButton(modifier = Modifier.size(63.dp,63.dp).clip(RoundedCornerShape(100)))
        InspiringQuotes(modifier = Modifier.size(421.dp,145.dp))
        QuoteBox(modifier = Modifier.size(384.dp,256.dp),phrase="\"Do it or don't do it, but don't try it\"",writer="Master Yoda")
        ReadyButton(modifier = Modifier.size(120.dp,55.dp), ReadyOrNot.Next)
    }
}