package com.github.myapplication.ui.main.movie

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.data.source.DataSource
import com.github.myapplication.data.source.Repository
import com.github.myapplication.data.source.remote.RemoteDataSource
import com.github.myapplication.utils.observeOnce
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

/**
 * Created by Muhammad Firdaus on 26/11/2019.
 */
class MovieViewModelTest {
    @Mock
    private lateinit var mViewModel: MovieViewModel
    @Mock
    private lateinit var repo : Repository
    @Mock
    private lateinit var context: Application
    @Mock
    private lateinit var remote : RemoteDataSource

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        setupContext()
        remote = RemoteDataSource(context)
        repo = Repository(remote)
        mViewModel = MovieViewModel(repo)
    }

    @Test
    fun movies() {
        mViewModel.getAllMovies()
        val moviesLive = MutableLiveData<List<MovieModel>>()
        val observer = mock(Observer::class.java)
        mViewModel.movieList.observeForever(observer as Observer<List<MovieModel>>)
        Assert.assertEquals(0,mViewModel.movieList)
//        mViewModel.movieList.observeOnce {
//            Assert.assertEquals(1, it.size)
//        }
    }

    private fun setupContext() {
        Mockito.`when`<Context>(context.applicationContext).thenReturn(context)
        Mockito.`when`(context.resources).thenReturn(Mockito.mock(Resources::class.java))
    }

}