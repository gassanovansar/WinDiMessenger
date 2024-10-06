package com.example.windimessenger.data.api

import com.example.data.request.EditProfileRequest
import com.example.data.response.ProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface ProfileApi {
    @GET("api/v1/users/me")
    suspend fun loadProfile(): ProfileResponse

    @PUT("api/v1/users/me/")
    suspend fun editProfile(@Body request: EditProfileRequest)
}