package com.github.myapplication.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.myapplication.data.source.Repository

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
abstract class BaseViewModel(application: Application) :
    AndroidViewModel(application) {
    private val mRepository = Repository()
    fun getRepository() = mRepository

    var eventGlobalMessage = MutableLiveData<String>()
    var eventShowProgress = MutableLiveData<Boolean>()
}