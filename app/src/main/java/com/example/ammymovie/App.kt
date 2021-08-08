package com.example.ammymovie

import android.app.Application
import androidx.room.Room
import com.example.ammymovie.domain.repository.impls.room.MovieDatabase
import com.example.ammymovie.domain.repository.impls.room.MovieEntityRepoRoomDto
import com.example.ammymovie.domain.repository.impls.room.MovieRepoDao

/**
 * homework com.example.ammymovie
 *
 * @author Amina
 * 05.08.2021
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private var appInstance: App? = null
        private var db: MovieDatabase? = null
        private const val DB_NAME = "Movie.db"

        fun getMovieDao(): MovieRepoDao {
            if (db == null) {
                synchronized(MovieDatabase::class.java) {
                    if (db == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            MovieDatabase::class.java,
                            DB_NAME)
                            .addMigrations(MovieDatabase.MIGRATION_1_2)
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }

            return db!!.movieRepoDao()
        }
    }
}