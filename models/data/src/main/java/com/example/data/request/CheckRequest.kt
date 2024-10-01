package com.example.data.request

import kotlinx.serialization.Serializable

@Serializable
class CheckRequest(
    val phone: String,
    val code: String
)