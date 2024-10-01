package com.example.managers

import com.example.domain.Notification
import kotlinx.coroutines.channels.Channel


interface NotificationManager {

    val notification: Channel<Notification>
}


class NotificationManagerImpl : NotificationManager {

    override val notification: Channel<Notification> = Channel(Channel.BUFFERED)

}