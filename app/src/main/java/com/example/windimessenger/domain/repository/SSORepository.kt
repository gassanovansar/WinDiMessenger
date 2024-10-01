package com.example.windimessenger.domain.repository

import com.example.corekt.Either
import com.example.corekt.Failure

interface SSORepository {
    suspend fun auth(phone: String): Either<Failure, Boolean>
}