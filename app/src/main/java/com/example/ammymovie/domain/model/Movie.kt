package com.example.ammymovie.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Movie(
    // Класс для хранения данных
    val id: Int,
    val name: String,
    val nameOrigin: String = "",
    val releaseDate: Date,
    var favorite: Boolean = false,
    val rating: String = "",
    val posterPath: String = "/rr7E0NoGKxvbkb89eR1GwfoYjpA.jpg",
    val description: String = "Описание фильма",
    val genre: String = "Жанр",
    val duration: String = "",
    val budget: String = "",
    val revenue: String = ""
) : Parcelable


