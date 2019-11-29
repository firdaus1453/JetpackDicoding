package com.github.myapplication.data.source

import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.local.LocalDataSource
import com.github.myapplication.data.source.remote.RemoteDataSource

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class Repository : DataSource {

    private val remoteDataSource = RemoteDataSource()
    private val localDataSource = LocalDataSource()

    // remote repo
    suspend fun getAllData(type: String, filter: String, callback: DataSource.GetAllDataCallback) {
        remoteDataSource.getAllData(type, filter, object : DataSource.GetAllDataCallback {
            override fun onSuccess(data: List<MovieModel>) {
                callback.onSuccess(data)
            }

            override fun onFailed(errorMessage: String?) {
                callback.onFailed(errorMessage)
            }
        })
    }

    suspend fun getDataById(type: String, id: Int, callback: DataSource.GetDataByIdCallback) {
        remoteDataSource.getDataById(type, id, object : DataSource.GetDataByIdCallback {
            override fun onSuccess(data: MovieModel) {
                callback.onSuccess(data)
            }

            override fun onFailed(errorMessage: String?) {
                callback.onFailed(errorMessage)
            }
        })
    }

    // local repo
    fun saveOneToLocalData(data: MovieModel) {
        localDataSource.saveOneToLocalData(data)
    }

    fun getLocalDataById(id: Int, callback: DataSource.GetDataByIdCallback) {
        localDataSource.getLocalDataById(id, object : DataSource.GetDataByIdCallback {
            override fun onSuccess(data: MovieModel) {
                callback.onSuccess(data)
            }

            override fun onFailed(errorMessage: String?) {
                callback.onFailed(errorMessage)
            }
        })
    }

    fun deleteLocalData(id: Int) {
        localDataSource.deleteLocalData(id)
    }
}