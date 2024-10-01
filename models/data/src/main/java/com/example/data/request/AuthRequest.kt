package com.example.data.request

import kotlinx.serialization.Serializable

@Serializable
class AuthRequest(
    val phone: String
)