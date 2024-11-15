package com.example.windimessenger.domain.repository

import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.domain.ChatsUI

interface ChatRepository {

    suspend fun chats(): Either<Failure, List<ChatsUI>>
}