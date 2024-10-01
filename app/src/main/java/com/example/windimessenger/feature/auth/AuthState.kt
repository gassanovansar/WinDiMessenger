package com.example.windimessenger.feature.auth

import com.example.domain.CountryUI


data class AuthState(
    val phone: String,
    val country: CountryUI,
) {

    val isValid = phone.length == 10

    companion object {
        val Default = AuthState(phone = "", country = CountryUI.Default)
    }
}