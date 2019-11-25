package com.github.myapplication.ui.detail

import androidx.lifecycle.ViewModel
import com.github.myapplication.data.model.MoviesModel
import com.github.myapplication.data.source.local.DataDummySource

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class DetailViewModel: ViewModel() {
    fun getDetailMovie(id: Int): MoviesModel? = DataDummySource.getMovieById(id)
    fun getDetailTvShow(id: Int): MoviesModel? = DataDummySource.getTvShowById(id)
}