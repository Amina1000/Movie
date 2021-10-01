package com.cocos.ammymovie.domain.repository.impls.web

import com.cocos.ammymovie.BuildConfig
import com.cocos.ammymovie.domain.repository.impls.web.api.MovieAPI
import com.cocos.ammymovie.domain.model.MovieDTO
import com.cocos.ammymovie.domain.model.MovieListDTO
import com.cocos.ammymovie.domain.repository.impls.web.api.MovieListAPI
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * homework com.example.ammymovie.domain.repository
 *
 * @author Amina
 * 23.07.2021
 */

class RemoteDataSource {
    private val baseUrl = "https://api.themoviedb.org/3/"

    private val movieApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .client(createOkHttpClient(MovieApiInterceptor()))
        .build().create(MovieAPI::class.java)

    private val movieListApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .client(createOkHttpClient(MovieApiInterceptor()))
        .build().create(MovieListAPI::class.java)

    fun getMovieDetails(movieId: Int, lan:String, callback: Callback<MovieDTO>) {
        movieApi.getMovie(movieId, BuildConfig.AMMY_API_KEY,lan).enqueue(callback)
    }

    fun getNowPlayingList(lan:String, callback: Callback<MovieListDTO>,page:Int,adultAdded:Boolean) {
        movieListApi.getNowPlayMovie(BuildConfig.AMMY_API_KEY,lan,page,adultAdded).enqueue(callback)
    }
    fun getUpcomingList(lan:String, callback: Callback<MovieListDTO>,page: Int,adultAdded:Boolean) {
        movieListApi.getUpcomingMovie(BuildConfig.AMMY_API_KEY,lan,page,adultAdded).enqueue(callback)
    }
    fun getSearchingMovieServer(query: String, lan: String, callback: Callback<MovieListDTO>, page:Int,adultAdded:Boolean) {
        movieListApi.getSearchingMovie(BuildConfig.AMMY_API_KEY,lan,page,query,adultAdded).enqueue(callback)
    }
    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    inner class MovieApiInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }


}