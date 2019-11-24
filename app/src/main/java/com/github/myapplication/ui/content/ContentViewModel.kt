package com.github.myapplication.ui.content

import androidx.lifecycle.ViewModel
import com.github.myapplication.data.model.MoviesModel
import com.github.myapplication.data.source.DataDummySource

/**
 * Created by Muhammad Firdaus on 24/11/2019.
 */

class ContentViewModel : ViewModel() {
    val getAllMovies: List<MoviesModel> = DataDummySource.getAllMovies()
    val getAllTvShows: List<MoviesModel> = DataDummySource.getAllTvShows()
}