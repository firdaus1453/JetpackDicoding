package com.github.myapplication.data.source.remote

import com.github.myapplication.BuildConfig
import com.github.myapplication.base.BaseApiModel
import com.github.myapplication.data.model.MovieModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Created by Muhammad Firdaus on 25/11/2019.
 */
interface ApiService {

    @GET("{type}/{filter}")
    fun getAllDataAsync(
        @Path("type") type: String,
        @Path("filter") filter: String,
        @Query("api_key") apiKey: String
    ): Deferred<BaseApiModel<List<MovieModel>>>

    @GET("{type}/{id}")
    fun getDataByIdAsync(
        @Path("type") type: String,
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Deferred<MovieModel>

    companion object Factory {

        //In Seconds
        private const val TIMEOUT = 30L

        fun getApiService(): ApiService {
            val getApiService: ApiService by lazy {
                val mLoggingInterceptor = HttpLoggingInterceptor()
                mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                val mClient = OkHttpClient.Builder()
                    .addInterceptor(mLoggingInterceptor)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .build()

                val mRetrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(mClient)
                    .build()

                mRetrofit.create(ApiService::class.java)
            }
            return getApiService
        }

    }

}