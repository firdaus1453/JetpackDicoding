package com.github.myapplication.ui.main.tvshow

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.myapplication.base.BaseViewModel
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.DataSource
import com.github.myapplication.utils.Constants.FILTER_TOP_RATED
import com.github.myapplication.utils.Constants.TYPE_TV
import com.github.myapplication.utils.EspressoIdlingResource


/**
 * Created by Muhammad Firdaus on 24/11/2019.
 */

class TvShowViewModel(application: Application) : BaseViewModel(application) {

    private val tvShowList = MutableLiveData<List<MovieModel>>()

    suspend fun getAllTvShows() {
        EspressoIdlingResource.increment()
        eventShowProgress.value = true
        getRepository().getAllData(TYPE_TV, FILTER_TOP_RATED, object : DataSource.GetAllDataCallback {
            override fun onSuccess(data: List<MovieModel>) {
                if (data.isNotEmpty()) {
                    eventShowProgress.value = false
                    tvShowList.apply {
                        postValue(null)
                        postValue(data)
                    }
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

    fun getTvShowList(): LiveData<List<MovieModel>> {
        return tvShowList
    }
}