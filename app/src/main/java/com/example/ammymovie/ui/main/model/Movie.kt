package com.example.ammymovie.ui.main.model

import java.util.*

data class Movie(
    // Класс для хранения данных
    val name:String,
    val releaseDate:Date,
    val favorite:Boolean=false,
    val rating: String="")

fun getNowPlayingMovies(): List<Movie> {
    return listOf(
        Movie("Movie 1",Calendar.getInstance().time,true,"8.1"),
        Movie("Movie 2",Calendar.getInstance().time,false,"6.8"),
        Movie("Movie 3",Calendar.getInstance().time,true,"7.4"),
        Movie("Movie 4",Calendar.getInstance().time,false,"5.2")
    )
}
fun getUpcomingMovies(): List<Movie> {
    return listOf(
        Movie("Movie 5",Calendar.getInstance().time),
        Movie("Movie 6",Calendar.getInstance().time),
        Movie("Movie 7",Calendar.getInstance().time),
        Movie("Movie 8",Calendar.getInstance().time)
    )
}