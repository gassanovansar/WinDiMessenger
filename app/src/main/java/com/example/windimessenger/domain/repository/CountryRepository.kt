package com.example.windimessenger.domain.repository

import com.example.corekt.Either
import com.example.corekt.Failure
import com.example.domain.CountryUI

interface CountryRepository {

    suspend fun loadCountry(): Either<Failure, List<CountryUI>>
}