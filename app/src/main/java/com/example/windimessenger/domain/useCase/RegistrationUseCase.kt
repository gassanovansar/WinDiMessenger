package com.example.windimessenger.domain.useCase

import com.example.corekt.BaseUseCase
import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.windimessenger.domain.repository.SSORepository
import kotlinx.coroutines.CoroutineScope

class RegistrationUseCase(private val repository: SSORepository) :
    BaseUseCase<RegistrationUseCase.Params, Unit>() {
    class Params(
        val phone: String,
        val name: String,
        val userName: String
    )

    override suspend fun execute(params: Params, scope: CoroutineScope): Either<Failure, Unit> {
        return repository.registration(params.phone, params.name, params.userName)
    }
}