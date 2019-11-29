package com.github.myapplication.ui.main.favorite

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.github.myapplication.R
import com.github.myapplication.testing.SingleFragmentActivity
import com.github.myapplication.utils.EspressoIdlingResource
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Muhammad Firdaus on 29/11/2019.
 */
class MainFavoriteFragmentTest {
    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)

    private val mainFavoriteFragment = MainFavoriteFragment()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
        activityRule.activity.setFragment(mainFavoriteFragment)
    }

    @Test
    fun favoriteFragmentTest() {
        Espresso.onView(ViewMatchers.withId(R.id.tl_main_favorite))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pager_main_favorite))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}