package com.example.windimessenger.feature.sso.auth

import com.example.domain.CountryUI


data class AuthState(
    val phone: String,
    val hasPhoneError: Boolean,
    val country: CountryUI
) {

    val isValid = phone.length == country.validation

    companion object {
        val Default = AuthState(phone = "", country = CountryUI.Default, hasPhoneError = false)
    }
}