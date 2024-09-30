package com.example.corekt

sealed class Failure(override val message: String) : Throwable() {
    class UseCase(e: Exception) : Failure(e.message.orEmpty())
}