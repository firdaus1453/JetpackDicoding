package com.github.myapplication.ui.main.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.myapplication.ui.MyApplication
import com.github.myapplication.utils.Constants.CATEGORY_MOVIE
import com.github.myapplication.utils.Constants.CATEGORY_TV
import com.github.myapplication.utils.observeOnce
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Muhammad Firdaus on 29/11/2019.
 */
class FavoriteViewModelTest {

    private lateinit var mViewModel: FavoriteViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mViewModel = FavoriteViewModel(Mockito.mock(MyApplication::class.java))
    }

    @Test
    fun getAllMovies() {
        mViewModel.getAllMovies(CATEGORY_MOVIE)?.observeOnce { assertNotNull(it) }
    }

    @Test
    fun getAllTv() {
        mViewModel.getAllMovies(CATEGORY_TV)?.observeOnce { assertNotNull(it) }
    }

}