package com.example.filmlistan



data class Film(
    val id: Int,
    val titel: String,
    val ar: Int,
    val genre: String,
    val sedd: Boolean = false,
    val bildResId: Int? = null
)

val startfilmer = listOf(
    Film(1, "Norrskensväktaren", 2024, "Äventyr", bildResId = R.drawable.film_bild),
    Film(2, "Den sista bussen", 2021, "Drama"),
    Film(3, "Kodnamn Granit", 2023, "Thriller")
)
