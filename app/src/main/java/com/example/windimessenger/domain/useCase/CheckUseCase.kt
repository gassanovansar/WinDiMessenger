package com.example.windimessenger.domain.useCase

import com.example.corekt.BaseUseCase
import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.domain.CheckUI
import com.example.windimessenger.domain.repository.SSORepository
import kotlinx.coroutines.CoroutineScope

class CheckUseCase(private val repository: SSORepository) :
    BaseUseCase<CheckUseCase.Params, CheckUI>() {

    class Params(val phone: String, val code: String)

    override suspend fun execute(params: Params, scope: CoroutineScope): Either<Failure, CheckUI> {
        return repository.check(params.phone, params.code)
    }
}