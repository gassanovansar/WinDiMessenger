package com.example.windimessenger.feature.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.uikit.designe.appTextFiled.AppPhoneTextFiled
import com.example.uikit.designe.button.PrimaryButton
import com.example.uikit.designe.toolBar.Toolbar
import com.example.uikit.screens.PageContainer

class AuthScreen : Screen {
    @Composable
    override fun Content() {

        PageContainer(header = {
            Toolbar(title = "Авторизация")
        }, content = {

            Column {
                AppPhoneTextFiled(
                    value = "",
                    hint = "1214141",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 64.dp)
                ) {

                }
                PrimaryButton(
                    text = "Вход",
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 24.dp)
                ) {
                }
            }

        })

    }
}