package com.example.ammymovie.domain.repository.impls

import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.repository.DetailsRepository
import com.example.ammymovie.domain.repository.impls.web.RemoteDataSource
import retrofit2.Callback

/**
 * homework com.example.ammymovie.domain.repository
 *
 * @author Amina
 * 23.07.2021
 */

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsRepository {

    override fun getMovieDetailsFromServer(
        movieId: Int,
        lan: String,
        callback: Callback<MovieDTO>
    ) {
        remoteDataSource.getMovieDetails(movieId, lan, callback)
    }
}

