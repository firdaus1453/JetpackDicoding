package com.github.myapplication.ui.main.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.github.myapplication.base.BaseViewModel
import com.github.myapplication.data.model.MovieModel

/**
 * Created by Muhammad Firdaus on 28/11/2019.
 */

private const val PAGE_SIZE = 10

class FavoriteViewModel(application: Application) : BaseViewModel(application) {
    fun getAllMovies(type: Int): LiveData<PagedList<MovieModel>>? =
        getLocalDataSource().getAllLocalData(type)?.toLiveData(PAGE_SIZE)
}