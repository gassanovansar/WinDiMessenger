package com.example.windimessenger.feature.auth


data class AuthState(val phone: String) {

    val isValid = phone.length == 10

    companion object {
        val Default = AuthState(phone = "")
    }
}