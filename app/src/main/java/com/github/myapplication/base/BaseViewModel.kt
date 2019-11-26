package com.github.myapplication.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
abstract class BaseViewModel :
    ViewModel() {
    var eventGlobalMessage = MutableLiveData<String>()
    var eventShowProgress = MutableLiveData<Boolean>()
}