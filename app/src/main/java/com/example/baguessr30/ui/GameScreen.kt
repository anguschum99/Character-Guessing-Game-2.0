package com.example.baguessr30.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baguessr30.R
import com.example.baguessr30.data.DataSource.GAME_LENGTH
import com.example.baguessr30.ui.theme.Shapes

@Composable
fun LayoutApp() {

}


@Composable
fun GameLayout(
    userInput: String,
    onValueChange: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    onUserError: Boolean,
    gameProgress: Int,
    studentPic: Int,
    finishGame: () -> Unit,
    skipButton: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .padding(18.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$gameProgress/10",
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                        .align(Alignment.End),
                    style = MaterialTheme.typography.bodyLarge
                )
                Image(
                    painter = painterResource(studentPic),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(Shapes.large)
                )

                Text(
                    text = "Who is this Character",
                    style = MaterialTheme.typography.headlineSmall
                )

                OutlinedTextField(
                    value = userInput,
                    onValueChange = onValueChange,
                    singleLine = true,
                    shape = Shapes.large,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        disabledContainerColor = MaterialTheme.colorScheme.surface,
                    ),
                    label = {
                        if (onUserError) {
                            Text(text = "WRONG ANSWER")
                        } else {
                            Text(text = "Enter Your Answer Here")
                        }
                    },
                    keyboardActions = KeyboardActions(
                        onDone = { onKeyboardDone() }
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    isError = onUserError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Row(Modifier.fillMaxWidth()) {
                    OutlinedButton(onClick = skipButton,
                        modifier = Modifier.weight(0.5f)) {
                        Text(text = "Skip")
                    }

                    Spacer(modifier = Modifier.weight(0.1f))

                    Button(
                        onClick = onKeyboardDone,
                        enabled = gameProgress != GAME_LENGTH,
                        modifier = Modifier.weight(0.5f)
                    ) {
                        Text(text = "Submit")
                    }

                }



//                Button(onClick = finishGame,
//                enabled = gameProgress == GAME_LENGTH
//                ) {
//                    Text(text = "Finish Game")
//
//                }

            }
        }
    }


}


@Preview
@Composable
fun GameLayoutPreview() {
    GameLayout(
        userInput = "",
        onValueChange = {},
        onKeyboardDone = {},
        onUserError = false,
        gameProgress = 1,
        studentPic = R.drawable.mina,
        finishGame = {},
        {}
    )
}