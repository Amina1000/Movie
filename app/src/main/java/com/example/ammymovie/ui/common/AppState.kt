package com.example.ammymovie.ui.common

import com.example.ammymovie.domain.model.Movie

sealed class AppState {
    // Добавила второй параметр лист, для второго списка
    data class Success(val movieDataPlay: List<Movie>, val movieDataCome: List<Movie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
