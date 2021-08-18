package com.example.ammymovie.domain.repository.impls

import android.os.Handler
import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.MovieListDTO
import com.example.ammymovie.domain.repository.DetailsRepository
import com.example.ammymovie.domain.repository.MainRepository
import com.example.ammymovie.domain.repository.impls.room.MovieRepoDao
import com.example.ammymovie.domain.repository.impls.room.RoomMainRepository
import com.example.ammymovie.domain.repository.impls.web.RemoteDataSource
import com.example.ammymovie.domain.repository.impls.web.WebDetailsRepository
import com.example.ammymovie.domain.repository.impls.web.WebMainRepository
import retrofit2.Callback

class DetailsRepositoryImpl(
    remoteDataSource: RemoteDataSource,
    localDataSource: MovieRepoDao,
    handler: Handler
) : DetailsRepository {

    private val webRepo = WebDetailsRepository(remoteDataSource)
    private val roomRepo = RoomMainRepository(localDataSource, handler)

    override fun getMovieDetailsFromServer(
        movieId: Int?,
        lan: String,
        callback: Callback<MovieDTO>
    ) {
        webRepo.getMovieDetailsFromServer(movieId,lan,callback)
    }


    override fun saveDetails(movieDTO: MovieDTO) {
        roomRepo.saveDetails(movieDTO)
    }
}