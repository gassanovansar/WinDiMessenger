package com.example.windimessenger.data.api

import com.example.data.response.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileApi {
    @GET("api/v1/users/me")
    suspend fun loadProfile(): ProfileResponse
}