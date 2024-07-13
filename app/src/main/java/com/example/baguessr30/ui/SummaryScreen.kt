package com.example.baguessr30.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.baguessr30.R

@Composable
fun SummaryScreen(
    score: Int,
    name: String,
    playAgain: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = "Congratulations $name",
            style = MaterialTheme.typography.displaySmall
        )

        Text(text = "You have Scored $score points", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.size(30.dp))


        Image(
            painter =
            if (score < 5) {
                painterResource(id = R.drawable._690854862127700)
            } else {
                painterResource(id = R.drawable.money)
            },
            contentDescription = null
        )

        Spacer(modifier = Modifier.size(30.dp))


        Button(onClick = playAgain) {
            Text(text = "Play Again")
        }
    }

}