package com.example.ammymovie.domain.repository

import com.example.ammymovie.domain.model.Movie

interface MainRepository {
    fun getNowPlayingFromLocalStorage(): List<Movie>
    fun getUpcomingFromLocalStorage(): List<Movie>
}