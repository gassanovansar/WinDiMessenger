package com.example.windimessenger.di

import com.example.managers.SessionManager
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    factoryOf(::createRetrofit)
}


fun createRetrofit(
    sessionManager: SessionManager,
): Retrofit {

    return Retrofit.Builder().baseUrl("https://plannerok.ru/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder().addInterceptor { chain ->
                val request =
                    chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer ${sessionManager.token}")
                        .build()
                chain.proceed(request)

            }.build()
        )
        .build()
}