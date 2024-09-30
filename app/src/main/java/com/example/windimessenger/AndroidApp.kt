package com.example.windimessenger

import android.app.Application
import com.example.windimessenger.di.KoinInjector
import org.koin.android.ext.koin.androidContext

class AndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInjector.koinApp.androidContext(this)
    }
}

