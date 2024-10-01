package com.example.windimessenger.feature.country

import com.example.core.base.BaseScreenModel
import com.example.windimessenger.domain.useCase.CountryUseCase
import org.koin.core.component.inject

class CountryScreenModel : BaseScreenModel<CountryState, Any>(CountryState.Default) {
    val countryUseCase: CountryUseCase by inject()

    fun loadCountry() {
        launchOperation(operation = { scope ->
            countryUseCase(scope, CountryUseCase.Params())
        }, success = {
            setState { state.value.copy(country = it) }
        })
    }
}