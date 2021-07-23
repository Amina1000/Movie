package com.example.ammymovie.ui.common

import com.example.ammymovie.domain.model.Movie
import com.example.ammymovie.domain.model.MovieDTO

sealed class AppState {
    // Добавила второй параметр лист, для второго списка
    data class Success(val movieDataPlay: List<Movie>, val movieDataCome: List<Movie>) : AppState()
    data class SuccessDetails(val movieDTO: MovieDTO): AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
