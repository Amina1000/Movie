package com.example.ammymovie.domain.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.ammymovie.domain.api.getLink

/**
 * homework com.example.ammymovie.domain.repository
 *
 * @author Amina
 * 23.07.2021
 */

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource):DetailsRepository {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun getMovieDetailsFromServer(movieLink:Any,listener: MovieLoaderListener) {
        val requestLink =  getLink(movieLink)
        remoteDataSource.loadMovie(requestLink, listener)
        }
}

