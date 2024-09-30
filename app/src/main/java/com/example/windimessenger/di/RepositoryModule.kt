package com.example.windimessenger.di

import com.example.auth.data.AuthRepositoryImpl
import com.example.auth.domain.AuthRepository
import com.example.detail.domain.DetailRepository
import com.example.data.MainRepositoryImpl
import com.example.favourite.domain.FavouriteRepository
import com.example.main.domain.MainRepository
import com.example.detail.data.DetailRepositoryImpl
import com.example.favourite.data.FavouriteRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }
    single<DetailRepository> { DetailRepositoryImpl(get()) }
    single<FavouriteRepository> { FavouriteRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(),get()) }
}