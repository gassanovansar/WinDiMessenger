package com.example.windimessenger.feature.auth

import com.example.core.base.BaseScreenModel

class AuthScreenModel : BaseScreenModel<AuthState, Any>(AuthState.Default) {


    fun changePhone(value: String) {
        setState { state.value.copy(phone = value) }
    }
}