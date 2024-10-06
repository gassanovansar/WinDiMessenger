package com.example.data.request

import kotlinx.serialization.Serializable

@Serializable
class RefreshTokenRequest(
    val refresh_token: String
) {
}