package com.github.myapplication.ui.content.movie

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.github.myapplication.R
import com.github.myapplication.testing.SingleFragmentActivity
import com.github.myapplication.ui.main.movie.MovieFragment
import com.github.myapplication.utils.RecyclerViewItemCountAssertion
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class MovieFragmentTest {
    @Rule
    @JvmField
    val activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java)
    private val moviesFragment = MovieFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(moviesFragment)
    }

    @Test
    fun getData() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.recycler_movie)).check(RecyclerViewItemCountAssertion(15))
    }
}