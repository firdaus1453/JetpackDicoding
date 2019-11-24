package com.github.myapplication.ui.content

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class ContentViewModelTest{
    private var mViewModel: ContentViewModel? = null

    @Before
    fun setUp() {
        mViewModel = ContentViewModel()
    }

    @Test
    fun movies() {
        assertNotNull(mViewModel?.getAllMovies)
        assertEquals(15, mViewModel?.getAllMovies?.size)
    }

    @Test
    fun tvshow() {
        assertNotNull(mViewModel?.getAllTvShows)
        assertEquals(15, mViewModel?.getAllTvShows?.size)
    }

}