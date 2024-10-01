package com.example.windimessenger.data.repository

import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.corekt.apiCall
import com.example.domain.ProfileUI
import com.example.mappers.toUI
import com.example.windimessenger.data.api.ProfileApi
import com.example.windimessenger.domain.repository.ProfileRepository

class ProfileRepositoryImpl(private val api: ProfileApi) : ProfileRepository {
    override suspend fun loadProfile(): Either<Failure, ProfileUI> {
        return apiCall(call = {
            api.loadProfile()
        }, mapResponse = {
            it.toUI()
        })
    }
}