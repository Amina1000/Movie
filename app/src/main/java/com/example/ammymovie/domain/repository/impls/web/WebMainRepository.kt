package com.example.ammymovie.domain.repository.impls.web

import com.example.ammymovie.domain.model.MovieListDTO
import retrofit2.Callback

class WebMainRepository(private val remoteDataSource: RemoteDataSource){
    fun getNowPlayingFromServer(lan: String,callback: Callback<MovieListDTO>,page:Int) {
        remoteDataSource.getNowPlayingList(lan, callback,page)
    }
    fun getUpcomingFromServer(lan: String, callback: Callback<MovieListDTO>, page:Int) {
        remoteDataSource.getUpcomingList(lan, callback,page)
    }
}