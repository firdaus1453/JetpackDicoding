package com.github.myapplication.di

import android.content.Context
import com.github.myapplication.data.source.Repository
import com.github.myapplication.data.source.remote.RemoteDataSource

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
object Injection {
    fun provideRepository(context: Context): Repository {
        return Repository.getInstance(RemoteDataSource(context))
    }
}