package com.example.windimessenger.di

import org.koin.core.context.startKoin

object KoinInjector {

    val koinApp = startKoin {
        modules(
            listOf(
                networkModule,
                apiModile,
                useCaseModule,
                repositoryModule,
                sourceModule,
                managerModule
            )
        )
    }
}