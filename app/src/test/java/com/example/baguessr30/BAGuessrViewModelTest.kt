package com.example.baguessr30

import com.example.baguessr30.data.DataSource.GAME_LENGTH
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import com.example.baguessr30.ui.BAViewModel
import org.junit.Test
import org.junit.Assert.assertTrue


class BAGuessrViewModelTest {
    private val viewModel = BAViewModel()

    @Test
    fun viewModel_CorrectWordGuessed_ScoreUpdated(){
        var uiState = viewModel.uiState.value
        val correctStudent = uiState.currentStudent

        viewModel.changeUserGuess(correctStudent)
        viewModel.checkUserGuess()

        uiState = viewModel.uiState.value

        assertFalse(uiState.isUserAnswerWrong)
        assertEquals(1,uiState.score)
    }

    @Test
    fun viewModel_IncorrectGuess(){
        val incorrectWord = "lol"

        viewModel.changeUserGuess(incorrectWord)
        viewModel.checkUserGuess()

        val uiState = viewModel.uiState.value


        assertEquals(0,uiState.score)
        assertTrue(uiState.isUserAnswerWrong)
    }

    @Test
    fun vieModel_Initialization_FirstWordLoaded(){
        val gameUiState = viewModel.uiState.value

        assertTrue(gameUiState.gameProgress == 1)
        assertTrue(gameUiState.score == 0)
        assertFalse(gameUiState.isUserAnswerWrong)
        assertFalse(gameUiState.gameOver)

    }

    @Test
    fun viewModel_AllWordsGuessed_UiStateUpdatedCorrectly(){
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value
        var correctGuess = currentGameUiState.currentStudent
        repeat(GAME_LENGTH){
            expectedScore += 1
            viewModel.changeUserGuess(correctGuess)
            viewModel.checkUserGuess()
            currentGameUiState = viewModel.uiState.value
            correctGuess = currentGameUiState.currentStudent
            assertEquals(expectedScore, currentGameUiState.score)
        }
        assertEquals(GAME_LENGTH,currentGameUiState.gameProgress)
        assertTrue(currentGameUiState.gameOver)
    }

    @Test
    fun viewModel_skipQuestion_UiStateUpdateCorrectly(){
        var gameUiState = viewModel.uiState.value

        viewModel.skipQuestion()
        gameUiState = viewModel.uiState.value
        assertEquals(2,gameUiState.gameProgress)
        assertEquals(0,gameUiState.score)
    }

}