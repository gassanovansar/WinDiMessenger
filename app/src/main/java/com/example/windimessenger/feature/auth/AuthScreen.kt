package com.example.windimessenger.feature.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.example.uikit.designe.appTextFiled.AppPhoneTextFiled
import com.example.uikit.designe.button.PrimaryButton
import com.example.uikit.designe.toolBar.Toolbar
import com.example.uikit.screens.PageContainer
import com.example.windimessenger.feature.country.CountryScreen

class AuthScreen : Screen {
    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        val screenModel = rememberScreenModel { AuthScreenModel() }
        val state by screenModel.state.collectAsState()
        LaunchedEffect(screenModel) {
            screenModel.loadCountry()
        }
        PageContainer(header = {
            Toolbar(title = "Авторизация")
        }, content = {

            Column {
                AppPhoneTextFiled(
                    value = state.phone,
                    hint = "XXX XXX XX XX",
                    phoneCode = state.country.code,
                    phoneLength = state.country.validation,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 64.dp),
                    phoneCodeOnClick = {
                        bottomSheetNavigator.show(CountryScreen() {
                            screenModel.changeCounty(it)
                        })
                    }
                ) {
                    screenModel.changePhone(it)
                }
                PrimaryButton(
                    text = "Вход",
                    enabled = state.isValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 24.dp)
                ) {
                }
            }

        })

    }
}