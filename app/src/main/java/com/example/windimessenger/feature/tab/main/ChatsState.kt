package com.example.windimessenger.feature.tab.main

import com.example.domain.ChatsUI


class ChatsState(
    val chats: List<ChatsUI>
) {

    companion object {
        val Default = ChatsState(chats = emptyList())

        val mockChats = (0..10).map {
            ChatsUI(
                "",
                "title",
                0,
                "10:00",
                ""
            )
        }
    }
}