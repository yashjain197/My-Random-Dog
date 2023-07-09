package com.example.randomdog.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

object  RetrofitHelper {
    private const val BASE_URL = "https://dog.ceo/api/breeds/image/"

    private var mClient: OkHttpClient? = null

    val client: OkHttpClient
        @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
        get() {
            if (mClient == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                /// show all JSON in logCat
                mClient = httpBuilder.build()

            }
            return mClient!!
        }

    fun getInstance(): Retrofit{
        Log.d("MYRANDOMDOG", "get Instance")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}