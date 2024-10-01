package com.example.domain

data class CountryUI(
    val title: String,
    val code: String
) {

    companion object {
        val Default = CountryUI(title = "", code = "")
    }
}