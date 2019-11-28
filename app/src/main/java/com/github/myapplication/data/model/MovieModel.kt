package com.github.myapplication.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.myapplication.utils.Constants.COLUMN_FAVORITE
import com.github.myapplication.utils.Constants.COLUMN_FIRSTRELEASEDATE
import com.github.myapplication.utils.Constants.COLUMN_ID
import com.github.myapplication.utils.Constants.COLUMN_IDMOVIE
import com.github.myapplication.utils.Constants.COLUMN_NAME
import com.github.myapplication.utils.Constants.COLUMN_OVERVIEW
import com.github.myapplication.utils.Constants.COLUMN_POSTERPATH
import com.github.myapplication.utils.Constants.COLUMN_RELEASEDATE
import com.github.myapplication.utils.Constants.COLUMN_TITLE
import com.github.myapplication.utils.Constants.COLUMN_VOTE_AVERAGE
import com.github.myapplication.utils.Constants.TABLE_NAME
import kotlinx.android.parcel.Parcelize

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
@Entity(tableName = TABLE_NAME)
@Parcelize
data class MovieModel(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    var id: Int? = 0,

    @ColumnInfo(name = COLUMN_IDMOVIE)
    var idMovie: Int? = 0,

    @ColumnInfo(name = COLUMN_TITLE)
    var title: String? = "",

    @ColumnInfo(name = COLUMN_NAME)
    var name: String? = "",

    @ColumnInfo(name = COLUMN_OVERVIEW)
    var overview: String? = "",

    @ColumnInfo(name = COLUMN_POSTERPATH)
    var poster_path: String? = "",

    @ColumnInfo(name = COLUMN_RELEASEDATE)
    var release_date: String? = "",

    @ColumnInfo(name = COLUMN_FIRSTRELEASEDATE)
    var first_air_date: String? = "",

    @ColumnInfo(name = COLUMN_VOTE_AVERAGE)
    var vote_average: Double? = 0.0,

    @ColumnInfo(name = COLUMN_FAVORITE)
    var favorite: String? = ""
) : Parcelable