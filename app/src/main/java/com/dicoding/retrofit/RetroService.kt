package com.dicoding.retrofit

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroService {
    private const val BASE_URL = "https://api.github.com/"
    private var client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Token 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")
            .build()
        chain.proceed(newRequest)
    }.build()
    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstansiasi: ApiUser = retrofit.create(ApiUser::class.java)
}