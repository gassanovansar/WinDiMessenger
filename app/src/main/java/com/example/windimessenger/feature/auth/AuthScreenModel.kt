package com.example.windimessenger.feature.auth

import com.example.core.base.BaseScreenModel
import com.example.domain.CountryUI
import com.example.windimessenger.domain.useCase.CountryUseCase
import org.koin.core.component.inject

class AuthScreenModel : BaseScreenModel<AuthState, Any>(AuthState.Default) {
    val countryUseCase: CountryUseCase by inject()
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
}