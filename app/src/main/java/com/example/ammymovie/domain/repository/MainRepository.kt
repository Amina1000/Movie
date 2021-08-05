package com.example.ammymovie.domain.repository

import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.MovieListDTO
import retrofit2.Callback

interface MainRepository {
    fun getNowPlayingFromLocalStorage(lan: String,callback: Callback<MovieListDTO>,page:Int)
    fun getUpcomingFromLocalStorage(lan: String,callback: Callback<MovieListDTO>,page:Int)
    fun saveEntity(movieDTO: MovieDTO)
}