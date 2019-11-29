package com.github.myapplication.data.source.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.myapplication.data.model.MovieModel

/**
 * Created by Muhammad Firdaus on 28/11/2019.
 */
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllToLocalData(data: List<MovieModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveOneToLocalData(data: MovieModel)

    @Query("SELECT * FROM movie WHERE type IN(:type) ORDER BY idMovie DESC")
    fun getAllLocalData(type: Int): DataSource.Factory<Int, MovieModel>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getLocalDataById(id: Int): MovieModel

    @Query("DELETE FROM movie WHERE id IN(:id)")
    fun deleteLocalData(id: Int)
}