package com.example.ammymovie.domain.repository.impls.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * homework com.example.ammymovie.domain.repository.impls.room
 *
 * @author Amina
 * 05.08.2021
 */
@Database(entities = [MovieEntityRepoRoomDto::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieRepoDao(): MovieRepoDao

}