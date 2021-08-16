package com.example.ammymovie.domain.repository.impls.web.api

import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.MovieListDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * homework com.example.ammymovie.domain.repository.impls.web.api
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
interface MovieListAPI {
    @GET("movie/now_playing")
    fun getNowPlayMovie(
        @Query("api_key") apiKey: String,
        @Query("language") lan: String,
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean
    ): Call<MovieListDTO>

    @GET("movie/upcoming")
    fun getUpcomingMovie(
        @Query("api_key") apiKey: String,
        @Query("language") lan: String,
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean
    ): Call<MovieListDTO>

    @GET("search/movie")
    fun getSearchingMovie(
        @Query("api_key") apiKey: String,
        @Query("language") lan: String,
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean
    ): Call<MovieListDTO>
}