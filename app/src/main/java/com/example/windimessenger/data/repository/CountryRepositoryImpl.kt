package com.example.windimessenger.data.repository

import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.domain.CountryUI
import com.example.mappers.toUI
import com.example.source.LocalSource
import com.example.windimessenger.domain.repository.CountryRepository

class CountryRepositoryImpl(private val localSource: LocalSource) : CountryRepository {
    override suspend fun loadCountry(): Either<Failure, List<CountryUI>> {
        return Either.Right(
            localSource.loadCountry().map { it.toUI() }
        )
    }
}