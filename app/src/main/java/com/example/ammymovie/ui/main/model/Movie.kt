package com.example.ammymovie.ui.main.model

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
        Movie(602223,"Фильм 1", "Movie 1", Calendar.getInstance().time, true, "8.1"),
        Movie(497698,"Фильм 2", "Movie 2", Calendar.getInstance().time, false, "6.8"),
        Movie(379686,"Фильм 3", "Movie 3", Calendar.getInstance().time, true, "7.4"),
        Movie(459151,"Фильм 4", "Movie 4", Calendar.getInstance().time, false, "5.2"),
        Movie(508943,"Фильм 9", "Movie 4", Calendar.getInstance().time, false, "5.2"),
        Movie(637649,"Фильм 10", "Movie 4", Calendar.getInstance().time, false, "5.2")
    )

fun getUpcomingMovies()= listOf(
        Movie(343611,"Фильм 5", "Movie 5", Calendar.getInstance().time, false, "", "", "Фантастика"),
        Movie(581726,"Фильм 6", "Movie 6", Calendar.getInstance().time, false, "", "", "Мелодрама"),
        Movie(522478,"Фильм 7", "Movie 7", Calendar.getInstance().time, false, "", "", "Боевик"),
        Movie(385128,"Фильм 8", "Movie 8", Calendar.getInstance().time, false, "", "", "Триллер"),
        Movie(337404,"Фильм 11", "Movie 8", Calendar.getInstance().time, false, "", "", "Комедия"),
        Movie(520763,"Фильм 12", "Movie 8", Calendar.getInstance().time, false, "", "", "Документальный")
    )
fun getDefaultMovie() =  Movie(550,"Фильм", "Movie", Calendar.getInstance().time, false, "0")

