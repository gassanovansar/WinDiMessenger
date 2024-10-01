package com.example.domain

sealed class CheckUI {
    object Auth : CheckUI()
    object Registration : CheckUI()

}