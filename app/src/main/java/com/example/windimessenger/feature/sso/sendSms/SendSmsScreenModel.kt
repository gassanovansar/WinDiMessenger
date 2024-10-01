package com.example.windimessenger.feature.sso.sendSms

import com.example.core.base.BaseScreenModel

class SendSmsScreenModel : BaseScreenModel<SendSmsState, Any>(SendSmsState.Default) {

    fun changeOtp(value: String) {
        setState { state.value.copy(otp = value) }
    }
}