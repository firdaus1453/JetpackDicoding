package com.github.myapplication.data.source.remote

import android.content.Context
import com.github.myapplication.BuildConfig
import com.github.myapplication.base.BaseApiModel
import com.github.myapplication.data.model.MovieModel
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
    fun getAllData(
        @Path("type") type: String,
        @Path("filter") filter: String,
        @Query("api_key") apiKey: String
    ): Observable<BaseApiModel<List<MovieModel>>>

    companion object Factory {

        //In Seconds
        private const val TIMEOUT = 30L

        fun getApiService(context: Context): ApiService {
            val getApiService: ApiService by lazy {
                val mLoggingInterceptor = HttpLoggingInterceptor()
                mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                val mClient = OkHttpClient.Builder()
                    .addInterceptor(mLoggingInterceptor)
                    .addInterceptor(ChuckInterceptor(context))
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .build()

                val gson = GsonBuilder().setLenient().create()

                val mRetrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mClient)
                    .build()

                mRetrofit.create(ApiService::class.java)
            }
            return getApiService
        }

    }

}