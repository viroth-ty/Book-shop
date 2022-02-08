package org.viroth.bookstore.app.networking.http

import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.viroth.bookstore.app.BookApplication
import org.viroth.bookstore.app.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private val httpClient = OkHttpClient
    .Builder()
    .readTimeout(5000, TimeUnit.MILLISECONDS)
    .writeTimeout(5000, TimeUnit.MILLISECONDS)
    .addInterceptor(ChuckerInterceptor.Builder(BookApplication.context).build())
    .addInterceptor { chain ->
        val request: Request = chain
            .request()
            .newBuilder()
            .addHeader("Accept", "application/ld+json")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build()
        chain.proceed(request)
    }
    .build()

private val retrofit = Retrofit
    .Builder()
    .client(httpClient)
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

internal val appService: AppService = retrofit.create()
