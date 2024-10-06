package com.example.windimessenger.feature.editProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.example.core.ext.toDateIntUI
import com.example.uikit.designe.DatePickerScreen
import com.example.uikit.designe.appCard.AppCard
import com.example.uikit.designe.appTextFiled.AppTitleTextField
import com.example.uikit.designe.button.PrimaryButton
import com.example.uikit.designe.toolBar.BackIcon
import com.example.uikit.designe.toolBar.Toolbar
import com.example.uikit.screens.PageContainer
import com.example.uikit.theme.AppTheme
import com.example.windimessenger.feature.tab.profile.ProfileItem

class EditProfileScreen : Screen {
    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel { EditProfileScreenModel() }
        val state by screenModel.state.collectAsState()

        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        LaunchedEffect(screenModel) {
            screenModel.loadProfile()
        }

        PageContainer(background = AppTheme.colors.black, header = {
            Toolbar(leftIcon = { BackIcon() }, title = "Редакьтровать профиль")
        }, content = {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {

                AppCard(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .align(Alignment.CenterHorizontally)
                        .size(86.dp),
                    shape = CircleShape
                ) {
                }
                AppTitleTextField(
                    "Имя",
                    value = state.name,
                    hint = "Введите имя",
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {}
                AppTitleTextField(
                    "E-mail",
                    value = state.username,
                    hint = "Введите E-mail",
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {}
                ProfileItem(
                    "Дата рождения",
                    value = state.birthday?.toDateIntUI() ?: "",
                    hint = "Выберите дату рождения",
                ) {
                    bottomSheetNavigator.show(DatePickerScreen(null) {
                        screenModel.changeBirthday(it)
                    })

                }
                ProfileItem(
                    "Город",
                    value = state.city,
                    hint = "Выберите город"
                )
                AppTitleTextField(
                    "ВКонтакте",
                    value = state.vk,
                    hint = "Ввелите аккаунт",
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {}
                AppTitleTextField(
                    "Инстаграм",
                    value = state.instagram,
                    hint = "Ввелите аккаунт",
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {}

            }

        }, footer = {
            Column {
                PrimaryButton(
                    text = "Редактировать профиль",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                }
            }
        })

    }

}