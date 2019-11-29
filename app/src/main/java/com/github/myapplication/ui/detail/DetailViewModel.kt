package com.github.myapplication.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.myapplication.base.BaseViewModel
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.DataSource
import com.github.myapplication.utils.Constants
import com.github.myapplication.utils.Constants.CATEGORY_MOVIE
import com.github.myapplication.utils.Constants.CATEGORY_TV
import com.github.myapplication.utils.EspressoIdlingResource

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class DetailViewModel(application: Application) : BaseViewModel(application) {

    private val movieData = MutableLiveData<MovieModel>()
    private val tvShowData = MutableLiveData<MovieModel>()

    var check = MutableLiveData<Boolean>()

    fun getMovieData(): LiveData<MovieModel> = movieData
    fun getTvShowData(): LiveData<MovieModel> = tvShowData

    fun checkFavorite(movieId: Int): Boolean {
        var bFavorite = false
        getRepository().getLocalDataById(movieId, object : DataSource.GetDataByIdCallback {
            override fun onSuccess(data: MovieModel) {
                bFavorite = data.id != null
            }

            override fun onFailed(errorMessage: String?) {
            }
        })
        return bFavorite
    }

    fun addToFavorite(data: MovieModel) {
        getRepository().saveOneToLocalData(data)
        check.value = true
    }

    fun removeFavorite(id: Int) {
        getRepository().deleteLocalData(id)
        check.value = false
    }

    suspend fun getDetailMovie(id: Int) {
        EspressoIdlingResource.increment()
        eventShowProgress.value = true

        getRepository().getDataById(Constants.TYPE_MOVIE, id, object : DataSource.GetDataByIdCallback {
            override fun onSuccess(data: MovieModel) {
                eventShowProgress.value = false
                movieData.apply {
                    data.type_movie = CATEGORY_MOVIE
                    postValue(data)
                }
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailed(errorMessage: String?) {
                eventShowProgress.value = false
                eventGlobalMessage.value = errorMessage
            }

        })
    }

    suspend fun getDetailTvShow(id: Int) {
        EspressoIdlingResource.increment()
        eventShowProgress.value = true

        getRepository().getDataById(Constants.TYPE_TV, id, object : DataSource.GetDataByIdCallback {
            override fun onSuccess(data: MovieModel) {
                eventShowProgress.value = false
                tvShowData.apply {
                    data.type_movie = CATEGORY_TV
                    postValue(data)
                }
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailed(errorMessage: String?) {
                eventShowProgress.value = false
                eventGlobalMessage.value = errorMessage
            }

        })
    }
}