package com.cocos.ammymovie.domain.repository.impls.room

import androidx.room.*

/**
 * homework com.example.ammymovie.domain.repository.impls.room
 *
 * @author Amina
 * 05.08.2021
 */
@Dao
interface MovieRepoDao {

    @Query("SELECT * FROM MovieEntityRepoRoomDto")
    fun all(): List<MovieEntityRepoRoomDto>

    @Query("SELECT * FROM MovieEntityRepoRoomDto WHERE adult==:adultAdded")
    fun adult(adultAdded:Boolean): List<MovieEntityRepoRoomDto>

    @Query("SELECT * FROM MovieEntityRepoRoomDto WHERE id ==:id")
    fun getIdByData(id: Int): MovieEntityRepoRoomDto

    @Query("UPDATE MovieEntityRepoRoomDto SET favorite=:favorite WHERE id = :id")
    fun updateFavorite(favorite:Boolean, id:Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: MovieEntityRepoRoomDto)

    @Update
    fun update(entity: MovieEntityRepoRoomDto)

    @Delete
    fun delete(entity: MovieEntityRepoRoomDto)

}