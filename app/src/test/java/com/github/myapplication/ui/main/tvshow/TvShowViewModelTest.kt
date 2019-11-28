package com.github.myapplication.ui.main.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.myapplication.ui.MyApplication
import com.github.myapplication.utils.observeOnce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Muhammad Firdaus on 26/11/2019.
 */
class TvShowViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(Mockito.mock(MyApplication::class.java))
    }

    @Test
    fun movies() = runBlocking {
        launch { viewModel.getAllTvShows() }
        viewModel.getTvShowList().observeOnce { assertNotEquals(0, it.size) }
    }
}