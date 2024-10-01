package com.example.source

import com.example.data.response.CountryResponse

class LocalSource() {


    fun loadCountry(): List<CountryResponse> {
        return listOf(
            CountryResponse(title = "Kazakhstan", code = "+7", validation = 10),
            CountryResponse(title = "Russia", code = "+7", validation = 10),
            CountryResponse(title = "China", code = "+86", validation = 9),
            CountryResponse(title = "Colombia", code = "+57", validation = 9),
            CountryResponse(title = "Germany", code = "+49", validation = 9),
            CountryResponse(title = "Iraq", code = "+964", validation = 8),
            CountryResponse(title = "Kirg(h)izia", code = "+996", validation = 8),
        )
    }

}