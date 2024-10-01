package com.example.windimessenger.di

import com.example.windimessenger.data.repository.*
import com.example.windimessenger.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single<CountryRepository> { CountryRepositoryImpl(get()) }
    single<SSORepository> { SSORepositoryImpl(get(), get()) }

}