package com.example.data.response

import kotlinx.serialization.Serializable

@Serializable
class CheckResponse(
    val refresh_token: String?,
    val access_token: String?,
    val user_id: String?,
    val is_user_exists: Boolean?,
)