package com.github.myapplication.utils.extension

import android.text.TextUtils
import android.view.View
import androidx.core.content.ContextCompat
import com.github.myapplication.R
import com.github.myapplication.utils.Helper.Const.SERVER_ERROR_MESSAGE_DEFAULT
import com.github.myapplication.utils.Helper.Const.SNACKBAR_TIMER_SHOWING_DEFAULT
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Muhammad Firdaus on 23/11/2019.
 */
fun View.showSnackbarWithCustomColor(
    message: String,
    textColor: Int, backgroundColor: Int,
    duration: Int = 5000
) {
    val finalMessage = if (TextUtils.isEmpty(message)) {
        SERVER_ERROR_MESSAGE_DEFAULT
    } else {
        message
    }

    val finalDuration = if (duration == 0) {
        SNACKBAR_TIMER_SHOWING_DEFAULT
    } else {
        duration
    }

    val finalTextColor = if (textColor == 0) {
        ContextCompat.getColor(this.context, R.color.mainWhite)
    } else {
        textColor
    }

    val finalBackgroundColor = if (textColor == 0) {
        ContextCompat.getColor(this.context, R.color.greyBackgroundDefault)
    } else {
        backgroundColor
    }

    val snackView = Snackbar.make(this, finalMessage, finalDuration)
    snackView.setActionTextColor(finalTextColor)
    snackView.view.setBackgroundColor(finalBackgroundColor)
    snackView.show()
}

fun View.showSnackbarDefault(
    message: String,
    duration: Int = 5000
) {
    val finalMessage = if (TextUtils.isEmpty(message)) {
        SERVER_ERROR_MESSAGE_DEFAULT
    } else {
        message
    }

    val finalDuration = if (duration == 0) {
        SNACKBAR_TIMER_SHOWING_DEFAULT
    } else {
        duration
    }

    Snackbar.make(this, finalMessage, finalDuration).show()
}