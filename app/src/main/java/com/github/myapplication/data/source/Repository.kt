package com.github.myapplication.data.source

import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.remote.RemoteDataSource

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class Repository : DataSource {

    private val remoteDataSource = RemoteDataSource()

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

    companion object {

//        @Volatile private var INSTANCE: Repository? = null
//        fun getInstance(remoteRepository: RemoteDataSource) = INSTANCE ?: synchronized(Repository::class.java){
//            INSTANCE ?: Repository(remoteRepository).also { INSTANCE = it }
//        }
    }
}