package com.example.windimessenger.feature.chooseMedia

import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.example.uikit.screens.PageContainer
import com.example.uikit.theme.AppTheme
import com.example.windimessenger.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


internal val photoSelectEvent = Channel<PhotoSelectEvent> {}

internal class PhotoSelectEvent(
    val key: String,
    val value: String
)

class ChooseMediaScreen(private val resultKey: String) : Screen, KoinComponent {


    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    override fun Content() {

        val navigator = LocalBottomSheetNavigator.current
        val scope = rememberCoroutineScope()
        var pickerShow by remember { mutableStateOf(false) }
        ImagePicker(pickerShow, onFileSelected = { image ->
            pickerShow = false
            photoSelectEvent.trySend(PhotoSelectEvent(resultKey, image))
        }, cancel = {
            navigator.hide()
        })
        val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
        val cameraPermissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                navigator.push(CameraScreen {
                    photoSelectEvent.trySend(PhotoSelectEvent(resultKey, it))
                })
            } else {
                navigator.hide()
            }
        }

        val galereyPermissionState =
            rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)
        val galereyPermissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                pickerShow = true
            } else {
                navigator.hide()
            }
        }



        PageContainer(
            fill = false,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .safeContentPadding()
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(end = 20.dp)
                            .clickable {
                                navigator.hide()
                            },
                        text = "Назад",
                        style = AppTheme.typography.medium.copy(
                            fontSize = 16.sp,
                            lineHeight = 22.4.sp,
                            color = AppTheme.colors.black,
                        )
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 24.dp),
                        text = "Выберите фото",
                        style = AppTheme.typography.semiBold.copy(
                            fontSize = 18.sp,
                            color = AppTheme.colors.black,
                        )
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 24.dp, bottom = 24.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        ImageTitle(
                            modifier = Modifier
                                .padding(end = 24.dp)
                                .clickable {
                                    cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                                },
                            image = painterResource(R.drawable.ic_camera),
                            title = "Камера"
                        )
                        ImageTitle(
                            modifier = Modifier
                                .padding(start = 24.dp)
                                .clickable {
                                    galereyPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                                },
                            image = painterResource(R.drawable.ic_album),
                            title = "Галерея"
                        )
                    }
                }
            })
    }

    @Composable
    private fun ImageTitle(modifier: Modifier = Modifier, image: Painter, title: String) {

        Column(modifier) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp),
                text = title,
                style = AppTheme.typography.medium.copy(
                    fontSize = 14.sp,
                    color = AppTheme.colors.black
                )
            )
        }

    }
}

@Composable
fun ImagePicker(
    show: Boolean,
    onFileSelected: (String) -> Unit,
    cancel: () -> Unit
) {


    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        onFileSelected(uri?.toString() ?: return@rememberLauncherForActivityResult)
    }

    LaunchedEffect(show) {
        if (show) {
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }
}