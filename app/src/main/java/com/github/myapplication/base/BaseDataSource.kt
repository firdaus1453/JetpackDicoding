package com.github.myapplication.base

/**
 * Created by Muhammad Firdaus on 18/08/2019.
 */
interface BaseDataSource {
    interface ResponseCallback<T> {
        fun onSuccess(data: T)
        fun onFinish()
        fun onFailed(statusCode: Int, errorMessage: String? = "")
    }
}