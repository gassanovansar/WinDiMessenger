package com.example.windimessenger.data.repository

import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.domain.ChatsUI
import com.example.windimessenger.domain.repository.ChatRepository

class ChatRepositoryImpl : ChatRepository {
    override suspend fun chats(): Either<Failure, List<ChatsUI>> {
        return Either.Right(emptyList())
    }
}