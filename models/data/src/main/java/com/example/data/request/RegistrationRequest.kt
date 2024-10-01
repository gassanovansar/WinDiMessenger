package com.example.data.request

import kotlinx.serialization.Serializable

@Serializable
class RegistrationRequest(
    val phone: String,
    val name: String,
    val username: String
)