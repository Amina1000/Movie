package com.cocos.ammymovie.domain.repository.impls.web

import com.cocos.ammymovie.domain.model.MovieDTO
import retrofit2.Callback

/**
 * homework com.example.ammymovie.domain.repository
 *
 * @author Amina
 * 23.07.2021
 */

class WebDetailsRepository(private val remoteDataSource: RemoteDataSource) {

    fun getMovieDetailsFromServer(
        movieId: Int?,
        lan: String,
        callback: Callback<MovieDTO>
    ) {
        movieId?.let {
            remoteDataSource.getMovieDetails(it, lan, callback)
        }
    }
}

