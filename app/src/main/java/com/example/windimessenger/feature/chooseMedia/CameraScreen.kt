package com.example.windimessenger.feature.chooseMedia

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.uikit.designe.toolBar.BackIcon

class CameraScreen(private val selectedPhoto: (String) -> Unit) : Screen {


    @Composable
    override fun Content() {
        Box(modifier = Modifier.safeContentPadding()) {

            Camera(
                modifier = Modifier,
                photo = selectedPhoto
            )
            BackIcon(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp)
                    .windowInsetsPadding(WindowInsets.safeDrawing)
            )
        }

    }
}

