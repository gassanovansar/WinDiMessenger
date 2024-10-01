package com.example.uikit.designe.appTextFiled

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uikit.theme.AppTheme

@Composable
fun AppPhoneTextFiled(
    modifier: Modifier = Modifier,
    hint: String = "",
    backgroundColor: Color = AppTheme.colors.gray2,
    textColor: Color = AppTheme.colors.white,
    value: String,
    error: Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    onClick: () -> Unit = {},
    phoneCodeOnClick: () -> Unit = {},
    onValueChange: (String) -> Unit,
) {
    val source = remember {
        MutableInteractionSource()
    }
    var _value by remember { mutableStateOf(value) }
    LaunchedEffect(value) {
        _value = value
    }

    var isFocused by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        Card(
            shape = shape,
            elevation = 0.dp,
            modifier = Modifier
                .defaultMinSize(minHeight = 40.dp)
                .clickable {
                    onClick()
                },
            border = if (error) BorderStroke(1.dp, AppTheme.colors.red) else null,
            backgroundColor = backgroundColor,
        ) {
            Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
                val keyboardController = LocalSoftwareKeyboardController.current
                Row(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickable {
                                phoneCodeOnClick()
                            }
                            .padding(4.dp),
                        text = "+7",
                        style = AppTheme.typography.regular.copy(
                            fontSize = 14.sp,
                            lineHeight = 16.8.sp,
                            color = AppTheme.colors.gray4
                        )
                    )
                    Divider(
                        Modifier
                            .padding(horizontal = 8.dp)
                            .width(1.dp)
                            .height(24.dp), color = AppTheme.colors.gray4
                    )

                }
                BasicTextField(
                    interactionSource = source,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }),
                    value = _value,
                    onValueChange = {

                        if (it.length <= 10) {
                            onValueChange(it)
                            _value = it
                        }

                    },
                    singleLine = true,
                    modifier = Modifier
                        .onFocusChanged {
                            isFocused = it.isFocused
                        }
                        .align(Alignment.CenterVertically)
                        .wrapContentSize(Alignment.Center)
                        .fillMaxWidth()
                        .padding(start = 4.dp)
                        .weight(1f),
                    textStyle = AppTheme.typography.regular.copy(
                        fontSize = 14.sp,
                        lineHeight = 16.8.sp,
                        color = if (error) AppTheme.colors.red else textColor
                    ),
                    decorationBox = { innerTextField ->
                        if (_value.isEmpty() && hint.isNotEmpty()) {
                            Text(
                                text = hint,
                                style = AppTheme.typography.regular.copy(
                                    fontSize = 14.sp,
                                    lineHeight = 16.8.sp,
                                    color = AppTheme.colors.gray4,
                                ),
                                maxLines = 1
                            )
                        }
                        innerTextField()
                    },
                    cursorBrush = SolidColor(AppTheme.colors.gray4),
                )

            }
        }
        AnimatedVisibility(visible = error) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Вы ввели неверный номер",
                style = AppTheme.typography.regular.copy(
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = AppTheme.colors.red,
                )
            )
        }
    }
}