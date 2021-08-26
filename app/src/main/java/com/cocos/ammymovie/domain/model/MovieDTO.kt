package com.cocos.ammymovie.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * homework com.example.ammymovie.domain.model
 *
 * @author Amina
 * 21.07.2021
 */
@Parcelize
data class MovieDTO(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("revenue")
    val revenue: Int?,
    var favorite:Boolean=false,
    var section:Int=0
): Parcelable