package com.example.windimessenger.feature.tab

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.windimessenger.R
import com.example.windimessenger.feature.tab.main.MainScreen

object MainTabScreen : Tab {

    @Composable
    override fun Content() {
        Navigator(MainScreen())
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1.toUShort(),
            title = "Чаты",
            icon = painterResource(id = R.drawable.ic_message)
        )


}