package com.example.windimessenger.feature.sso.sendSms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.uikit.designe.button.PrimaryButton
import com.example.uikit.designe.button.Size
import com.example.uikit.designe.otpTextFiled.OtpTextField
import com.example.uikit.designe.toolBar.BackIcon
import com.example.uikit.designe.toolBar.Toolbar
import com.example.uikit.screens.PageContainer
import com.example.uikit.theme.AppTheme
import com.example.windimessenger.feature.sso.registration.RegistrationScreen
import com.example.windimessenger.feature.tab.TabScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SendSmsScreen(private val phone: String) : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { SendSmsScreenModel() }
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(screenModel) {
            launch {
                screenModel.event.collectLatest {
                    when (it) {
                        SendSmsEvent.Auth -> {
                            navigator.replaceAll(TabScreen())
                        }

                        SendSmsEvent.Registration -> {
                            navigator.replace(RegistrationScreen(phone))
                        }
                    }
                }
            }

        }
        PageContainer(
            isLoading = screenModel.status.collectAsState(),
            error = screenModel.error.collectAsState(initial = null),
            header = {
                Toolbar(leftIcon = { BackIcon() })
            },
            content = {
                Column {
                    Spacer(modifier = Modifier.size(130.dp))
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        text = "Отправили код на $phone",
                        style = AppTheme.typography.semiBold.copy(
                            fontSize = 20.sp,
                            lineHeight = 24.sp,
                            color = AppTheme.colors.white,
                        )
                    )

                    OtpTextField(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        otpText = state.otp
                    ) { value, _ ->
                        screenModel.changeOtp(value)
                    }

                    PrimaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .padding(horizontal = 16.dp),
                        size = Size.XXL,
                        enabled = state.isValid,
                        text = "Продолжить",
                    ) {
                        screenModel.check(phone)
                    }
                }
            })
    }
}