package com.hachatml.zenix.Screens

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.relay.compose.RelayColumn
import com.hachatml.zenix.R
import com.hachatml.zenix.Routes
import com.hachatml.zenix.actioncards.Action
import com.hachatml.zenix.actioncards.ActionCards
import com.hachatml.zenix.helpbutton.HelpButton
import com.hachatml.zenix.readybutton.ReadyButton
import com.hachatml.zenix.readybutton.ReadyOrNot
import com.hachatml.zenix.sessionplayingcloseyoureyes.SessionPlayingText
import com.hachatml.zenix.welcome.WelcomeText

/**
 * Composable principal de la screen de cuando una sesión se está ejecutando.
 */
@Composable
fun PlayingColumn(navController: NavController) {
    //este media player es el objeto que se encarga de reproducir nuestro audio. Colocándolo de esta forma,
    //comenzará a reproducirse nada más cargue el Composable
    val mMediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.meditation)
    mMediaPlayer.start()
    mMediaPlayer.isLooping=false
    RelayColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF9DD1F6))
            .padding(0.dp, 150.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SessionPlayingText(modifier = Modifier.size(447.dp, 145.dp))
        //Spacer(modifier = Modifier.size(100.dp,100.dp))
        ReadyButton(
            modifier = Modifier
                .size(120.dp, 55.dp)
                .clickable {
                    mMediaPlayer.stop()
                    navController.navigate(Routes.MainScreen.route) },
            readyOrNot = ReadyOrNot.LeaveSession
        )
    }
}