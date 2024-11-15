package com.example.windimessenger.feature.tab.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.uikit.designe.appCard.AppCard
import com.example.uikit.designe.toolBar.Toolbar
import com.example.uikit.screens.PageContainer
import com.example.uikit.theme.AppTheme

class ChatsScreen : Screen {
    @Composable
    override fun Content() {
        PageContainer(
            header = {
                Toolbar(title = "Чаты")
            },
            content = {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(18.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(10) {
                        ChatItem()
                    }
                }
            }
        )
    }

    @Composable
    fun ChatItem() {
        Row {
            AppCard(
                shape = CircleShape,
                modifier = Modifier.size(64.dp)
            ) {
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "Mert Karatutun",
                    style = AppTheme.typography.semiBold.copy(
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        color = AppTheme.colors.white,
                    )
                )
                Spacer(modifier = Modifier.size(4.dp))
                Row {

                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Салам",
                        style = AppTheme.typography.regular.copy(
                            fontSize = 16.sp,
                            lineHeight = 16.sp,
                            color = AppTheme.colors.white,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "11:00",
                        style = AppTheme.typography.regular.copy(
                            fontSize = 16.sp,
                            lineHeight = 16.sp,
                            color = AppTheme.colors.white,
                        )
                    )

                }


            }
        }
    }

}


