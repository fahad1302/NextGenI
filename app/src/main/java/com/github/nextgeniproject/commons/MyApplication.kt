package com.github.nextgeniproject.commons

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo: NetworkInfo? = null
            if (connectivityManager != null) {
                activeNetworkInfo = connectivityManager.activeNetworkInfo
            }
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    companion object {
        @get:Synchronized
        var instance: MyApplication? = null
            private set
        private var retrofit: Retrofit? = null
        private const val AUTH = "Authorization"
        private const val DEVICE_TYPE = "x-device-type"
        private const val OS_VERSION = "x-os-version"
        private const val APP_VERSION = "x-app-version"
        private const val AUTH_VALUE = "Bearer 14028_J8g1GvFLkNOl31pBM3J8enLJKQcFLSJM"
        private const val DEVICE_TYPE_VALUE = "android"
        private const val OS_VERSION_VALUE = "9.1"
        private const val APP_VERSION_VALUE = "18"

        val retrofitClient: Retrofit?
            get() {
                if (retrofit == null) {
                    val httpClient = OkHttpClient.Builder()
                    httpClient.addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                                .addHeader(AUTH, AUTH_VALUE)
                                .addHeader(DEVICE_TYPE, DEVICE_TYPE_VALUE)
                                .addHeader(OS_VERSION, OS_VERSION_VALUE)
                                .addHeader(APP_VERSION, APP_VERSION_VALUE)
                                .build()
                        chain.proceed(request)
                    }
                    retrofit = Retrofit.Builder()
                            .client(httpClient.build())
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(Constants.BASE_URL)
                            .build()
                }
                return retrofit
            }
    }
}