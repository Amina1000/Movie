package com.example.ammymovie.domain.repository

import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.MovieListDTO
import retrofit2.Callback


/**
 * homework com.example.ammymovie.domain.repository
 *
 * @author Amina
 * 23.07.2021
 */
interface DetailsRepository {
    fun getMovieDetailsFromServer(movieId: Int?, lan:String, callback: Callback<MovieDTO>)
    fun saveDetails(movieDTO: MovieDTO)
}
