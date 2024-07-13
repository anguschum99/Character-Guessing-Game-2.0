package com.example.baguessr30.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ChooseApp(
    playGame: () -> Unit,
    playStudy: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = playGame) {
            Text(text = "Play Game")
        }

        Button(onClick = playStudy) {
            Text(text = "Play Study")
        }
    }
}

@Preview
@Composable
fun ChooseScreenPreview() {
    ChooseApp(
        playGame = {},
        playStudy = {}
    )
}