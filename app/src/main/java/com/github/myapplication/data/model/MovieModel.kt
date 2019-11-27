package com.github.myapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
@Parcelize
data class MovieModel(
    var id: Int? = 0,

    var title: String? = "",

    var name: String? = "",

    var overview: String? = "",

    var poster_path: String? = "",

    var release_date: String? = "",

    var first_air_date: String? = "",

    var vote_average: Double? = 0.0,

    var favorite: String? = ""
): Parcelable