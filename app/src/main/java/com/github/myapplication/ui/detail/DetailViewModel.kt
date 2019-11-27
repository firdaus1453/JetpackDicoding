package com.github.myapplication.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.myapplication.base.BaseViewModel
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.DataSource
import com.github.myapplication.utils.Constants

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class DetailViewModel(application: Application) : BaseViewModel(application) {

    private val movieData = MutableLiveData<MovieModel>()
    private val tvShowData = MutableLiveData<MovieModel>()

    fun getMovieData(): LiveData<MovieModel> {
        return movieData
    }

    fun getTvShowData(): LiveData<MovieModel> {
        return tvShowData
    }

    suspend fun getDetailMovie(id: Int) {
        eventShowProgress.value = true

        getRepository().getDataById(Constants.TYPE_MOVIE, id, object : DataSource.GetDataByIdCallback {
            override fun onSuccess(data: MovieModel) {
                eventShowProgress.value = false
                movieData.apply {
                    postValue(null)
                    postValue(data)
                }
            }


            override fun onFailed(errorMessage: String?) {
                eventShowProgress.value = false
                eventGlobalMessage.value = errorMessage
            }

        })
    }

    suspend fun getDetailTvShow(id: Int) {
        eventShowProgress.value = true

        getRepository().getDataById(Constants.TYPE_TV, id, object : DataSource.GetDataByIdCallback {
            override fun onSuccess(data: MovieModel) {
                eventShowProgress.value = false
                tvShowData.apply {
                    postValue(null)
                    postValue(data)
                }
            }

            override fun onFailed(errorMessage: String?) {
                eventShowProgress.value = false
                eventGlobalMessage.value = errorMessage
            }

        })
    }
}