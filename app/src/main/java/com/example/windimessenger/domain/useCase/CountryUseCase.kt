package com.example.windimessenger.domain.useCase

import com.example.corekt.BaseUseCase
import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.domain.CountryUI
import com.example.windimessenger.domain.repository.CountryRepository
import kotlinx.coroutines.CoroutineScope

class CountryUseCase(private val repository: CountryRepository) :
    BaseUseCase<CountryUseCase.Params, List<CountryUI>>() {

    class Params

    override suspend fun execute(
        params: Params,
        scope: CoroutineScope
    ): Either<Failure, List<CountryUI>> {
        return repository.loadCountry()
    }
}