package com.example.windimessenger.feature.tab.profile

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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.RootNavigator
import com.example.uikit.designe.appCard.AppCard
import com.example.uikit.designe.appTextFiled.AppTitleTextField
import com.example.uikit.designe.button.PrimaryButton
import com.example.uikit.screens.PageContainer
import com.example.uikit.theme.AppTheme
import com.example.windimessenger.App
import com.example.windimessenger.feature.editProfile.EditProfileScreen

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = RootNavigator.currentOrThrow
        val screenModel = rememberScreenModel { ProfileScreenModel() }
        val state by screenModel.state.collectAsState()

        LaunchedEffect(screenModel) {
            screenModel.loadProfile()
        }

        PageContainer(background = AppTheme.colors.shadows, content = {

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
                ProfileItem("Имя", state.profile.name)
                ProfileItem("Телефон", state.profile.phone)
                ProfileItem("E-mail", state.profile.username)
                ProfileItem("Дата рождения", state.profile.birthday)
                ProfileItem("Город", state.profile.city)
                ProfileItem("ВКонтакте", state.profile.vk)
                ProfileItem("Инстаграм", state.profile.instagram)

            }

        }, footer = {
            Column {
                PrimaryButton(
                    text = "Редактировать профиль",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    navigator.push(EditProfileScreen())
                }
            }
        })

    }
}

@Composable
fun ProfileItem(title: String, value: String, hint: String = "Не известно") {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = title,
            style = AppTheme.typography.regular.copy(
                fontSize = 16.sp,
                lineHeight = 16.sp,
                color = AppTheme.colors.white,
            )
        )
        AppCard(
            backgroundColor = AppTheme.colors.gray2,
            modifier = Modifier
                .defaultMinSize(minHeight = 48.dp)
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 8.dp),
                text = value.ifBlank { hint },
                style = AppTheme.typography.regular.copy(
                    fontSize = 14.sp,
                    lineHeight = 16.8.sp,
                    color = if (value.isNotBlank()) AppTheme.colors.white else AppTheme.colors.gray4
                )
            )
        }
    }
}