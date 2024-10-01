package com.example.windimessenger.di

import com.example.windimessenger.domain.useCase.*
import org.koin.dsl.module

val useCaseModule = module {

    factory<CountryUseCase> { CountryUseCase(get()) }
    factory<AuthUseCase> { AuthUseCase(get()) }
    factory<CheckUseCase> { CheckUseCase(get()) }
    factory<RegistrationUseCase> { RegistrationUseCase(get()) }

}