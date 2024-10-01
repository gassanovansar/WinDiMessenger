package com.example.windimessenger.feature.country

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.example.domain.CountryUI
import com.example.uikit.designe.appCard.AppCard
import com.example.uikit.screens.PageContainer
import com.example.uikit.theme.AppTheme

class CountryScreen(private val onClick: (CountryUI) -> Unit) : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { CountryScreenModel() }
        val state by screenModel.state.collectAsState()
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        LaunchedEffect(screenModel) {
            screenModel.loadCountry()
        }
        PageContainer(fill = false, content = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.country) {
                    CountryItem(it) {
                        onClick(it)
                        bottomSheetNavigator.hide()
                    }
                }
            }
        })
    }

    @Composable
    private fun CountryItem(item: CountryUI, onClick: () -> Unit) {
        AppCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },
            backgroundColor = AppTheme.colors.gray2,
            border = BorderStroke(width = 1.dp, AppTheme.colors.white)
        ) {
            Text(
                text = item.title, modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                style = AppTheme.typography.regular.copy(
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = AppTheme.colors.white,
                )
            )
        }
    }
}