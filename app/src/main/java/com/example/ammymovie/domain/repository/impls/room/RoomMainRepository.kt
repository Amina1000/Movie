package com.example.ammymovie.domain.repository.impls.room

import android.os.Handler
import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.MovieListDTO
import com.example.ammymovie.utils.convertMovieDTOMovieEntity
import com.example.ammymovie.utils.convertMovieEntityToMovie
import com.example.ammymovie.utils.convertMovieListDTOMovieEntity

class RoomMainRepository(private val dao: MovieRepoDao, private val handler: Handler) {

    fun getMovieFromLocalStorage(onSuccess: (MovieListDTO) -> Unit, adultAdded: Boolean) {
        Thread {
            val repos = if (!adultAdded) {
                convertMovieEntityToMovie(dao.adult(adultAdded))
            } else {
                convertMovieEntityToMovie(dao.all())
            }
            handler.post { onSuccess(repos) }
        }.start()

    }

    fun saveEntity(movieListDTO: MovieListDTO) {
        Thread {
            val movieEntityList = convertMovieListDTOMovieEntity(movieListDTO)
            movieEntityList.forEach {
                dao.getIdByData(it.id)?.let { en ->
                    it.favorite = en.favorite
                }
                dao.insert(it)
            }
        }.start()
    }

    fun saveDetails(movieDTO: MovieDTO) {
        Thread {
            val movieEntity = dao.getIdByData(movieDTO.id)?:convertMovieDTOMovieEntity(movieDTO)
            movieEntity.favorite = movieDTO.favorite
            dao.insert(movieEntity)
        }.start()
    }
}