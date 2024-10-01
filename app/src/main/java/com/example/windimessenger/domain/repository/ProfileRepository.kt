package com.example.windimessenger.domain.repository

import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.domain.ProfileUI

interface ProfileRepository {

    suspend fun loadProfile(): Either<Failure, ProfileUI>
}