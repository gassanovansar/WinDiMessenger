package com.example.windimessenger.domain.useCase

import com.example.corekt.BaseUseCase
import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.windimessenger.domain.repository.ProfileRepository
import kotlinx.coroutines.CoroutineScope

class EditProfileUseCase(private val repository: ProfileRepository) :
    BaseUseCase<EditProfileUseCase.Params, Unit>() {
    class Params(
        val name: String,
        val email: String,
        val birthday: String,
        val city: String,
        val vk: String,
        val instagram: String
    )

    override suspend fun execute(params: Params, scope: CoroutineScope): Either<Failure, Unit> {
        return repository.editProfile(
            params.name, params.email, params.birthday, params.city, params.vk, params.instagram
        )
    }
}