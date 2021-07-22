package com.example.ammymovie.ui.main.model

class RepositoryImpl : Repository {
    override fun getNowPlayingFromLocalStorage() = getNowPlayingMovies()
    override fun getUpcomingFromLocalStorage() = getUpcomingMovies()
}