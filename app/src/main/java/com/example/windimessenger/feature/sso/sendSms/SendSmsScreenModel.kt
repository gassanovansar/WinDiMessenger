package com.example.windimessenger.feature.sso.sendSms

import com.example.core.base.BaseScreenModel
import com.example.domain.CheckUI
import com.example.windimessenger.domain.useCase.CheckUseCase
import org.koin.core.component.inject

class SendSmsScreenModel : BaseScreenModel<SendSmsState, SendSmsEvent>(SendSmsState.Default) {

    private val checkUseCase: CheckUseCase by inject()

    fun changeOtp(value: String) {
        setState { state.value.copy(otp = value) }
    }

    fun check(phone: String) {
        launchOperation(operation = { scope ->
            checkUseCase(scope, CheckUseCase.Params(phone, state.value.otp))
        }, success = {
            when (it) {
                CheckUI.Auth -> setEvent(SendSmsEvent.Auth)
                CheckUI.Registration -> setEvent(SendSmsEvent.Registration)
            }
        })
    }
}