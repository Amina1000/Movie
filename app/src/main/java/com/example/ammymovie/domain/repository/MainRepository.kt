package com.example.ammymovie.domain.repository

import com.example.ammymovie.domain.model.MovieListDTO
import retrofit2.Callback

interface MainRepository {
    fun getNowPlayingFromLocalStorage(onSuccess: (MovieListDTO) -> Unit,adultAdded:Boolean)
    fun getUpcomingFromLocalStorage(onSuccess: (MovieListDTO) -> Unit,adultAdded:Boolean)
    fun getNowPlayingFromServer(lan: String,callback: Callback<MovieListDTO>,page:Int,adultAdded:Boolean)
    fun getUpcomingFromServer(lan: String,callback: Callback<MovieListDTO>,page:Int,adultAdded:Boolean)
    fun saveEntity(movieListDTO: MovieListDTO)
}