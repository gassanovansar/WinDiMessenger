package com.example.uikit.designe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.example.core.ext.clickableRound
import com.example.uikit.theme.AppTheme
import kotlinx.datetime.Clock

class DatePickerScreen(
    private val current: Long? = null,
    private val onDateSelected: (Long) -> Unit
) :
    Screen {


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        val _current = current ?: Clock.System.now().toEpochMilliseconds()
        val datePickerState =
            rememberDatePickerState(
                initialSelectedDateMillis = _current,
            )
        Column(Modifier.background(AppTheme.colors.shadows)) {

            DatePicker(
                state = datePickerState,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(bottom = 32.dp),
                title = {
                    Text(
                        text = "Сохранить",
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .clickableRound(8.dp) {
                                onDateSelected(
                                    datePickerState.selectedDateMillis ?: Clock.System
                                        .now()
                                        .toEpochMilliseconds()
                                )
                                bottomSheetNavigator.hide()
                            }
                            .padding(horizontal = 8.dp),
                        style = AppTheme.typography.regular.copy(
                            fontSize = 16.sp,
                            color = AppTheme.colors.white,
                            textAlign = TextAlign.Center,
                        )
                    )
                },
                showModeToggle = false,
                colors = DatePickerDefaults.colors(
                    containerColor = Color.White,
                    titleContentColor = AppTheme.colors.white,
                    headlineContentColor = AppTheme.colors.white,
                    weekdayContentColor = AppTheme.colors.white,
                    subheadContentColor = AppTheme.colors.white,
                    navigationContentColor = AppTheme.colors.white,
                    yearContentColor = AppTheme.colors.black,
                    dayContentColor = AppTheme.colors.white,
                    selectedDayContainerColor = AppTheme.colors.black,
                    selectedDayContentColor = AppTheme.colors.white,
                    todayDateBorderColor = AppTheme.colors.white,
                    todayContentColor = AppTheme.colors.white,
                    selectedYearContentColor = AppTheme.colors.white,
                    selectedYearContainerColor = AppTheme.colors.black,
                )
            )
        }

    }
}