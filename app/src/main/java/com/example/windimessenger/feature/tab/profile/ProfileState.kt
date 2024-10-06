package com.example.windimessenger.feature.tab.profile

import com.example.domain.ProfileUI

data class ProfileState(val profile: ProfileUI) {

    companion object {
        val Default = ProfileState(profile = ProfileUI.Default)
    }
}