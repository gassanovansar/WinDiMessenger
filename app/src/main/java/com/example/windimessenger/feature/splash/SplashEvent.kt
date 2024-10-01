package com.example.windimessenger.feature.splash
sealed interface SplashEvent {

    object Main : SplashEvent
    object Auth : SplashEvent
}