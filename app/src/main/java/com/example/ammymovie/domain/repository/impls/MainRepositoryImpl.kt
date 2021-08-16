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

    override fun getMovieFromLocalStorage(onSuccess: (MovieListDTO) -> Unit,adultAdded:Boolean) {
        roomRepo.getMovieFromLocalStorage({
            if (it.isNotEmpty()) {
                onSuccess(it)
            }
        },adultAdded)
    }

    override fun getNowPlayingFromServer(lan: String, callback: Callback<MovieListDTO>, page: Int,adultAdded:Boolean) {
        webRepo.getNowPlayingFromServer(lan, callback, page,adultAdded)
    }

    override fun getUpcomingFromServer(lan: String, callback: Callback<MovieListDTO>, page: Int,adultAdded:Boolean) {
        webRepo.getUpcomingFromServer(lan, callback, page,adultAdded)
    }

    override fun getSearchingMovieServer(
        query: String,
        lan: String,
        callback: Callback<MovieListDTO>,
        page: Int,
        adultAdded: Boolean
    ) {
        webRepo.getSearchingMovieServer(query, lan, callback,page,adultAdded)
    }

    override fun saveEntity(movieListDTO: MovieListDTO) {
        roomRepo.saveEntity(movieListDTO)
    }
}