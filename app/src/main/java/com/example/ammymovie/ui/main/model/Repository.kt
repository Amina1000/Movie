package com.example.ammymovie.ui.main.model

interface Repository {
    fun getNowPlayingFromLocalStorage(): List<Movie>
    fun getUpcomingFromLocalStorage(): List<Movie>
}