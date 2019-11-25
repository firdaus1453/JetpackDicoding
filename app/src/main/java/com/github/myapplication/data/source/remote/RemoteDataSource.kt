package com.github.myapplication.data.source.remote

import android.content.Context
import com.github.myapplication.BuildConfig.MOVIE_DB_API_KEY
import com.github.myapplication.base.BaseApiModel
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.DataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class RemoteDataSource(context: Context) : DataSource {

    val mApiService = ApiService.getApiService(context)
    private var compositeDisposable: CompositeDisposable? = null

    override fun getAllData(type: String, filter: String, callback: DataSource.GetAllDataCallback) {
        mApiService.getAllData(type, filter, MOVIE_DB_API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<List<MovieModel>>>() {
                override fun onSuccess(model: BaseApiModel<List<MovieModel>>) {
                    val newData = model.results?: listOf()
                    callback.onSuccess(newData)
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }

                override fun onStartObserver(disposable: Disposable) {
                    addSubscribe(disposable)
                }

            })
    }

    override fun onClearDisposables() {
        compositeDisposable?.clear()
    }

    fun addSubscribe(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
            compositeDisposable?.add(disposable)
        }
    }
}