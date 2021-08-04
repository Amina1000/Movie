package com.example.ammymovie.domain.repository.impls.web

import com.example.ammymovie.domain.model.MovieListDTO
import com.example.ammymovie.domain.repository.MainRepository
import retrofit2.Callback

class WebMainRepositoryImpl(private val remoteDataSource: RemoteDataSource) : MainRepository {
    override fun getNowPlayingFromLocalStorage(lan: String,callback: Callback<MovieListDTO>,page:Int) {
        remoteDataSource.getNowPlayingList(lan, callback,page)
    }
    override fun getUpcomingFromLocalStorage(lan: String,callback: Callback<MovieListDTO>,page:Int) {
        remoteDataSource.getUpcomingList(lan, callback,page)
    }
}