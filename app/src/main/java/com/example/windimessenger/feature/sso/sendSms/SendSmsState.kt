package com.example.windimessenger.feature.sso.sendSms

data class SendSmsState(val otp: String) {

    val isValid = otp.length == 6

    companion object {
        val Default = SendSmsState(otp = "")
    }
}