package com.example.ammymovie.domain.repository

import com.example.ammymovie.domain.model.MovieDTO

/**
 * homework com.example.ammymovie.domain.repository
 *
 * @author Amina
 * 23.07.2021
 */
interface DetailsRepository {
    fun getMovieDetailsFromServer(movieLink:Any,listener: MovieLoaderListener)
}
interface MovieLoaderListener {
    fun onLoaded(movieDTO: MovieDTO)
    fun onFailed(throwable: Throwable) }