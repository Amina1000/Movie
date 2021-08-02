package com.example.ammymovie.domain.repository.impls.web

import com.example.ammymovie.BuildConfig
import com.example.ammymovie.domain.api.MovieAPI
import com.example.ammymovie.domain.model.MovieDTO
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * homework com.example.ammymovie.domain.repository
 *
 * @author Amina
 * 23.07.2021
 */

class RemoteDataSource {
    private val movieApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(MovieAPI::class.java)

    fun getMovieDetails(movieId: Int, lan:String, callback: Callback<MovieDTO>) {
        movieApi.getMovie(movieId,BuildConfig.AMMY_API_KEY,lan).enqueue(callback)
    }
}