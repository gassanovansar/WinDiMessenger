package com.example.windimessenger.feature.tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.core.ext.clickableRound
import com.example.uikit.designe.appCard.AppCard
import com.example.uikit.screens.PageContainer
import com.example.uikit.theme.AppTheme

class TabScreen(private val tab: Tab = ProfileTabScreen) : Screen {

    @Composable
    override fun Content() {
        TabNavigator(
            tab = tab,
            disposeNestedNavigators = true
        ) {
            PageContainer(
                content = {
                    it.current.Content()
                },
                footer = {
                    Divider(color = AppTheme.colors.gray3)
                    Row(
                        modifier = Modifier
                            .background(AppTheme.colors.shadows)
                            .padding(vertical = 8.dp)
                    ) {
                        TabNavItem(MainTabScreen)
                        TabNavItem(ProfileTabScreen)
                    }
                }
            )
        }
    }

    @Composable
    private fun RowScope.TabNavItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current
        val selected = tabNavigator.current == tab
        val color = if (selected) AppTheme.colors.blue else AppTheme.colors.gray4

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .clickableRound(8.dp) {
                    tabNavigator.current = tab
                }) {

            tab.options.icon?.let {
                Box {
                    Image(
                        painter = it,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color),
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .size(24.dp)
                    )
                }
            }
            Text(
                text = tab.options.title,
                modifier = Modifier,
                color = color,
                style = AppTheme.typography.medium.copy(fontSize = 11.sp)
            )

        }
    }
}

