package com.example.windimessenger.feature.sso.registration

import com.example.core.base.BaseScreenModel
import com.example.core.ext.isEmail
import com.example.windimessenger.domain.useCase.RegistrationUseCase
import org.koin.core.component.inject

class RegistrationScreenModel :
    BaseScreenModel<RegistrationState, RegistrationEvent>(RegistrationState.Default) {
    private val registrationUseCase: RegistrationUseCase by inject()
    fun changeName(value: String) {
        setState {
            state.value.copy(name = value, hasNameError = false)
        }
    }

    fun changeUserName(value: String) {
        setState {
            state.value.copy(userName = value, hasUserNameError = false)
        }
    }


    fun registration(phone: String) {
        if (state.value.isValid) {
            setState {
                state.value.copy(
                    hasNameError = false,
                    hasUserNameError = false
                )
            }
            launchOperation(operation = { scope ->
                registrationUseCase(
                    scope,
                    RegistrationUseCase.Params(phone, state.value.name, state.value.userName)
                )
            }, success = {
                setEvent(RegistrationEvent.Success)
            })
        } else {
            val hasNameError = state.value.name.isBlank()
            val hasUserNameError = !state.value.userName.isEmail()
            setState {
                state.value.copy(
                    hasNameError = hasNameError,
                    hasUserNameError = hasUserNameError
                )
            }
        }

    }

}