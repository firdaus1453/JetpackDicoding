package com.github.myapplication.ui.main.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.myapplication.base.BaseViewModel
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.DataSource
import com.github.myapplication.utils.EspressoIdlingResource

/**
 * Created by Muhammad Firdaus on 28/11/2019.
 */
class FavoriteViewModel(application: Application) : BaseViewModel(application) {
    private val movieList = MutableLiveData<List<MovieModel>>()

    fun getAllMovies(type: Int) {
        EspressoIdlingResource.increment()
        eventShowProgress.value = true
        getRepository().getAllLocalData(type, object : DataSource.GetAllDataCallback {
            override fun onSuccess(data: List<MovieModel>) {
                if (data.isNotEmpty()) {
                    eventShowProgress.value = false
                    movieList.value = data
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
            }

            override fun onFailed(errorMessage: String?) {
                eventShowProgress.value = false
                eventGlobalMessage.value = errorMessage
            }
        })
    }

    fun getMovieList(): LiveData<List<MovieModel>> {
        return movieList
    }
}