package com.example.ammymovie.domain.repository

import android.os.Handler
import com.example.ammymovie.domain.model.MovieListDTO
import retrofit2.Callback

interface MainRepository {
    fun getNowPlayingFromLocalStorage(onSuccess: (MovieListDTO) -> Unit)
    fun getUpcomingFromLocalStorage(onSuccess: (MovieListDTO) -> Unit)
    fun getNowPlayingFromServer(lan: String,callback: Callback<MovieListDTO>,page:Int)
    fun getUpcomingFromServer(lan: String,callback: Callback<MovieListDTO>,page:Int)
    fun saveEntity(movieListDTO: MovieListDTO)
}