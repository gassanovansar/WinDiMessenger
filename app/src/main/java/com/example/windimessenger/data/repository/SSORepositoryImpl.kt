package com.example.windimessenger.data.repository

import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.corekt.apiCall
import com.example.data.request.AuthRequest
import com.example.data.request.CheckRequest
import com.example.domain.CheckUI
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

    override suspend fun check(phone: String, code: String): Either<Failure, CheckUI> {
        return apiCall(call = {
            api.checkAuth(CheckRequest(phone, code))
        }, mapResponse = {
            if (it.is_user_exists == true) {
                CheckUI.Auth
            } else {
                CheckUI.Registration
            }
        }
        )
    }
}