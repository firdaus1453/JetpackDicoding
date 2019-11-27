package com.github.myapplication.ui

import android.app.Application
import android.content.Context

/**
 * Created by Muhammad Firdaus on 27/11/2019.
 */
class MyApplication:Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null

        fun applicationContext(): Context? {
            return instance?.applicationContext
        }
    }
}
