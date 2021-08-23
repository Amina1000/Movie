package com.cocos.ammymovie.domain.repository.impls.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * homework com.example.ammymovie.domain.repository.impls.room
 *
 * @author Amina
 * 05.08.2021
 */
@Entity
class MovieEntityRepoRoomDto(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("genre_ids")
    val genreIds: String?,
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
    var section:Int=0)