package com.example.ammymovie.domain.repository.impls.room

import androidx.room.*

/**
 * homework com.example.ammymovie.domain.repository.impls.room
 *
 * @author Amina
 * 05.08.2021
 */
@Dao
interface MovieRepoDao {

    @Query("SELECT * FROM MovieEntityRepoRoomDto WHERE section==:section")
    fun all(section:Int): List<MovieEntityRepoRoomDto>

    @Query("SELECT * FROM MovieEntityRepoRoomDto WHERE id ==:id")
    fun getIdByData(id: Int): List<MovieEntityRepoRoomDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: MovieEntityRepoRoomDto)

    @Update
    fun update(entity: MovieEntityRepoRoomDto)

    @Delete
    fun delete(entity: MovieEntityRepoRoomDto)

}