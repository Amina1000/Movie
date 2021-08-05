package com.example.ammymovie.domain.repository.impls.room

import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.MovieListDTO
import com.example.ammymovie.domain.repository.MainRepository
import retrofit2.Callback

class RoomMainRepositoryImpl(dao: MovieRepoDao) : MainRepository {
    override fun getNowPlayingFromLocalStorage(lan: String,callback: Callback<MovieListDTO>,page:Int){
        //TODO
    }
    override fun getUpcomingFromLocalStorage(lan: String,callback: Callback<MovieListDTO>,page:Int){
        //TODO
    }

    override fun saveEntity(movieDTO: MovieDTO) {
        TODO("Not yet implemented")
    }
}