package com.example.baguessr30.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baguessr30.R
import com.example.baguessr30.ui.theme.Shapes

@Composable
fun TitleScreen(
    userName: String,
    onValueChange: (String) -> Unit,
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ba_logo_1_webp),
            contentDescription = null
        )
        Text(
            text = "Guessing Game",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = userName
        )

        UserInput(
            userInput = userName,
            onValueChange = onValueChange,

            )

        Button(
            onClick = onNextButtonClicked,
            enabled = userName.isNotEmpty()
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}

@Composable
fun UserInput(
    userInput: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = userInput,
        onValueChange = onValueChange,
        shape = Shapes.large,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
        ),
        label = {
            Text(text = "Enter Your Name Here")
        },
        singleLine = true,
        modifier = Modifier
            .padding(10.dp)
            .testTag("textfield")
    )
}




@Preview
@Composable
fun TitleScreenPreview() {
    TitleScreen(userName = "q", onValueChange = {})
}