package com.example.windimessenger.feature.sso.sendSms

sealed interface SendSmsEvent {

    object Auth : SendSmsEvent

    object Registration : SendSmsEvent
}