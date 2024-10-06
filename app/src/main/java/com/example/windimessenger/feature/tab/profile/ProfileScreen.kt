package com.example.windimessenger.feature.tab.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.core.RootNavigator
import com.example.core.ext.clickableRound
import com.example.uikit.designe.appCard.AppCard
import com.example.uikit.designe.button.PrimaryButton
import com.example.uikit.screens.PageContainer
import com.example.uikit.theme.AppTheme
import com.example.windimessenger.feature.editProfile.EditProfileScreen
import com.example.windimessenger.feature.sso.auth.AuthScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = RootNavigator.currentOrThrow
        val screenModel = rememberScreenModel { ProfileScreenModel() }
        val state by screenModel.state.collectAsState()

        LaunchedEffect(screenModel) {
            screenModel.loadProfile()
            launch {
                screenModel.event.collectLatest {
                    when (it) {
                        ProfileEvent.Exit -> navigator.replaceAll(AuthScreen())
                    }
                }
            }
        }

        PageContainer(
            isLoading = screenModel.status.collectAsState(),
            error = screenModel.error.collectAsState(null),
            background = AppTheme.colors.shadows, content = {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {

                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(vertical = 8.dp, horizontal = 4.dp)
                        .clickable {
                            screenModel.exit()
                        },
                    text = "Выйти из аккаунта", style = AppTheme.typography.regular.copy(
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        color = AppTheme.colors.white,
                    )
                )

                AppCard(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .size(100.dp),
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
                    navigator.push(EditProfileScreen {
                        navigator.pop()
                        screenModel.loadProfile()
                    })
                }
            }
        })

    }
}

@Composable
fun ProfileItem(
    title: String,
    value: String,
    hint: String = "Не известно",
    error: Boolean = false,
    errorText: String = "",
    onClick: (() -> Unit)? = null
) {
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
            border = if (error) BorderStroke(1.dp, AppTheme.colors.red) else null,
            backgroundColor = AppTheme.colors.gray2,
            modifier = Modifier
                .defaultMinSize(minHeight = 48.dp)
                .fillMaxWidth()
                .clickableRound(8.dp, onClick != null) {
                    onClick?.invoke()
                }
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
        AnimatedVisibility(visible = error && !errorText.isNullOrBlank()) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = errorText!!,
                style = AppTheme.typography.regular.copy(
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = AppTheme.colors.red,
                )
            )
        }
    }
}