package com.github.myapplication.base

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.myapplication.data.source.Repository
import com.github.myapplication.di.Injection

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
abstract class BaseViewModel(
    application: Application,
    val mRepository: Repository = Injection.provideRepository(application)
) :
    ViewModel() {
    var eventGlobalMessage = MutableLiveData<String>()
    var eventShowProgress = MutableLiveData<Boolean>()
}