package com.example.data.response

import kotlinx.serialization.Serializable

@Serializable
class AuthResponse(
    val is_success: Boolean?
)