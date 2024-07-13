package com.example.baguessr30.data

import androidx.annotation.DrawableRes
import com.example.baguessr30.R

object DataSource {
    data class Student(
        @DrawableRes val imageResourceId: Int,
        val name: String

    )

    const val GAME_LENGTH = 10


    val studentList = listOf<Student>(
        Student(R.drawable.hatsune_miku, "Hatsune Miku"),
        Student(R.drawable.fuuka__new_year_, "Fuuka"),
        Student(R.drawable.hare__camping_, "Hare"),
        Student(R.drawable.hifumi__swimsuit_, "Hifumi"),
        Student(R.drawable.hiyori, "Hiyori"),
        Student(R.drawable.hinata__swimsuit_, "Hinata"),
        Student(R.drawable.hanako__swimsuit_, "Hanako"),
        Student(R.drawable.himari, "Himari"),
        Student(R.drawable.eimi__swimsuit_, "Eimi"),
        Student(R.drawable.kotama__camping_, "Kotama"),
        Student(R.drawable.mina,"Mina"),
        Student(R.drawable.toki__bunny_girl_,"Toki"),
        Student(R.drawable.wakamo,"Wakamo")
    )
}