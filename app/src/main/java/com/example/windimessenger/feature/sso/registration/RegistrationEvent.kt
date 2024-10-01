package com.example.windimessenger.feature.sso.registration

sealed interface RegistrationEvent {
    object Success : RegistrationEvent
}