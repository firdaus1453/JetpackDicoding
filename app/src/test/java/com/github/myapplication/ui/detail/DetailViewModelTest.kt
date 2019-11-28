package com.github.myapplication.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.myapplication.ui.MyApplication
import com.github.myapplication.utils.observeOnce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        viewModel = DetailViewModel(Mockito.mock(MyApplication::class.java))
    }

    @Test
    fun moviesDetail() = runBlocking {
        launch { viewModel.getDetailMovie(330457) }
        viewModel.getMovieData().observeOnce { Assert.assertNotNull(it) }
    }

    @Test
    fun tvShowDetail() = runBlocking {
        launch { viewModel.getDetailTvShow(60574) }
        viewModel.getTvShowData().observeOnce { Assert.assertNotNull(it) }
    }
}