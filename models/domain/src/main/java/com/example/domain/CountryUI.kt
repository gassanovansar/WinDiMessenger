package com.example.domain

data class CountryUI(
    val title: String,
    val code: String,
    val validation: Int
) {

    companion object {
        val Default = CountryUI(title = "", code = "", validation = 10)
    }
}