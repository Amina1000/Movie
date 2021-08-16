package com.example.ammymovie.domain.repository.impls.web

import com.example.ammymovie.domain.model.MovieListDTO
import retrofit2.Callback

class WebMainRepository(private val remoteDataSource: RemoteDataSource){
    fun getNowPlayingFromServer(lan: String,callback: Callback<MovieListDTO>,page:Int,adultAdded:Boolean) {
        remoteDataSource.getNowPlayingList(lan, callback,page,adultAdded)
    }
    fun getUpcomingFromServer(lan: String, callback: Callback<MovieListDTO>, page:Int,adultAdded:Boolean) {
        remoteDataSource.getUpcomingList(lan, callback,page,adultAdded)
    }
    fun getSearchingMovieServer(query: String, lan: String, callback: Callback<MovieListDTO>, page:Int,adultAdded:Boolean) {
        remoteDataSource.getSearchingMovieServer(query, lan, callback,page,adultAdded)
    }
}