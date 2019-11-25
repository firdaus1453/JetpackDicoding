package com.github.myapplication.data.source

import com.github.myapplication.base.BaseDataSource
import com.github.myapplication.data.model.MovieModel

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
interface DataSource : BaseDataSource {

    // remote source
    fun getAllData(type: String, filter: String, callback: GetAllDataCallback)

    // callback
    interface GetAllDataCallback : BaseDataSource.ResponseCallback<List<MovieModel>>

    interface GetDataByIdCallback : BaseDataSource.ResponseCallback<MovieModel>
}