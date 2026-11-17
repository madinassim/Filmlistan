package com.example.filmlistan


import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class UiState(
    val filmer: List<Film> = emptyList(),
    val nyTitel: String = ""
)


class FilmlistanViewModel : ViewModel() {
    private var nastaId = 4

    var uiState by mutableStateOf(
        UiState(filmer = startfilmer)
    )
        private set

    fun uppdateraTitel(nyText: String) {
        uiState = uiState.copy(nyTitel = nyText)
    }

    fun laggTillFilm() {
        val titel = uiState.nyTitel.trim()

        if (titel.isBlank()) return

        val nyFilm = Film(
            id = nastaId,
            titel = titel,
            ar = 2026,
            genre = "Okänd"
        )

        nastaId++

        uiState = uiState.copy(
            filmer = uiState.filmer + nyFilm,
            nyTitel = ""
        )
    }

    fun andraSedd(id: Int) {
        uiState = uiState.copy(
            filmer = uiState.filmer.map { film ->
                if (film.id == id) {
                    film.copy(sedd = !film.sedd)
                } else {
                    film
                }
            }
        )
    }
    fun taBortFilm(id: Int) {
        uiState = uiState.copy(
            filmer = uiState.filmer.filter { film ->
                film.id != id
            }
        )
    }

}

