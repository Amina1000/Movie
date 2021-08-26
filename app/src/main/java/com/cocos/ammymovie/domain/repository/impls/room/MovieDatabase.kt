package com.cocos.ammymovie.domain.repository.impls.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * homework com.example.ammymovie.domain.repository.impls.room
 *
 * @author Amina
 * 05.08.2021
 */
@Database(entities = [MovieEntityRepoRoomDto::class], version = 2)
abstract class MovieDatabase: RoomDatabase() {
    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE MovieEntityRepoRoomDto ADD COLUMN vote_average DOUBLE"
                )
            }
        }}

    abstract fun movieRepoDao(): MovieRepoDao

}