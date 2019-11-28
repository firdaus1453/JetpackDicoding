package com.github.myapplication.data.source.local

import android.util.Log
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.DataSource
import com.github.myapplication.data.source.local.dao.MovieDao
import com.github.myapplication.ui.MyApplication

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

    fun getAllLocalData(callback: DataSource.GetAllDataCallback) {
        try {
            movieDao?.getAllLocalData().let {
                if (!it.isNullOrEmpty()) callback.onSuccess(it)
            }
        } catch (exception: Exception) {
            callback.onFailed(exception.message)
        }
    }

    fun getLocalDataById(id: Int, callback: DataSource.GetDataByIdCallback) {
        try {
            movieDao?.getLocalDataById(id)?.let { callback.onSuccess(it) }
        } catch (exception: Exception) {
            callback.onFailed(exception.message)
        }
    }

    fun deleteLocalDataById(id: Int) {
        try {
            movieDao?.deleteLocalDataById(id)
        } catch (exception: Exception) {
            Log.e(TAG, exception.message.toString())
        }
    }

    fun deleteLocalData(data: MovieModel) {
        try {
            movieDao?.deleteLocalData(data)
        } catch (exception: Exception) {
            Log.e(TAG, exception.message.toString())
        }
    }

    companion object {
        val TAG = LocalDataSource::class.java.simpleName
    }
}