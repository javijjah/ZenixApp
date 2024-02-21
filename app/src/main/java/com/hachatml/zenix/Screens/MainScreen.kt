package com.hachatml.zenix.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hachatml.zenix.ImportedRelayComponents.MyHelpButton
import com.hachatml.zenix.ImportedRelayComponents.MyLogin
import com.hachatml.zenix.helpbutton.HelpButton
import com.hachatml.zenix.login.Login

@Composable
fun MainScreen(){
Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize()
){
    MyHelpButton()
    MyLogin()
}
}