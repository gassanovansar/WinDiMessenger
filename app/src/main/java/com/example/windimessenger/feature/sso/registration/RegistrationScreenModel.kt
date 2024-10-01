package com.example.windimessenger.feature.sso.registration

import com.example.core.base.BaseScreenModel
import com.example.core.ext.isEmail

class RegistrationScreenModel : BaseScreenModel<RegistrationState, Any>(RegistrationState.Default) {
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
//            launchOperation(operation = { scope ->
//                authUseCase(scope, AuthUseCase.Params(state.phone, state.password))
//            }, success = {
//                postSideEffectLocal(AuthEvent.Success)
//            })
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