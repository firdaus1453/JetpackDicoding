package com.github.myapplication.utils

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.myapplication.data.source.Repository
import com.github.myapplication.di.Injection
import com.github.myapplication.ui.content.ContentViewModel

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class ViewModelFactory private constructor(
    private val mApplication: Application,
    private val mRepository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(ContentViewModel::class.java) ->
                    ContentViewModel(mApplication)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(mApplication: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(mApplication,
                    Injection.provideRepository(mApplication.applicationContext))
                    .also { INSTANCE = it }
            }
    }
}