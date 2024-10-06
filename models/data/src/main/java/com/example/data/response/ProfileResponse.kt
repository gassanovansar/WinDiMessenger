package com.example.data.response

import kotlinx.serialization.Serializable


@Serializable
class ProfileResponse(
    val profile_data: ProfileData
)

@Serializable
class ProfileData(
    val name: String?,
    val username: String?,
    val birthday: String?,
    val city: String?,
    val vk: String?,
    val instagram: String?,
    val status: String?,
    val avatar: String?,
    val id: String?,
    val online: Boolean?,
    val phone: String?,
    val completed_task: Int?,
)