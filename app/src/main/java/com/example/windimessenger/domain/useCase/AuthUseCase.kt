package com.example.windimessenger.domain.useCase

import com.example.corekt.BaseUseCase
import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.windimessenger.domain.repository.SSORepository
import kotlinx.coroutines.CoroutineScope

class AuthUseCase(private val repository: SSORepository) :
    BaseUseCase<AuthUseCase.Params, Boolean>() {
    class Params(val phone: String)

    override suspend fun execute(params: Params, scope: CoroutineScope): Either<Failure, Boolean> {
        return repository.auth(params.phone)
    }
}