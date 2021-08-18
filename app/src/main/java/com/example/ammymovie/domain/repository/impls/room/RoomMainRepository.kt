package com.example.ammymovie.domain.repository.impls.room

import android.os.Handler
import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.MovieListDTO
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
                val entity = dao.getIdByData(it.id)
                entity?.let {en->
                    it.favorite = en.favorite
                }
                dao.insert(it)
            }
        }.start()
    }

    fun saveDetails(movieDTO: MovieDTO) {
        Thread {
            dao.updateFavorite(movieDTO.favorite,movieDTO.id)
        }.start()
    }
}