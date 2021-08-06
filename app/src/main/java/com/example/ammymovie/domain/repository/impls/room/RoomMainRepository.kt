package com.example.ammymovie.domain.repository.impls.room

import android.os.Handler
import com.example.ammymovie.domain.model.MovieListDTO
import com.example.ammymovie.domain.model.MovieSection
import com.example.ammymovie.utils.convertMovieEntityToMovie
import com.example.ammymovie.utils.convertMovieListDTOMovieEntity

class RoomMainRepository(private val dao: MovieRepoDao, private val handler: Handler) {


    fun getNowPlayingFromLocalStorage(onSuccess: (MovieListDTO) -> Unit) {
        Thread {
            val repos = convertMovieEntityToMovie(dao.all(MovieSection.PLAY.section))
            handler.post { onSuccess(repos) }
        }.start()


    }
    fun getUpcomingFromLocalStorage(onSuccess: (MovieListDTO) -> Unit) {
        Thread {
            val repos = convertMovieEntityToMovie(dao.all(MovieSection.UPCOMING.section))
            handler.post { onSuccess(repos) }
        }.start()
    }

    fun saveEntity(movieListDTO: MovieListDTO) {
        val movieEntityList = convertMovieListDTOMovieEntity(movieListDTO)
        movieEntityList.forEach { dao.insert(it) }
    }
}