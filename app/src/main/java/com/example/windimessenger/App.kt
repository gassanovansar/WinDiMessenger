package com.example.windimessenger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import com.example.core.RootNavigator
import com.example.domain.Notification
import com.example.managers.NotificationManager
import com.example.uikit.components.NotificationCenter
import com.example.uikit.theme.AppTheme
import com.example.windimessenger.feature.splash.SplashScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.mp.KoinPlatform

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun App() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            BottomSheetNavigator(
                sheetShape = RoundedCornerShape(
                    topStartPercent = 8,
                    topEndPercent = 8
                )
            ) {
                Navigator(SplashScreen()) {
                    CompositionLocalProvider(
                        RootNavigator provides it,
                    ) {
                        CurrentScreen()
                    }
                }
            }
            NotificationContainer()
        }
    }
}

@Composable
private fun NotificationContainer() {
    val notificationManager by KoinPlatform.getKoin().inject<NotificationManager>()
    var notification by remember { mutableStateOf<Notification?>(null) }
    LaunchedEffect(notificationManager) {
        notificationManager.notification.consumeAsFlow().collectLatest {
            notification = it
        }
    }
    NotificationCenter(notification)
}