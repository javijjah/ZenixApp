package com.hachatml.zenix.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.relay.compose.RelayColumn
import com.hachatml.zenix.mainlogo.MainLogoVector
import com.hachatml.zenix.zenix.ZenixText

/**
 * SplashScreen creada para imitar el diseño de Relay "Splash Screen", pero deprecada por no ser
 * realmente necesaria
 */
@Composable
fun SplashScreen(navController: NavController){
    RelayColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF9DD1F6))
            .padding(0.dp, 150.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ZenixText(modifier = Modifier.size(247.dp,150.dp))
        MainLogoVector(modifier = Modifier.size(110.dp,110.dp))
    }
}