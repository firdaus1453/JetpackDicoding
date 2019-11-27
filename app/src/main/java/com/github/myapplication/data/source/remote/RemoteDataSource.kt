package com.github.myapplication.data.source.remote

import com.github.myapplication.BuildConfig.MOVIE_DB_API_KEY
import com.github.myapplication.data.source.DataSource
import com.github.myapplication.utils.EspressoIdlingResource

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class RemoteDataSource {

    private val mApiService = ApiService.getApiService()

    suspend fun getAllData(type: String, filter: String, callback: DataSource.GetAllDataCallback) {
        EspressoIdlingResource.increment()
        try {
            val response = mApiService.getAllDataAsync(type, filter, MOVIE_DB_API_KEY).await()
            val newData = response.results ?: listOf()
            callback.onSuccess(newData)
            EspressoIdlingResource.decrement()
        } catch (e: Exception) {
            callback.onFailed(e.message)
        }
    }

    suspend fun getDataById(type: String, id: Int, callback: DataSource.GetDataByIdCallback) {
        try {
            mApiService.getDataByIdAsync(type, id, MOVIE_DB_API_KEY).await().let { callback.onSuccess(it) }
        } catch (e: Exception) {
            callback.onFailed(e.message)
        }
    }

    companion object {
        private var INSTANCE: RemoteDataSource? = null
        fun getInstance() = INSTANCE ?: RemoteDataSource()
    }
}