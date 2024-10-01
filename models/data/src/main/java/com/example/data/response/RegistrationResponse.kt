package com.example.data.response

import kotlinx.serialization.Serializable

@Serializable
class RegistrationResponse(
    val refresh_token: String?,
    val access_token: String?,
    val user_id: String?,
)