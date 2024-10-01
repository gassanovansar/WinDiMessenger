package com.example.windimessenger.feature.country

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.uikit.designe.appCard.AppCard
import com.example.uikit.screens.PageContainer
import com.example.uikit.theme.AppTheme

class CountryScreen : Screen {
    @Composable
    override fun Content() {
        PageContainer(fill = false, content = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(10) {
                    CountryItem()
                }
            }
        })
    }

    @Composable
    private fun CountryItem() {
        AppCard(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = AppTheme.colors.gray2,
            border = BorderStroke(width = 1.dp, AppTheme.colors.white)
        ) {
            Text(
                text = "Казахстан", modifier = Modifier
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