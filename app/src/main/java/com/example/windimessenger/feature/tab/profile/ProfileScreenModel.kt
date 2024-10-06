package com.example.windimessenger.feature.tab.profile

import com.example.core.base.BaseScreenModel
import com.example.managers.SessionManager
import com.example.windimessenger.domain.useCase.ProfileUseCase
import org.koin.core.component.inject

class ProfileScreenModel : BaseScreenModel<ProfileState, ProfileEvent>(ProfileState.Default) {

    private val profileUseCase: ProfileUseCase by inject()
    private val sessionManager: SessionManager by inject()

    fun loadProfile() {
        launchOperation(operation = { scope ->
            profileUseCase(scope, ProfileUseCase.Params())
        }, success = {
            setState { state.value.copy(profile = it) }
        })
    }

    fun exit() {
        sessionManager.exit()
        setEvent(ProfileEvent.Exit)
    }
}