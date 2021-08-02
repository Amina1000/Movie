package com.example.ammymovie.domain.repository.impls

import com.example.ammymovie.domain.model.getNowPlayingMovies
import com.example.ammymovie.domain.model.getUpcomingMovies
import com.example.ammymovie.domain.repository.MainRepository

class MainRepositoryImpl : MainRepository {
    override fun getNowPlayingFromLocalStorage() = getNowPlayingMovies()
    override fun getUpcomingFromLocalStorage() = getUpcomingMovies()
}