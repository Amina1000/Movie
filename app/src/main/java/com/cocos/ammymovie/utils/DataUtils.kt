package com.cocos.ammymovie.utils

import android.app.Activity
import android.os.Bundle
import androidx.navigation.Navigation
import com.cocos.ammymovie.R
import com.cocos.ammymovie.domain.model.MovieDTO
import com.cocos.ammymovie.domain.model.MovieListDTO
import com.cocos.ammymovie.domain.repository.impls.room.MovieEntityRepoRoomDto

/**
 * homework com.example.ammymovie.utils
 *
 * @author Amina
 * 23.07.2021
 */

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

fun convertMovieDTOMovieEntity(movieDTO: MovieDTO?): MovieEntityRepoRoomDto? {
    return movieDTO?.let {
        MovieEntityRepoRoomDto(
            it.id,
            it.adult,
            "",
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

fun navigateTo(activity:Activity, target: Int, bundle: Bundle? = null) {
    val navController = Navigation.findNavController(activity, R.id.container)
    if (bundle == null)
        navController.also {
            it.navigate(target)
        } else {
        navController.also {
            it.navigate(target, bundle)
        }
    }
}