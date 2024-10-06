package com.example.windimessenger.feature.tab.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen

class ProfileScreen : Screen {
    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel { ProfileScreenModel() }
        val state = screenModel.state.collectAsState()

        LaunchedEffect(screenModel) {
            screenModel.loadProfile()
        }

    }
}