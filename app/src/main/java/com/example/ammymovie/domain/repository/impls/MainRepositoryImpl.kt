package com.example.ammymovie.domain.repository.impls

import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.MovieListDTO
import com.example.ammymovie.domain.repository.MainRepository
import com.example.ammymovie.domain.repository.impls.room.MovieRepoDao
import com.example.ammymovie.domain.repository.impls.room.RoomMainRepositoryImpl
import com.example.ammymovie.domain.repository.impls.web.RemoteDataSource
import com.example.ammymovie.domain.repository.impls.web.WebMainRepositoryImpl
import retrofit2.Callback

class MainRepositoryImpl(remoteDataSource: RemoteDataSource,localDataSource: MovieRepoDao) : MainRepository {

    private val webRepo = WebMainRepositoryImpl(remoteDataSource)
    private val roomRepo = RoomMainRepositoryImpl(localDataSource)

    override fun getNowPlayingFromLocalStorage(lan: String, callback: Callback<MovieListDTO>,page:Int) {
        webRepo.getNowPlayingFromLocalStorage(lan, callback,1)

    }

    override fun getUpcomingFromLocalStorage(lan: String, callback: Callback<MovieListDTO>,page:Int) {
        webRepo.getUpcomingFromLocalStorage(lan, callback,2)
    }

    override fun saveEntity(movieDTO: MovieDTO) {
        TODO("Not yet implemented")
    }
}