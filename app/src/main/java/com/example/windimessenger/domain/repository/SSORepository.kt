package com.example.windimessenger.domain.repository

import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.domain.CheckUI

interface SSORepository {
    suspend fun auth(phone: String): Either<Failure, Boolean>
    suspend fun check(phone: String,code:String): Either<Failure, CheckUI>
}