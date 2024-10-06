package com.example.windimessenger.feature.editProfile

import com.example.core.base.BaseScreenModel
import com.example.core.ext.isEmail
import com.example.core.ext.toDateIntUI
import com.example.core.ext.toLong
import com.example.domain.CountryUI
import com.example.windimessenger.domain.useCase.EditProfileUseCase
import com.example.windimessenger.domain.useCase.ProfileUseCase
import org.koin.core.component.inject

class EditProfileScreenModel : BaseScreenModel<EditProfileState, Any>(EditProfileState.Default) {

    private val profileUseCase: ProfileUseCase by inject()
    private val editProfileUseCase: EditProfileUseCase by inject()

    fun loadProfile() {
        launchOperation(operation = { scope ->
            profileUseCase(scope, ProfileUseCase.Params())
        }, success = {
            setState {
                state.value.copy(
                    ava = it.avatar,
                    name = it.name,
                    hasNameError = false,
                    username = it.username,
                    hasUsernameError = false,
                    birthday = it.birthday.toLong(),
                    city = it.city,
                    hasCityError = false,
                    vk = it.vk,
                    hasVkError = false,
                    instagram = it.instagram,
                    hasInstagramError = false
                )
            }
        })
    }

    fun changeName(value: String) {
        setState { state.value.copy(name = value, hasNameError = false) }
    }

    fun changeEmail(value: String) {
        setState { state.value.copy(username = value, hasUsernameError = false) }
    }

    fun changeBirthday(value: Long?) {
        setState { state.value.copy(birthday = value, hasBirthdayError = false) }
    }

    fun changeCity(value: CountryUI) {
        setState { state.value.copy(city = value.title, hasCityError = false) }
    }

    fun changeVK(value: String) {
        setState { state.value.copy(vk = value, hasVkError = false) }
    }

    fun changeInstagram(value: String) {
        setState { state.value.copy(instagram = value, hasInstagramError = false) }
    }

    fun changePhoto(value: String) {
        setState { state.value.copy(ava = value) }
    }

    fun editProfile() {
        if (state.value.isValid) {
            setState {
                state.value.copy(
                    hasNameError = false,
                    hasUsernameError = false,
                    hasCityError = false,
                    hasInstagramError = false,
                    hasVkError = false
                )
            }
            launchOperation(operation = { scope ->
                editProfileUseCase(
                    scope,
                    EditProfileUseCase.Params(
                        state.value.name,
                        state.value.username,
                        state.value.birthday?.toDateIntUI() ?: "",
                        state.value.city,
                        state.value.vk,
                        state.value.instagram
                    )
                )
            }, success = {
                println()
            })
        } else {
            val hasNameError = state.value.name.isBlank()
            val hasUsernameError = !state.value.username.isEmail()
            val hsaBirthdayError = state.value.birthday == null
            val hasCityError = state.value.city.isBlank()
            val hasVkError = state.value.vk.isBlank()
            val hasInstagramError = state.value.instagram.isBlank()
            setState {
                state.value.copy(
                    hasNameError = hasNameError,
                    hasUsernameError = hasUsernameError,
                    hasBirthdayError = hsaBirthdayError,
                    hasCityError = hasCityError,
                    hasVkError = hasVkError,
                    hasInstagramError = hasInstagramError,
                )
            }
        }

    }

}