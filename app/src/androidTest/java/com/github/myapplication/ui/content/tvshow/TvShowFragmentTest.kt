package com.github.myapplication.ui.content.tvshow

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.github.myapplication.R
import com.github.myapplication.testing.SingleFragmentActivity
import com.github.myapplication.ui.main.tvshow.TvShowFragment
import com.github.myapplication.utils.EspressoIdlingResource
import com.github.myapplication.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class TvShowFragmentTest {
    @Rule
    @JvmField
    val activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java)
    private val tvShowFragment = TvShowFragment()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
        activityRule.activity.setFragment(tvShowFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun getData() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.recycler_movie)).check(RecyclerViewItemCountAssertion(20))
    }
}