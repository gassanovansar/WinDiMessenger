package com.example.windimessenger.feature.country

import com.example.domain.CountryUI

data class CountryState(val country: List<CountryUI>) {
    companion object {
        val Default = CountryState(country = emptyList())
    }
}