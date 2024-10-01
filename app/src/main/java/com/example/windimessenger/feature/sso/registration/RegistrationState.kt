package com.example.windimessenger.feature.sso.registration

import com.example.core.ext.isEmail

data class RegistrationState(
    val name: String,
    val hasNameError: Boolean,
    val userName: String,
    val hasUserNameError: Boolean,
) {

    val isValid = name.isNotBlank() && userName.isEmail()

    companion object {
        val Default =
            RegistrationState(name = "", hasNameError = false, userName = "", hasUserNameError = false)
    }
}