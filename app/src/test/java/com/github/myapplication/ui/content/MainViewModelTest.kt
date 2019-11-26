package com.github.myapplication.ui.content

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.Repository
import com.github.myapplication.ui.main.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class MainViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var mViewModel: MainViewModel? = null
    private val data = Mockito.mock(Repository::class.java)
    private lateinit var itemList: MovieModel

    @Before
    fun setUp() {
        mViewModel = MainViewModel(data)
//        itemList = MovieModel()
    }
    @Test
    fun movies() {
        assertNotNull(mViewModel?.getMovieList()?.value)
        assertEquals(15, mViewModel?.getMovieList()?.value?.size)
    }

    @Test
    fun tvshow() {
        assertNotNull(mViewModel?.getTvShowList()?.value)
        assertEquals(15, mViewModel?.getTvShowList()?.value?.size)
    }

}