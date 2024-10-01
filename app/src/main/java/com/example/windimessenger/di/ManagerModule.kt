package com.example.windimessenger.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.core.module.dsl.bind
import com.example.managers.NotificationManager
import com.example.managers.NotificationManagerImpl
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
    singleOf(::NotificationManagerImpl) { bind<NotificationManager>() }
}