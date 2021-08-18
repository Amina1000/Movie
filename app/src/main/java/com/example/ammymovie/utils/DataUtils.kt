package com.example.ammymovie.utils

import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.MovieListDTO
import com.example.ammymovie.domain.repository.impls.room.MovieEntityRepoRoomDto

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
