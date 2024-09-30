package com.example.windimessenger.di

import com.example.auth.domain.AuthUseCase
import com.example.favourite.domain.AddFavouritesUseCase
import com.example.favourite.domain.DeleteFavouritesUseCase
import com.example.favourite.domain.FavouriteCountFlowUseCase
import com.example.favourite.domain.FavouritesUseCase
import com.example.main.domain.OffersUseCase
import com.example.main.domain.VacanciesUseCase
import com.example.detail.domain.VacancyUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory<OffersUseCase> { OffersUseCase(get()) }
    factory<VacanciesUseCase> { VacanciesUseCase(get()) }
    factory<VacancyUseCase> { VacancyUseCase(get()) }
    factory<FavouritesUseCase> { FavouritesUseCase(get()) }
    factory<FavouriteCountFlowUseCase> { FavouriteCountFlowUseCase(get()) }
    factory<AddFavouritesUseCase> { AddFavouritesUseCase(get()) }
    factory<DeleteFavouritesUseCase> { DeleteFavouritesUseCase(get()) }
    factory<AuthUseCase> { AuthUseCase(get()) }
}