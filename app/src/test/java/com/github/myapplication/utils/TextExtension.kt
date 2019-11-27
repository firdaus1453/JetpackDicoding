package com.github.myapplication.utils

import androidx.lifecycle.LiveData

/**
 * Created by Muhammad Firdaus on 26/11/2019.
 */
fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit?) {
    val observer = OneTimeObserver(handler = onChangeHandler)
    observe(observer, observer)
}