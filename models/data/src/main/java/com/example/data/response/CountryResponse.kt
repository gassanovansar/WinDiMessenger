package com.example.data.response

import kotlinx.serialization.Serializable

@Serializable
class CountryResponse(
    val title: String?,
    val code: String?,
    val validation: Int?
)