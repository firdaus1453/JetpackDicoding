package com.github.myapplication.data.source.local

import android.util.Log
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.DataSource
import com.github.myapplication.data.source.local.dao.MovieDao
import com.github.myapplication.ui.MyApplication
import androidx.paging.DataSource as DS

/**
 * Created by Muhammad Firdaus on 28/11/2019.
 */
class LocalDataSource {
    private var movieDao: MovieDao? = null

    init {
        AppDatabase.getInstance(MyApplication.applicationContext())?.let {
            movieDao = it.movieDao()
        }
    }

    fun saveOneToLocalData(data: MovieModel) {
        try {
            movieDao?.saveOneToLocalData(data)
        } catch (exception: Exception) {
            Log.e(TAG, exception.message.toString())
        }
    }

    fun saveAllToLocalData(data: List<MovieModel>) {
        try {
            movieDao?.saveAllToLocalData(data)
        } catch (exception: Exception) {
            Log.e(TAG, exception.message.toString())
        }
    }

    fun getAllLocalData(type: Int): DS.Factory<Int, MovieModel>? = movieDao?.getAllLocalData(type)

    fun getLocalDataById(id: Int, callback: DataSource.GetDataByIdCallback) {
        try {
            movieDao?.getLocalDataById(id)?.let { callback.onSuccess(it) }
        } catch (exception: Exception) {
            callback.onFailed(exception.message)
        }
    }

    fun deleteLocalData(id: Int) {
        try {
            movieDao?.deleteLocalData(id)
        } catch (exception: Exception) {
            Log.e(TAG, exception.message.toString())
        }
    }

    companion object {
        val TAG = LocalDataSource::class.java.simpleName
    }
}