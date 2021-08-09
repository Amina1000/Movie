package com.example.ammymovie.utils

import android.icu.text.SimpleDateFormat
import android.os.Build
import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.MovieListDTO
import com.example.ammymovie.domain.repository.impls.room.MovieEntityRepoRoomDto
import java.util.*
import kotlin.collections.ArrayList

/**
 * homework com.example.ammymovie.utils
 *
 * @author Amina
 * 23.07.2021
 */
const val DATE_TIME_FORMAT = "dd.MMM.yy"

// добавила функцию расширения (extension-функцию) для класса Date.
fun Date.format(): String =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
            .format(this)
    } else {
        this.toString()
    }

fun convertMovieEntityToMovie(entityList: List<MovieEntityRepoRoomDto>): MovieListDTO {
    return MovieListDTO(ArrayList(entityList.map {
        MovieDTO(
            it.id,
            it.adult,
            listOf(0),
            it.originalLanguage,
            it.originalTitle,
            it.overview,
            it.popularity,
            it.posterPath,
            it.releaseDate,
            it.originalTitle,
            it.voteAverage,
            it.voteCount,
            it.duration,
            it.revenue,
            it.favorite,
            it.section
        )
    }))
}

fun convertMovieListDTOMovieEntity(movieListDTO: MovieListDTO): List<MovieEntityRepoRoomDto> {
    return movieListDTO.results!!.map {
        MovieEntityRepoRoomDto(
            it.id,
            it.adult,
            it.genreIds.toString(),
            it.originalLanguage,
            it.originalTitle,
            it.overview,
            it.popularity,
            it.posterPath,
            it.releaseDate,
            it.originalTitle,
            it.voteAverage,
            it.voteCount,
            it.duration,
            it.revenue,
            it.favorite,
            it.section
        )
    }
}