package com.example.ammymovie.ui.common

import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.MovieListDTO

sealed class AppState {
    // Добавила второй параметр лист, для второго списка
    data class SuccessPlay(val movieDataPlay: MovieListDTO) : AppState()
    data class SuccessCome(val movieDataCome: MovieListDTO) : AppState()
    data class SuccessDetails(val movieDTO: MovieDTO): AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
