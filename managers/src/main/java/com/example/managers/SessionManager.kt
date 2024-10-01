package com.example.managers

import android.content.SharedPreferences
import androidx.core.content.edit

interface SessionManager {

    val isAuth: Boolean

    fun auth(token: String, refresh: String)
}


class SessionManagerImpl(private val sharedPreference: SharedPreferences) : SessionManager {
    override val isAuth: Boolean
        get() = !sharedPreference.getString(TOKEN, "").isNullOrBlank()

    override fun auth(token: String, refresh: String) {
        sharedPreference.edit {
            putString(TOKEN, token).commit()
            putString(REFRESH, refresh).commit()
        }
    }

    companion object {
        private const val BASE = "SessionManager"
        private const val TOKEN = BASE + "TOKEN"
        private const val REFRESH = BASE + "REFRESH"
    }

}