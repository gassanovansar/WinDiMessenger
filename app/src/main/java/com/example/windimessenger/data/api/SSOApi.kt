package com.example.windimessenger.data.api

import com.example.data.request.AuthRequest
import com.example.data.request.CheckRequest
import com.example.data.request.RegistrationRequest
import com.example.data.response.AuthResponse
import com.example.data.response.CheckResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SSOApi {

    @POST("api/v1/users/send-auth-code/")
    suspend fun sendAuth(@Body request: AuthRequest): AuthResponse

    @POST("api/v1/users/check-auth-code/")
    suspend fun checkAuth(@Body request: CheckRequest): CheckResponse

    @POST("api/v1/users/register/")
    suspend fun registration(@Body request: RegistrationRequest): CheckResponse

}