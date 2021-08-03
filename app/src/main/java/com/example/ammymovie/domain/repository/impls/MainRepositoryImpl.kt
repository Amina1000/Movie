package com.example.ammymovie.domain.repository.impls

import com.example.ammymovie.domain.model.MovieListDTO
import com.example.ammymovie.domain.repository.MainRepository
import com.example.ammymovie.domain.repository.impls.room.RoomMainRepositoryImpl
import com.example.ammymovie.domain.repository.impls.web.RemoteDataSource
import com.example.ammymovie.domain.repository.impls.web.WebMainRepositoryImpl
import retrofit2.Callback

class MainRepositoryImpl(private val remoteDataSource: RemoteDataSource) : MainRepository {

    private val webRepo = WebMainRepositoryImpl(remoteDataSource)
    private val cacheRepo = RoomMainRepositoryImpl()

    override fun getNowPlayingFromLocalStorage(lan: String, callback: Callback<MovieListDTO>,page:Int) {
        webRepo.getNowPlayingFromLocalStorage(lan, callback,1)

    }

    override fun getUpcomingFromLocalStorage(lan: String, callback: Callback<MovieListDTO>,page:Int) {
        webRepo.getUpcomingFromLocalStorage(lan, callback,2)
    }
}