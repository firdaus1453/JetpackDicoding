package com.github.myapplication.data.source.local.dao

import androidx.room.*
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

    @Query("SELECT * FROM movie")
    fun getAllLocalData(): List<MovieModel>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getLocalDataById(id: Int): MovieModel

    @Query("DELETE FROM movie WHERE id == :id")
    fun deleteLocalDataById(id: Int): MovieModel

    @Delete
    fun deleteLocalData(data: MovieModel)
}