package com.example.windimessenger.feature.editProfile

import com.example.core.base.BaseScreenModel
import com.example.core.ext.toLong
import com.example.domain.CountryUI
import com.example.windimessenger.domain.useCase.ProfileUseCase
import org.koin.core.component.inject

class EditProfileScreenModel : BaseScreenModel<EditProfileState, Any>(EditProfileState.Default) {

    private val profileUseCase: ProfileUseCase by inject()

    fun loadProfile() {
        launchOperation(operation = { scope ->
            profileUseCase(scope, ProfileUseCase.Params())
        }, success = {
            setState {
                state.value.copy(
                    name = it.name,
                    username = it.username,
                    birthday = it.birthday.toLong(),
                    city = it.city,
                    vk = it.vk,
                    instagram = it.instagram
                )
            }
        })
    }

    fun changeName(value: String) {
        setState { state.value.copy(name = value) }
    }
    fun changeEmail(value: String) {
        setState { state.value.copy(username = value) }
    }
    fun changeBirthday(value: Long?) {
        setState { state.value.copy(birthday = value) }
    }

    fun changeCity(value: CountryUI) {
        setState { state.value.copy(city = value.title) }
    }

    fun changeVK(value: String) {
        setState { state.value.copy(vk = value) }
    }

    fun changeInstagram(value: String) {
        setState { state.value.copy(instagram = value) }
    }
}