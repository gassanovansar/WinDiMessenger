package com.example.windimessenger.data.repository

import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.domain.CountryUI
import com.example.windimessenger.domain.repository.CountryRepository

class CountryRepositoryImpl() : CountryRepository {
    override suspend fun loadCountry(): Either<Failure, List<CountryUI>> {
        return Either.Right(
            listOf(
                CountryUI(title = "Kazakhstan", code = "+7", validation = 10),
                CountryUI(title = "Russia", code = "+7", validation = 10),
                CountryUI(title = "China", code = "+86", validation = 9),
                CountryUI(title = "Colombia", code = "+57", validation = 9),
                CountryUI(title = "Germany", code = "+49", validation = 9),
                CountryUI(title = "Iraq", code = "+964", validation = 8),
                CountryUI(title = "Kirg(h)izia", code = "+996", validation = 8),
            )
        )
    }
}