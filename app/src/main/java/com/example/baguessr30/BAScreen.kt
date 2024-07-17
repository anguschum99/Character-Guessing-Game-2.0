package com.example.baguessr30

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.baguessr30.ui.BAViewModel
import com.example.baguessr30.ui.ChooseApp
import com.example.baguessr30.ui.GameLayout
import com.example.baguessr30.ui.StudyApp
import com.example.baguessr30.ui.SummaryScreen
import com.example.baguessr30.ui.TitleScreen

enum class BAScreen(@StringRes val title: Int) {
    Title(title = R.string.blue_archive_guessing_game),
    Study(title = R.string.study),
    Choose(title = R.string.choose_gamemode),
    Game(title = R.string.guessing_game),
    Summary(title = R.string.summary)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BAScreenAppBar(
    currentScreen: BAScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(title = { Text(text = stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)

                }
            }
        }


    )


}


@Composable
fun BAApp(
    viewModel: BAViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = BAScreen.valueOf(
        backStackEntry?.destination?.route ?: BAScreen.Title.name
    )

    Scaffold(
        topBar = {
            BAScreenAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = BAScreen.Title.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = BAScreen.Title.name) {
                TitleScreen(
                    userName = uiState.username,
                    onValueChange = { viewModel.updateName(it) },
                    onNextButtonClicked = { navController.navigate(BAScreen.Choose.name) }
                )
            }

            composable(route = BAScreen.Choose.name) {
                ChooseApp(
                    playGame = { navController.navigate(BAScreen.Game.name) },
                    playStudy = { navController.navigate(BAScreen.Study.name) }
                )
            }

            composable(route = BAScreen.Study.name) {
                StudyApp(goBackButton = { navController.navigate(BAScreen.Choose.name) },
                    playGame = { navController.navigate(BAScreen.Game.name) }
                )
            }


            composable(route = BAScreen.Game.name) {
                GameLayout(
                    userInput = viewModel.userGuess,
                    onValueChange = { viewModel.changeUserGuess(it) },
                    onKeyboardDone = { viewModel.checkUserGuess() },
                    onUserError = uiState.isUserAnswerWrong,
                    gameProgress = uiState.gameProgress,
                    studentPic = uiState.currentPicture.toInt(),
                    finishGame = {},
                    skipButton = { viewModel.skipQuestion() }
                )

                if (uiState.gameOver) {
                    navController.navigate(BAScreen.Summary.name)
                }

            }

            composable(route = BAScreen.Summary.name) {
                SummaryScreen(
                    score = uiState.score,
                    playAgain = {
                        viewModel.resetGame()
                        navController.navigate((BAScreen.Title.name))
                    },
                    name = uiState.username
                )
            }


        }

    }


}