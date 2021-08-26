package com.cocos.ammymovie.domain.repository.impls.room

import android.os.Handler
import com.cocos.ammymovie.domain.model.MovieDTO
import com.cocos.ammymovie.domain.model.MovieListDTO
import com.cocos.ammymovie.utils.convertMovieDTOMovieEntity
import com.cocos.ammymovie.utils.convertMovieEntityToMovie
import com.cocos.ammymovie.utils.convertMovieListDTOMovieEntity

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

    fun saveDetails(movieDTO: MovieDTO?) {
        movieDTO?.let {
            Thread {
                val movieEntity = dao.getIdByData(it.id)?:convertMovieDTOMovieEntity(movieDTO)
                movieEntity?.favorite = it.favorite
                movieEntity?.let {en-> dao.insert(en) }
            }.start()
        }
    }
}