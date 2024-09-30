package com.example.windimessenger.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder().baseUrl("https://api.adampay.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}