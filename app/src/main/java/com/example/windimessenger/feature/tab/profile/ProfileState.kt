package com.example.windimessenger.feature.tab.profile

import com.example.domain.ProfileUI

class ProfileState(profile: ProfileUI) {

    companion object {
        val Default = ProfileState(profile = ProfileUI.Default)
    }
}