package com.example.windimessenger.di

import com.example.windimessenger.data.api.*
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModile = module {
    factory { get<Retrofit>().create(SSOApi::class.java) }
    factory { get<Retrofit>().create(ProfileApi::class.java) }
}

