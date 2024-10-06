package com.example.data.request

import kotlinx.serialization.Serializable

@Serializable
class EditProfileRequest(
    val name: String,
    val username: String,
    val birthday: String,
    val city: String,
    val vk: String,
    val instagram: String,
    val status: String,
    val avatar: EditProfileAvatarRequest
) {

}

@Serializable
class EditProfileAvatarRequest(
    val filename: String,
    val base_64: String
)