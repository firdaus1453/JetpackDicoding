package com.github.myapplication.base

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * Created by Muhammad Firdaus on 23/11/2019.
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    var eventShowProgress = ObservableBoolean()
    var eventGlobalMessage = MutableLiveData<String>()
    open fun start() {}
}