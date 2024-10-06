package com.example.windimessenger.feature.editProfile

import com.example.core.ext.isEmail


data class EditProfileState(
    val name: String,
    val hasNameError: Boolean,
    val username: String,
    val hasUsernameError: Boolean,
    val birthday: Long?,
    val hasBirthdayError: Boolean,
    val city: String,
    val hasCityError: Boolean,
    val vk: String,
    val hasVkError: Boolean,
    val instagram: String,
    val hasInstagramError: Boolean
) {
    val isValid =
        name.isNotBlank() && username.isEmail() && birthday != null && city.isNotBlank() && vk.isNotBlank() && instagram.isNotBlank()

    companion object {
        val Default = EditProfileState(
            name = "",
            hasNameError = false,
            username = "",
            hasUsernameError = false,
            birthday = null,
            hasBirthdayError = false,
            city = "",
            hasCityError = false,
            vk = "",
            hasVkError = false,
            instagram = "",
            hasInstagramError = false,
        )
    }
}