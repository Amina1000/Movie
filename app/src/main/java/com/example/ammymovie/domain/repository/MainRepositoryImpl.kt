package com.example.ammymovie.domain.repository

import com.example.ammymovie.domain.model.getNowPlayingMovies
import com.example.ammymovie.domain.model.getUpcomingMovies

class MainRepositoryImpl : MainRepository {
    override fun getNowPlayingFromLocalStorage() = getNowPlayingMovies()
    override fun getUpcomingFromLocalStorage() = getUpcomingMovies()
}