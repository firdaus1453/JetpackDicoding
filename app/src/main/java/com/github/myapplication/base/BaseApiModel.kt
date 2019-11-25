package com.github.myapplication.base

/**
 * Created by Muhammad Firdaus on 18/08/2019.
 */
class BaseApiModel<T>(
    var page: Int,
    var total_results: Int,
    var total_pages: Int,
    val message: String? = null,
    var results: T? = null
)