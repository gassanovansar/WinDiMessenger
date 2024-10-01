package com.example.mappers

import com.example.data.response.ProfileResponse
import com.example.domain.ProfileUI

fun ProfileResponse.toUI(): ProfileUI {
    val profile = this.profile_data
    return ProfileUI(
        name = profile.name.orEmpty(),
        username = profile.username.orEmpty(),
        birthday = profile.birthday.orEmpty(),
        city = profile.city.orEmpty(),
        vk = profile.vk.orEmpty(),
        instagram = profile.instagram.orEmpty(),
        status = profile.status.orEmpty(),
        avatar = profile.avatar.orEmpty(),
        id = profile.id.orEmpty(),
        online = profile.online ?: false,
        phone = profile.phone.orEmpty(),
        completedTask = profile.completed_task ?: 0
    )
}