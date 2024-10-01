package com.example.windimessenger.feature.splash

import com.example.core.base.BaseScreenModel
import com.example.managers.SessionManager
import org.koin.core.component.inject

class SplashScreenModel : BaseScreenModel<Any, SplashEvent>(Any()) {

    private val sessionManager: SessionManager by inject()

    init {
        navigate()
    }

    private fun navigate() {
        if (sessionManager.isAuth) {
            setEvent(SplashEvent.Main)
        } else {
            setEvent(SplashEvent.Auth)
        }
    }
}