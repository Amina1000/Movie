package com.cocos.ammymovie.domain.repository

import com.cocos.ammymovie.domain.model.MovieListDTO
import retrofit2.Callback

interface MainRepository {
    fun getMovieFromLocalStorage(onSuccess: (MovieListDTO) -> Unit,adultAdded:Boolean)
    fun getNowPlayingFromServer(lan: String,callback: Callback<MovieListDTO>,page:Int,adultAdded:Boolean)
    fun getUpcomingFromServer(lan: String,callback: Callback<MovieListDTO>,page:Int,adultAdded:Boolean)
    fun getSearchingMovieServer(query:String, lan: String,callback: Callback<MovieListDTO>,page:Int,adultAdded:Boolean)
    fun saveEntity(movieListDTO: MovieListDTO)
}