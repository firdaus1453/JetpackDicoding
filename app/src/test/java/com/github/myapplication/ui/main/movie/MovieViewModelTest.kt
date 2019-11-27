package com.github.myapplication.ui.main.movie

import android.graphics.Movie
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.Repository
import com.github.myapplication.data.source.remote.RemoteDataSource
import com.github.myapplication.ui.MyApplication
import com.github.myapplication.utils.observeOnce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

/**
 * Created by Muhammad Firdaus on 26/11/2019.
 */
class MovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel(mock(MyApplication::class.java))
    }

    @Test
    fun movies() = runBlocking {
        launch { viewModel.getAllMovies() }
        viewModel.movies.observeOnce { Assert.assertEquals(0, it.size) }
//        val observer = mock(Observer::class.java)
//        viewModel.movies.observeForever(observer as Observer<List<MovieModel>>)
//        Assert.assertEquals(0, viewModel.movies.value?.size)
    }

}