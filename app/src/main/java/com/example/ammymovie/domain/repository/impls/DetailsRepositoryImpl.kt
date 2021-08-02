package com.example.ammymovie.domain.repository.impls

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.ammymovie.domain.api.getLink
import com.example.ammymovie.domain.repository.DetailsRepository
import com.example.ammymovie.domain.repository.impls.web.RemoteDataSource
import okhttp3.Callback

/**
 * homework com.example.ammymovie.domain.repository
 *
 * @author Amina
 * 23.07.2021
 */

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource): DetailsRepository {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun getMovieDetailsFromServer(movieLink:Any,callback: Callback) {
        val requestLink =  getLink(movieLink)
        remoteDataSource.getMovieDetails(requestLink, callback)
        }
}

