package com.example.windimessenger.di

import com.example.managers.SessionManager
import com.example.managers.SessionManagerImpl
import org.koin.dsl.module

val managerModule = module {
    single<SessionManager> { SessionManagerImpl() }
}