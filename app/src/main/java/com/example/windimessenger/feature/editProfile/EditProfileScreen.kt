package com.example.windimessenger.feature.editProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import com.example.uikit.designe.appCard.AppCard
import com.example.uikit.designe.appTextFiled.AppTitleTextField
import com.example.uikit.designe.button.PrimaryButton
import com.example.uikit.screens.PageContainer
import com.example.uikit.theme.AppTheme
import com.example.windimessenger.App
import com.example.windimessenger.feature.tab.profile.ProfileItem

class EditProfileScreen : Screen {
    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel { EditProfileScreenModel() }
        val state by screenModel.state.collectAsState()

        LaunchedEffect(screenModel) {
            screenModel.loadProfile()
        }

        PageContainer(background = AppTheme.colors.black, content = {

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
                    value = state.birthday,
                    hint = "Выберите дату рождения",
                )
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