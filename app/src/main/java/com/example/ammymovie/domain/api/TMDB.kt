package com.example.ammymovie.domain.api

import com.example.ammymovie.domain.model.MovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * homework com.example.ammymovie.domain.api
 *
 * @author Amina
 * 23.07.2021
 */

interface MovieAPI {
    @GET("movie/{id}")
    fun getMovie(
        @Path("id")movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lan: String
    ): Call<MovieDTO>
}