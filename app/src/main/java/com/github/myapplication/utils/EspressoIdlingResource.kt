package com.github.myapplication.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Created by Muhammad Firdaus on 27/11/2019.
 */
object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }

    fun getEspressoIdlingResource(): IdlingResource {
        return espressoTestIdlingResource
    }
}