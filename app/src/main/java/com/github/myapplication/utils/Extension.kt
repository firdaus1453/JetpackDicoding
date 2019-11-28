package com.github.myapplication.utils

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.github.myapplication.R
import com.github.myapplication.utils.Constants.SERVER_ERROR_MESSAGE_DEFAULT
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Muhammad Firdaus on 24/11/2019.
 */
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun View.visible(

) {
    visibility = View.VISIBLE
}

fun View.gone(

) {
    visibility = View.GONE
}

fun View.showSnackbarDefault(
    message: String,
    duration: Int = 0
) {
    val finalMessage = if (TextUtils.isEmpty(message)) {
        SERVER_ERROR_MESSAGE_DEFAULT
    } else {
        message
    }

    val finalDuration = if (duration == 0) {
        Snackbar.LENGTH_SHORT
    } else {
        duration
    }

    Snackbar.make(this, finalMessage, finalDuration).show()
}

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this).get(viewModelClass)

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this).get(viewModelClass)

fun createCircularProgressDrawable(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 4f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.setColorSchemeColors(ContextCompat.getColor(context, R.color.colorAccent))
    circularProgressDrawable.start()
    return circularProgressDrawable
}