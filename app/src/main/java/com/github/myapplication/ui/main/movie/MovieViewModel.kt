package com.github.myapplication.ui.main.movie

import android.app.Application
import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.github.myapplication.base.BaseViewModel
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.DataSource
import com.github.myapplication.utils.Constants

/**
 * Created by Muhammad Firdaus on 26/11/2019.
 */
class MovieViewModel(application: Application) : BaseViewModel(application) {

    private val movieList = MutableLiveData<List<MovieModel>>()
    val movies: LiveData<List<MovieModel>> = Transformations.map(movieList) { it }

    suspend fun getAllMovies() {
        eventShowProgress.value = true
        getRepository().getAllData(
            Constants.TYPE_MOVIE,
            Constants.FILTER_POPULAR,
            object : DataSource.GetAllDataCallback {
                override fun onSuccess(data: List<MovieModel>) {
                    if (data.isNotEmpty()) {
                        eventShowProgress.value = false
                        movieList.value = data
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