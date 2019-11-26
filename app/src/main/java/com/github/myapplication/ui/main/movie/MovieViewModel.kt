package com.github.myapplication.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.myapplication.base.BaseViewModel
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.DataSource
import com.github.myapplication.data.source.Repository
import com.github.myapplication.utils.Constants

/**
 * Created by Muhammad Firdaus on 26/11/2019.
 */
class MovieViewModel(private val mRepository: Repository) : BaseViewModel() {

    private val movieList = MutableLiveData<List<MovieModel>>()

    init {
        getAllMovies()
    }

    fun getAllMovies() {
        eventShowProgress.value = true
        mRepository.remoteDataSource.getAllData(
            Constants.TYPE_MOVIE,
            Constants.FILTER_POPULAR,
            object : DataSource.GetAllDataCallback {
                override fun onSuccess(data: List<MovieModel>) {
                    if (data.isNotEmpty()) {
                        eventShowProgress.value = false
                        movieList.apply {
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

    fun getMovieList(): LiveData<List<MovieModel>> {
        return movieList
    }

}