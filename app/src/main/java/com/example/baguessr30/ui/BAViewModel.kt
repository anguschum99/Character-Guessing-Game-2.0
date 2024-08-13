package com.example.baguessr30.ui

import android.provider.ContactsContract.Data
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.baguessr30.data.BAUiState
import com.example.baguessr30.data.DataSource
import com.example.baguessr30.data.DataSource.GAME_LENGTH
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BAViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BAUiState())
    val uiState: StateFlow<BAUiState> = _uiState.asStateFlow()

    private lateinit var currentStudent: String
    private lateinit var currentPic: String

    // Current Guess by User
    var userGuess by mutableStateOf("")
        private set

    // Used Name List
    private var usedNames = mutableSetOf<String>()

    // temporary name holder
    private var temporary = DataSource.studentList.random()

    init {
        resetGame()
    }

    // Sets Username
    fun updateName(input: String) {
        _uiState.update { currentState ->
            currentState.copy(
                username = input
            )
        }
    }


    fun resetGame() {
        usedNames.clear()
        _uiState.value =
            BAUiState(
                currentStudent = setCurrentStudent(),
                currentPicture = setCurrentPicture()
            )
    }


    private fun setCurrentPicture(): String {

        return temporary.imageResourceId.toString()
    }

    private fun setCurrentStudent(): String {
        temporary = DataSource.studentList.random()

        return if (usedNames.contains(temporary.name)) {
            setCurrentStudent()
        } else {
            currentStudent = temporary.name
            setCurrentPicture()
            usedNames.add(temporary.name)
            return currentStudent
        }
    }

    // Set Current Guess
    fun changeUserGuess(input: String) {
        userGuess = input
    }

    fun checkUserGuess() {
        if (userGuess.equals(currentStudent, ignoreCase = true)) {
            val addScore = _uiState.value.score.plus(1)
            updateGameState(addScore)
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isUserAnswerWrong = true
                )
            }
        }
        changeUserGuess("")
    }


    fun updateGameState(newScore: Int) {
        if (usedNames.size.equals(GAME_LENGTH)) {
            _uiState.update { currentState ->
                currentState.copy(
                    gameOver = true,
                    score = newScore,

                    )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    score = newScore,
                    gameProgress = _uiState.value.gameProgress.inc(),
                    currentStudent = setCurrentStudent(),
                    currentPicture = setCurrentPicture(),
                    isUserAnswerWrong = false
                )
            }
        }
    }

    fun skipQuestion() {
        updateGameState(_uiState.value.score)
        changeUserGuess("")
    }


}