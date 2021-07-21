package com.example.ammymovie.ui.main.model

import java.util.*

class RepositoryImpl : Repository {
//Воспользуемся преимуществами языка
    override fun getMoviesFromServer()= Movie(12,
        "Фильм из сервера","Movie Server", Calendar.getInstance().time)
    override fun getNowPlayingFromLocalStorage()=getNowPlayingMovies()
    override fun getUpcomingFromLocalStorage()= getUpcomingMovies()
}