package com.example.ammymovie.domain.repository.impls.room

import com.example.ammymovie.domain.model.MovieListDTO
import com.example.ammymovie.domain.repository.MainRepository
import retrofit2.Callback

class RoomMainRepositoryImpl : MainRepository {
    override fun getNowPlayingFromLocalStorage(lan: String,callback: Callback<MovieListDTO>,page:Int){
        //TODO
    }
    override fun getUpcomingFromLocalStorage(lan: String,callback: Callback<MovieListDTO>,page:Int){
        //TODO
    }
}