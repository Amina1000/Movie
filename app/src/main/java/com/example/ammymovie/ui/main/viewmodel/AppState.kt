package com.example.ammymovie.ui.main.viewmodel

import com.example.ammymovie.ui.main.model.Movie

sealed class AppState{
    data class Success(val movieDataPlay: List<Movie>, val movieDataCome: List<Movie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
