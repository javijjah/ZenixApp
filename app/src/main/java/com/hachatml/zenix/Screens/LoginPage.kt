package com.hachatml.zenix.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hachatml.zenix.helpbutton.HelpButton

@Composable
fun LoginColumn() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
            //.align(Alignment.Center)
    ) {
        HelpButton()
    }
}