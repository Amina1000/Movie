package com.example.ammymovie.domain.repository.impls

import android.os.Handler
import com.example.ammymovie.domain.model.MovieListDTO
import com.example.ammymovie.domain.repository.MainRepository
import com.example.ammymovie.domain.repository.impls.room.MovieRepoDao
import com.example.ammymovie.domain.repository.impls.room.RoomMainRepository
import com.example.ammymovie.domain.repository.impls.web.RemoteDataSource
import com.example.ammymovie.domain.repository.impls.web.WebMainRepository
import retrofit2.Callback

class MainRepositoryImpl(
    remoteDataSource: RemoteDataSource,
    localDataSource: MovieRepoDao,
    handler: Handler
) : MainRepository {

    private val webRepo = WebMainRepository(remoteDataSource)
    private val roomRepo = RoomMainRepository(localDataSource, handler)

    override fun getNowPlayingFromLocalStorage(onSuccess: (MovieListDTO) -> Unit) {
        roomRepo.getNowPlayingFromLocalStorage {
            if (it.isNotEmpty()) {
                onSuccess(it)
            }
        }
    }

    override fun getUpcomingFromLocalStorage(onSuccess: (MovieListDTO) -> Unit) {
        roomRepo.getUpcomingFromLocalStorage {
            if (it.isNotEmpty()) {
                onSuccess(it)
            }
        }
    }

    override fun getNowPlayingFromServer(lan: String, callback: Callback<MovieListDTO>, page: Int) {
        webRepo.getNowPlayingFromServer(lan, callback, page)
    }

    override fun getUpcomingFromServer(lan: String, callback: Callback<MovieListDTO>, page: Int) {
        webRepo.getUpcomingFromServer(lan, callback, page)
    }

    override fun saveEntity(movieListDTO: MovieListDTO) {
        roomRepo.saveEntity(movieListDTO)
    }
}