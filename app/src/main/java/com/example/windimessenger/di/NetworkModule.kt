package com.example.windimessenger.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder().baseUrl("https://plannerok.ru/docs#/)")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}