package com.example.ammymovie.domain.repository

import okhttp3.Callback

/**
 * homework com.example.ammymovie.domain.repository
 *
 * @author Amina
 * 23.07.2021
 */
interface DetailsRepository {
    fun getMovieDetailsFromServer(movieLink:Any,callback: Callback)
}
