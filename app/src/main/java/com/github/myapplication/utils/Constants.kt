package com.github.myapplication.utils

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
object Constants {
    const val KEY_MOVIE = "KEY_MOVIE"
    const val KEY_TVSHOW = "KEY_TVSHOW"
    const val KEY_FRAGMENT = "KEY_FRAGMENT"
    const val CATEGORY_MOVIE = 1
    const val CATEGORY_TV = 2

    // Type data
    const val TYPE_MOVIE = "movie"
    const val TYPE_TV = "tv"
    const val FILTER_POPULAR = "popular"
    const val FILTER_TOP_RATED = "top_rated"

    // Server side constanta
    const val SERVER_ERROR_MESSAGE_DEFAULT = "Data not found"
    const val BASE_IMAGE_URL_MOVIE_DB = "https://image.tmdb.org/t/p/original"

    // Local database constanta
    const val TABLE_NAME = "movie"
    const val COLUMN_ID = "id"
    const val COLUMN_IDMOVIE = "idMovie"
    const val COLUMN_TITLE = "title"
    const val COLUMN_NAME = "name"
    const val COLUMN_OVERVIEW = "overview"
    const val COLUMN_RELEASEDATE = "releaseDate"
    const val COLUMN_FIRSTRELEASEDATE = "firstReleaseDate"
    const val COLUMN_POSTERPATH = "posterPath"
    const val COLUMN_VOTE_AVERAGE = "vote_average"
    const val COLUMN_FAVORITE = "favorite"
}