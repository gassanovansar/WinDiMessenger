package com.example.mappers

import com.example.data.response.CountryResponse
import com.example.domain.CountryUI

fun CountryResponse.toUI(): CountryUI {
    return CountryUI(
        title = this.title.orEmpty(),
        code = this.code.orEmpty(),
        validation = this.validation ?: 10
    )
}