package com.github.myapplication.data.source

import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.remote.RemoteDataSource

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class Repository(val remoteDataSource: RemoteDataSource) : DataSource {

    // remote repo
    override fun getAllData(type: String, filter: String, callback: DataSource.GetAllDataCallback) {
        remoteDataSource.getAllData(type, filter, object : DataSource.GetAllDataCallback {
            override fun onSuccess(data: List<MovieModel>) {
                callback.onSuccess(data)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                callback.onFailed(statusCode, errorMessage)
            }

            override fun onFinish() {
                callback.onFinish()
            }
        })
    }

    override fun onClearDisposables() {
        remoteDataSource.onClearDisposables()
    }

    companion object {

        var mRepository: Repository? = null

        @JvmStatic
        fun getInstance(remoteDataSource: RemoteDataSource): Repository {
            if (mRepository == null) {
                mRepository = Repository(remoteDataSource = remoteDataSource)
            }
            return mRepository!!
        }
    }
}