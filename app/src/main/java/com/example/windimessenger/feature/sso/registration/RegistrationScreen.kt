package com.example.windimessenger.feature.sso.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.uikit.designe.appTextFiled.AppTitleTextField
import com.example.uikit.designe.button.PrimaryButton
import com.example.uikit.designe.toolBar.Toolbar
import com.example.uikit.screens.PageContainer

class RegistrationScreen(private val phone: String) : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { RegistrationScreenModel() }
        val state by screenModel.state.collectAsState()
        PageContainer(header = {
            Toolbar(
                title = "Регистрация"
            )
        }, content = {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AppTitleTextField(
                    title = "Телефон",
                    value = phone,
                    enabled = false,
                ) {
                }
                AppTitleTextField(
                    title = "Имя",
                    value = state.name,
                    error = state.hasNameError,
                    errorText = "Введите имя",
                    hint = "Введите имя",
                ) {
                    screenModel.changeName(it)
                }
                AppTitleTextField(
                    title = "E-mail",
                    value = state.userName,
                    error = state.hasUserNameError,
                    errorText = "Вы ввели неверный e-mail",
                    hint = "Введите e-mail",
                ) {
                    screenModel.changeUserName(it)
                }
                PrimaryButton(
                    text = "Зарегестрироватся",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    screenModel.registration(phone)
                }
            }
        })
    }
}