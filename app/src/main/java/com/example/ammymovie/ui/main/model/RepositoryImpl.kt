package com.example.ammymovie.ui.main.model

import java.util.*

class RepositoryImpl : Repository {

    override fun getMoviesFromServer(): Movie {
        return Movie("Movie Server", Calendar.getInstance().time)
    }

    override fun getNowPlayingFromLocalStorage(): List<Movie> {
        return getNowPlayingMovies()
    }

    override fun getUpcomingFromLocalStorage(): List<Movie> {
        return getUpcomingMovies()
    }

}