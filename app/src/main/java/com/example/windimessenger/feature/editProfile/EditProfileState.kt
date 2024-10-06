package com.example.windimessenger.feature.editProfile

import com.example.domain.ProfileUI

data class EditProfileState(
    val name: String,
    val username: String,
    val birthday: String,
    val city: String,
    val vk: String,
    val instagram: String
) {

    companion object {
        val Default = EditProfileState(
            name = "",
            username = "",
            birthday = "",
            city = "",
            vk = "",
            instagram = ""
        )
    }
}