package com.example.ammymovie.domain.repository

import com.example.ammymovie.domain.model.getNowPlayingMovies
import com.example.ammymovie.domain.model.getUpcomingMovies

class RepositoryImpl : Repository {
    override fun getNowPlayingFromLocalStorage() = getNowPlayingMovies()
    override fun getUpcomingFromLocalStorage() = getUpcomingMovies()
}