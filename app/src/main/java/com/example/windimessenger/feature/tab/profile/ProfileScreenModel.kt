package com.example.windimessenger.feature.tab.profile

import com.example.core.base.BaseScreenModel
import com.example.windimessenger.domain.useCase.ProfileUseCase
import org.koin.core.component.inject

class ProfileScreenModel : BaseScreenModel<ProfileState, Any>(ProfileState.Default) {

    private val profileUseCase: ProfileUseCase by inject()

    fun loadProfile() {
        launchOperation(operation = { scope ->
            profileUseCase(scope, ProfileUseCase.Params())
        }, success = {
            println(it)
        })
    }
}