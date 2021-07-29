package com.example.ammymovie.domain.api

import com.example.ammymovie.BuildConfig

/**
 * homework com.example.ammymovie.domain.api
 *
 * @author Amina
 * 23.07.2021
 */
private const val apiKey = BuildConfig.AMMY_API_KEY
private const val MAIN_LINK = "https://api.themoviedb.org/3/"

fun getLink(movieLink: Any?, lan: String = "ru-RU"): String {
    return when (movieLink) {
        is Int -> MAIN_LINK + "movie/${movieLink}?api_key=${apiKey}&language=${lan}"
        is String -> MAIN_LINK + "search/movie?api_key=${apiKey}&query=${movieLink}"
        else -> MAIN_LINK + "movie/now_playing?api_key=${apiKey}&language=${lan}"
    }
}