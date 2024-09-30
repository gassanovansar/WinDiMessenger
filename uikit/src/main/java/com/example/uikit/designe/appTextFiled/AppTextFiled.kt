package com.example.uikit.designe.appTextFiled

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.ext.clickableRound
import com.example.uikit.theme.AppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    hint: String = "",
    backgroundColor: Color = AppTheme.colors.gray2,
    textColor: Color = AppTheme.colors.white,
    fill: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLength: Int? = null,
    value: String,
    enabled: Boolean = true,
    error: Boolean = false,
    password: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    left: @Composable (() -> Unit)? = null,
    right: @Composable (() -> Unit)? = null,
    minLines: Int = 1,
    shape: Shape = RoundedCornerShape(8.dp),
    onClick: () -> Unit = {},
    onValueChange: (String) -> Unit,
) {
    val source = remember {
        MutableInteractionSource()
    }
    var passwordState by remember { mutableStateOf(password) }
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
                .clickableRound(8.dp) {
                    onClick()
                },
            border = if (error) BorderStroke(1.dp, AppTheme.colors.red) else null,
            backgroundColor = backgroundColor,
        ) {
            Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
                val keyboardController = LocalSoftwareKeyboardController.current
                left?.invoke()
                BasicTextField(
                    interactionSource = source,
                    enabled = enabled,
                    visualTransformation = if (passwordState) {
                        PasswordVisualTransformation()
                    } else visualTransformation,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }),
                    value = _value,
                    onValueChange = {
                        if (maxLength != null) {
                            if (it.length <= maxLength) {
                                onValueChange(it)
                                _value = it
                            }
                        } else {
                            onValueChange(it)
                            _value = it
                        }

                    },
                    minLines = minLines,
                    singleLine = minLines == 1,
                    modifier = Modifier
                        .onFocusChanged {
                            isFocused = it.isFocused
                        }
                        .align(Alignment.CenterVertically)
                        .wrapContentSize(Alignment.Center)
                        .fillMaxWidth()
                        .padding(start = 4.dp)
                        .weight(1f, fill),
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
                if (password) {
//                Image(
//                    painter = if (passwordState) AppResource.image.passwordstateno.painterResource() else AppResource.image.passwordstateyes.painterResource(),
//                    contentDescription = null,
//                    modifier = Modifier.clickableRound(8.dp) {
//                        passwordState = !passwordState
//                    }
//                )

                } else right?.invoke()

            }
        }
        AnimatedVisibility(visible = error) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Вы ввели неверный e-mail",
                style = AppTheme.typography.regular.copy(
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = AppTheme.colors.red,
                )
            )
        }
    }
}