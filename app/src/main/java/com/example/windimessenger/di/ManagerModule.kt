package com.example.windimessenger.di

import android.content.Context
import android.content.SharedPreferences
import com.example.managers.SessionManager
import com.example.managers.SessionManagerImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val managerModule = module {
    single {
        val context = get<Context>()
        context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
    }
    single<SessionManager> { SessionManagerImpl(get()) }
}