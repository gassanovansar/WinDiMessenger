package com.example.windimessenger.di

import com.example.windimessenger.data.repository.CountryRepositoryImpl
import com.example.windimessenger.domain.repository.CountryRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CountryRepository> { CountryRepositoryImpl() }

}