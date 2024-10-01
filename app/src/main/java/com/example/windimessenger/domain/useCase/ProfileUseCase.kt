package com.example.windimessenger.domain.useCase

import com.example.corekt.BaseUseCase
import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.domain.ProfileUI
import com.example.windimessenger.domain.repository.ProfileRepository
import kotlinx.coroutines.CoroutineScope

class ProfileUseCase(private val repository: ProfileRepository) :
    BaseUseCase<ProfileUseCase.Params, ProfileUI>() {

    class Params

    override suspend fun execute(
        params: Params,
        scope: CoroutineScope
    ): Either<Failure, ProfileUI> {
        return repository.loadProfile()
    }
}