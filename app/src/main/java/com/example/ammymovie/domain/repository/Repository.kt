package com.example.ammymovie.domain.repository

import com.example.ammymovie.domain.model.Movie

interface Repository {
    fun getNowPlayingFromLocalStorage(): List<Movie>
    fun getUpcomingFromLocalStorage(): List<Movie>
}