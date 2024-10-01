package com.example.windimessenger.data.repository

import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.corekt.apiCall
import com.example.data.request.AuthRequest
import com.example.windimessenger.data.api.SSOApi
import com.example.windimessenger.domain.repository.SSORepository

class SSORepositoryImpl(private val api: SSOApi) : SSORepository {
    override suspend fun auth(phone: String): Either<Failure, Boolean> {
        return apiCall(call = {
            api.sendAuth(AuthRequest(phone))
        }, mapResponse = {
            it.is_success ?: false
        })
    }
}