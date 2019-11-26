package com.github.myapplication.data.source.remote

import android.content.Context
import com.github.myapplication.BuildConfig.MOVIE_DB_API_KEY
import com.github.myapplication.base.BaseApiModel
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.DataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class RemoteDataSource(context: Context) : DataSource {

    private val mApiService = ApiService.getApiService(context)

    override fun getAllData(type: String, filter: String, callback: DataSource.GetAllDataCallback) {
        mApiService.getAllData(type, filter, MOVIE_DB_API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<List<MovieModel>>>() {
                override fun onSuccess(model: BaseApiModel<List<MovieModel>>) {
                    val newData = model.results ?: listOf()
                    callback.onSuccess(newData)
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }

            })
    }

    override fun getDataById(type: String, id: Int, callback: DataSource.GetDataByIdCallback) {
        mApiService.getDataById(type, id, MOVIE_DB_API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<MovieModel>() {
                override fun onSuccess(model: MovieModel) {
                    callback.onSuccess(model)
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }
}