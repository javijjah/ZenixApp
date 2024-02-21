package com.hachatml.zenix.ImportedRelayComponents

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.relay.compose.RelayVector
import com.hachatml.zenix.R
import com.hachatml.zenix.helpbutton.HelpEllipse
import com.hachatml.zenix.helpbutton.HelpVector
import com.hachatml.zenix.helpbutton.TopLevel
import com.hachatml.zenix.helpbutton.TopLevelSynth
import com.hachatml.zenix.login.LoginEllipse
import com.hachatml.zenix.login.LoginVector

@Composable
fun MyHelpVector(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.help_button_help_vector),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 17.0.dp,
                top = 12.0.dp,
                end = 18.644920349121094.dp,
                bottom = 12.644920349121094.dp
            )
        )
    )
}
@Composable
fun MyHelpButton(modifier: Modifier = Modifier) {
    HelpEllipse()
    MyHelpVector(modifier = Modifier)
}
@Composable
fun MyLogin(modifier: Modifier = Modifier) {
    com.hachatml.zenix.login.TopLevel(modifier = modifier) {
        com.hachatml.zenix.login.TopLevelSynth {
            LoginEllipse()
        }
        LoginVector(modifier = Modifier)
    }
}
@Composable
fun Login(modifier: Modifier = Modifier) {
    LoginEllipse()
    LoginVector(modifier = Modifier)
}
