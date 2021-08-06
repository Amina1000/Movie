package com.example.ammymovie.domain.repository.impls.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ammymovie.domain.model.MovieSection

/**
 * homework com.example.ammymovie.domain.repository.impls.room
 *
 * @author Amina
 * 05.08.2021
 */
@Entity
class MovieEntityRepoRoomDto(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    val title: String?,
    val original_title: String?,
    val release_date: String?,
    val popularity: Double?,
    val poster_path:String?,
    val overview: String?,
    val duration: String?,
    val budget: Int?,
    val revenue: Int?,
    var favorite:Boolean,
    val section:Int)