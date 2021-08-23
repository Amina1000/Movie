package com.cocos.ammymovie.ui.common

import com.cocos.ammymovie.domain.model.MovieDTO
import com.cocos.ammymovie.domain.model.MovieListDTO
import com.cocos.ammymovie.domain.model.MovieSection

sealed class AppState {
    // Добавила второй параметр лист, для второго списка
    data class Success(val movieDataList: MovieListDTO) : AppState() {

        companion object {
           var movieComoList= MovieListDTO(ArrayList())
        }

        init {
            movieDataList.results?.let {
                movieComoList.results?.addAll(it)
            }

        }

        fun getMovieList(): MovieListDTO {
            return movieComoList
        }
    }

    data class SuccessDetails(val movieDTO: MovieDTO) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
