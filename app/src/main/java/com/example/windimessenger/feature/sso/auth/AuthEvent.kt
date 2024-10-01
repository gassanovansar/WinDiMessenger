package com.example.windimessenger.feature.sso.auth

sealed interface AuthEvent {

    object Success : AuthEvent
}