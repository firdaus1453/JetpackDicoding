package com.github.myapplication.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.myapplication.data.source.Repository
import com.github.myapplication.di.Injection

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
abstract class BaseViewModel(application: Application, val mRepository: Repository = Injection.provideRepository(application)) :
    AndroidViewModel(application){
    var eventGlobalMessage = MutableLiveData<String>()
    var eventShowProgress = MutableLiveData<Boolean>()
}