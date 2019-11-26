package com.github.myapplication.ui.main.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.myapplication.base.BaseViewModel
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.DataSource
import com.github.myapplication.data.source.Repository
import com.github.myapplication.utils.Constants.FILTER_TOP_RATED
import com.github.myapplication.utils.Constants.TYPE_TV


/**
 * Created by Muhammad Firdaus on 24/11/2019.
 */

class TvShowViewModel(private val mRepository: Repository) : BaseViewModel() {

    private val tvShowList = MutableLiveData<List<MovieModel>>()

    init {
        getAllTvShows()
    }

    fun getAllTvShows() {
        eventShowProgress.value = true
        mRepository.remoteDataSource.getAllData(TYPE_TV, FILTER_TOP_RATED, object : DataSource.GetAllDataCallback {
            override fun onSuccess(data: List<MovieModel>) {
                if (data.isNotEmpty()) {
                    eventShowProgress.value = false
                    tvShowList.apply {
                        postValue(null)
                        postValue(data)
                    }
                }
            }

            override fun onFinish() {}

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                eventShowProgress.value = false
                eventGlobalMessage.value = errorMessage
            }
        })
    }

    fun getTvShowList(): LiveData<List<MovieModel>> {
        return tvShowList
    }
}