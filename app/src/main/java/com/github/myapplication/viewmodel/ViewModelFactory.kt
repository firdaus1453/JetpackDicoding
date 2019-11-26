package com.github.myapplication.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.myapplication.data.source.Repository
import com.github.myapplication.di.Injection
import com.github.myapplication.ui.detail.DetailViewModel
import com.github.myapplication.ui.main.MainViewModel

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
class ViewModelFactory private constructor(
    private val mRepository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(mRepository)
                isAssignableFrom(DetailViewModel::class.java) ->
                    DetailViewModel(mRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(mApplication: Application) =
            INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE
                        ?: ViewModelFactory(
                            Injection.provideRepository(mApplication.applicationContext)
                        )
                            .also { INSTANCE = it }
                }
    }
}