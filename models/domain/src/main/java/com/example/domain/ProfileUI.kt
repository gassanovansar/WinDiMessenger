package com.example.domain

class ProfileUI(
    val name: String,
    val username: String,
    val birthday: String,
    val city: String,
    val vk: String,
    val instagram: String,
    val status: String,
    val avatar: String,
    val id: String,
    val online: Boolean,
    val phone: String,
    val completedTask: Int,
) {
    companion object {
        val Default = ProfileUI(
            name = "",
            username = "",
            birthday = "",
            city = "",
            vk = "",
            instagram = "",
            status = "",
            avatar = "",
            id = "",
            online = false,
            phone = "",
            completedTask = 0
        )
    }
}