package com.example.windimessenger.data.api

import com.example.data.request.RefreshTokenRequest
import com.example.data.response.RegistrationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshApi {

    @POST("api/v1/users/refresh-token/")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): RegistrationResponse
}