package com.github.myapplication.ui.main.tvshow

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.myapplication.data.source.Repository
import com.github.myapplication.data.source.remote.RemoteDataSource
import com.github.myapplication.ui.main.movie.MovieViewModel
import com.github.myapplication.utils.observeOnce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Muhammad Firdaus on 26/11/2019.
 */
class TvShowViewModelTest{
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var viewModel: TvShowViewModel
//    @Mock
//    private lateinit var repo: Repository
//    @Mock
//    private lateinit var remote: RemoteDataSource
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.initMocks(this)
//        remote = RemoteDataSource()
//        repo = Repository(remote)
//        viewModel = TvShowViewModel(repo)
//    }
//
//    @Test
//    fun movies() = runBlocking {
//        launch { viewModel.getAllTvShows() }
//        viewModel.tvSHow.observeOnce { assertNotEquals(0, it.size) }
//    }

}