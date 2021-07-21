package com.example.ammymovie.ui.main.model

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Movie(
    // Класс для хранения данных
    val id:Int,
    val name: String,
    val nameOrigin: String = "",
    val releaseDate: Date,
    var favorite: Boolean = false,
    val rating: String = "",
    val description: String = "Описание фильма",
    val genre: String = "Жанр",
    val duration: String = "",
    val budget: String = "",
    val revenue: String = ""
) : Parcelable

fun getNowPlayingMovies()=listOf(
        Movie(0,"Фильм 1", "Movie 1", Calendar.getInstance().time, true, "8.1"),
        Movie(1,"Фильм 2", "Movie 2", Calendar.getInstance().time, false, "6.8"),
        Movie(2,"Фильм 3", "Movie 3", Calendar.getInstance().time, true, "7.4"),
        Movie(3,"Фильм 4", "Movie 4", Calendar.getInstance().time, false, "5.2"),
        Movie(4,"Фильм 9", "Movie 4", Calendar.getInstance().time, false, "5.2"),
        Movie(5,"Фильм 10", "Movie 4", Calendar.getInstance().time, false, "5.2")
    )

fun getUpcomingMovies()= listOf(
        Movie(6,"Фильм 5", "Movie 5", Calendar.getInstance().time, false, "", "", "Фантастика"),
        Movie(7,"Фильм 6", "Movie 6", Calendar.getInstance().time, false, "", "", "Мелодрама"),
        Movie(8,"Фильм 7", "Movie 7", Calendar.getInstance().time, false, "", "", "Боевик"),
        Movie(9,"Фильм 8", "Movie 8", Calendar.getInstance().time, false, "", "", "Триллер"),
        Movie(10,"Фильм 11", "Movie 8", Calendar.getInstance().time, false, "", "", "Комедия"),
        Movie(11,"Фильм 12", "Movie 8", Calendar.getInstance().time, false, "", "", "Документальный")
    )


