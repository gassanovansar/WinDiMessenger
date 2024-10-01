package com.example.windimessenger.domain

import com.example.corekt.BaseUseCase
import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.domain.CountryUI
import kotlinx.coroutines.CoroutineScope

class CountryUseCase : BaseUseCase<CountryUseCase.Params, List<CountryUI>>() {

    class Params

    override suspend fun execute(
        params: Params,
        scope: CoroutineScope
    ): Either<Failure, List<CountryUI>> {
        return Either.Right(emptyList())
    }
}