package com.example.ammymovie.ui.main.model

interface Repository {
    fun getMoviesFromServer(): Movie
    fun getNowPlayingFromLocalStorage(): List<Movie>
    fun getUpcomingFromLocalStorage(): List<Movie>
}