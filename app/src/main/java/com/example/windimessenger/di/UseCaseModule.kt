package com.example.windimessenger.di

import com.example.windimessenger.domain.CountryUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory<CountryUseCase> { CountryUseCase() }

}