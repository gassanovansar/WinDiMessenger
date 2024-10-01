package com.example.windimessenger.feature.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.windimessenger.feature.tab.TabScreen
import com.example.uikit.screens.PageContainer
import com.example.uikit.theme.AppTheme
import com.example.windimessenger.R
import com.example.windimessenger.feature.sso.auth.AuthScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SplashScreen : Screen {


    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { SplashScreenModel() }
        LaunchedEffect(screenModel) {
            launch {
                screenModel.event.collectLatest {
                    delay(1000)
                    when (it) {
                        SplashEvent.Auth -> navigator.replaceAll(AuthScreen())
                        SplashEvent.Main -> navigator.replaceAll(TabScreen())
                    }
                }


            }

        }

        PageContainer(
            content = {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(id = R.string.app_name),
                        style = AppTheme.typography.semiBold.copy(
                            fontSize = 40.sp,
                            lineHeight = 24.sp,
                            color = AppTheme.colors.white,
                        )
                    )
                }

            })
    }
}