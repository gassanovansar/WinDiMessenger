package com.example.windimessenger.data.api

import com.example.data.request.AuthRequest
import com.example.data.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SSOApi {

    @POST("api/v1/users/send-auth-code/")
    suspend fun sendAuth(@Body request: AuthRequest): AuthResponse
}