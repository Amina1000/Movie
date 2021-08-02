package com.example.ammymovie.domain.repository.impls.web

import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * homework com.example.ammymovie.domain.repository
 *
 * @author Amina
 * 23.07.2021
 */

@RequiresApi(Build.VERSION_CODES.N)
class RemoteDataSource {

    fun getMovieDetails(requestLink: String, callback: Callback) {
        val builder: Request.Builder = Request.Builder().apply {
            url(requestLink)
        }
        OkHttpClient().newCall(builder.build()).enqueue(callback)
    }

}