package com.github.myapplication.utils.extension

import android.content.Context
import android.widget.Toast

/**
 * Created by Muhammad Firdaus on 23/11/2019.
 */
fun Context.showToast(
    message: String
) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}