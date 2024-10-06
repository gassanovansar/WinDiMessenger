package com.example.windimessenger.feature.editProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.example.core.ext.toDateIntUI
import com.example.uikit.designe.AppImage
import com.example.uikit.designe.DatePickerScreen
import com.example.uikit.designe.appCard.AppCard
import com.example.uikit.designe.appTextFiled.AppTitleTextField
import com.example.uikit.designe.button.PrimaryButton
import com.example.uikit.designe.toolBar.BackIcon
import com.example.uikit.designe.toolBar.Toolbar
import com.example.uikit.screens.PageContainer
import com.example.uikit.theme.AppTheme
import com.example.windimessenger.R
import com.example.windimessenger.feature.chooseMedia.ChooseMediaScreen
import com.example.windimessenger.feature.chooseMedia.photoSelectEvent
import com.example.windimessenger.feature.country.CountryScreen
import com.example.windimessenger.feature.tab.profile.ProfileItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class EditProfileScreen(private val success: () -> Unit) : Screen {

    companion object {
        private const val PHOTO = "PHOTO"
    }

    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel { EditProfileScreenModel() }
        val state by screenModel.state.collectAsState()

        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        LaunchedEffect(screenModel) {
            screenModel.loadProfile()
            launch {
                screenModel.event.collectLatest {
                    success()
                }
            }



            launch {
                photoSelectEvent.receiveAsFlow().collect {
                    bottomSheetNavigator.hide()
                    when (it.key) {
                        PHOTO -> {
                            if (it.value.isNotBlank()) {
                                screenModel.changePhoto(it.value)
                            }
                        }
                    }
                }
            }
        }

        PageContainer(
            isLoading = screenModel.status.collectAsState(),
            error = screenModel.error.collectAsState(null),
            background = AppTheme.colors.black, header = {
                Toolbar(leftIcon = { BackIcon() }, title = "Редакьтровать профиль")
            }, content = {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {

                    Box(
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 32.dp)
                    ) {
                        AppImage(
                            url = state.ava.ifBlank { state.ava.ifBlank { "https://media.istockphoto.com/id/1222357475/vector/image-preview-icon-picture-placeholder-for-website-or-ui-ux-design-vector-illustration.jpg?s=612x612&w=0&k=20&c=KuCo-dRBYV7nz2gbk4J9w1WtTAgpTdznHu55W9FjimE=" } },
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(100.dp))
                                .clickable {
                                    bottomSheetNavigator.show(ChooseMediaScreen(PHOTO))
                                },
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                        )
                        Image(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .clickable {
                                    bottomSheetNavigator.show(ChooseMediaScreen(PHOTO))
                                },
                            painter = painterResource(id = R.drawable.ic_pen),
                            contentDescription = null
                        )
                    }


                    AppTitleTextField(
                        "Имя",
                        value = state.name,
                        hint = "Введите имя",
                        error = state.hasNameError,
                        errorText = "Введите имя",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        screenModel.changeName(it)
                    }
                    AppTitleTextField(
                        "E-mail",
                        value = state.username,
                        hint = "Введите E-mail",
                        error = state.hasUsernameError,
                        errorText = "Вы ввели неверный e-mail",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        screenModel.changeEmail(it)
                    }
                    ProfileItem(
                        "Дата рождения",
                        value = state.birthday?.toDateIntUI() ?: "",
                        error = state.hasBirthdayError,
                        errorText = "Выберите дату рождения",
                        hint = "Выберите дату рождения",
                    ) {
                        bottomSheetNavigator.show(DatePickerScreen(null) {
                            screenModel.changeBirthday(it)
                        })

                    }
                    ProfileItem(
                        "Город",
                        value = state.city,
                        error = state.hasCityError,
                        errorText = "Выберите город",
                        hint = "Выберите город"
                    ) {
                        bottomSheetNavigator.show(CountryScreen() {
                            screenModel.changeCity(it)
                        })
                    }
                    AppTitleTextField(
                        "ВКонтакте",
                        value = state.vk,
                        hint = "Введите аккаунт",
                        error = state.hasVkError,
                        errorText = "Необходимо заполнить поле",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        screenModel.changeVK(it)
                    }
                    AppTitleTextField(
                        "Инстаграм",
                        value = state.instagram,
                        hint = "Введите аккаунт",
                        error = state.hasInstagramError,
                        errorText = "Необходимо заполнить поле",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        screenModel.changeInstagram(it)
                    }

                }

            }, footer = {
                Column {
                    PrimaryButton(
                        text = "Сохранить",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        screenModel.editProfile()
                    }
                }
            })

    }

}