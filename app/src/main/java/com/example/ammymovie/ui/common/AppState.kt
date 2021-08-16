package com.example.ammymovie.ui.common

import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.MovieListDTO
import com.example.ammymovie.domain.model.MovieSection

sealed class AppState {
    // Добавила второй параметр лист, для второго списка
    data class Success(val movieDataList: MovieListDTO) : AppState()
    data class SuccessDetails(val movieDTO: MovieDTO): AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
