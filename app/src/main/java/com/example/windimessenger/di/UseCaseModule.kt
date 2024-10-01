package com.example.windimessenger.di

import com.example.windimessenger.domain.useCase.CountryUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory<CountryUseCase> { CountryUseCase(get()) }

}