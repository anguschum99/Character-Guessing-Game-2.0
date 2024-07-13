package com.example.baguessr30.data

data class BAUiState
    (
    val username: String = "",
    val currentStudent: String = "",
    val isUserAnswerWrong: Boolean = false,
    val currentPicture: String = "",
    val score: Int = 0,
    val gameProgress: Int = 1,
            val gameOver: Boolean = false
    )