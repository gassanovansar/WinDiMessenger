package com.example.windimessenger.di

import com.example.auth.data.Api
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModile = module {
    factory { get<Retrofit>().create(Api::class.java) }
}

