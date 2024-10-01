package com.example.windimessenger.feature.sso.auth

import com.example.core.base.BaseScreenModel
import com.example.domain.CountryUI
import com.example.windimessenger.domain.useCase.AuthUseCase
import com.example.windimessenger.domain.useCase.CountryUseCase
import org.koin.core.component.inject

class AuthScreenModel : BaseScreenModel<AuthState, AuthEvent>(AuthState.Default) {
    private val countryUseCase: CountryUseCase by inject()
    private val authUseCase: AuthUseCase by inject()
    fun changePhone(value: String) {
        setState { state.value.copy(phone = value) }
    }

    fun loadCountry() {
        launchOperation(operation = { scope ->
            countryUseCase(scope, CountryUseCase.Params())
        }, success = {
            setState { state.value.copy(country = it.firstOrNull() ?: CountryUI.Default) }
        })
    }

    fun changeCounty(value: CountryUI) {
        setState { state.value.copy(country = value, phone = "") }
    }

    fun auth() {
        launchOperation(operation = { scope ->
            authUseCase(
                scope,
                AuthUseCase.Params("${state.value.country.code}${state.value.phone}")
            )
        }, success = {
            if (it) {
                setEvent(AuthEvent.Success)
            }
        })
    }
}