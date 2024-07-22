package com.pavelshelkovenko.currencyconvertervk.data

import com.pavelshelkovenko.currencyconvertervk.domain.ConverterRepository
import com.pavelshelkovenko.currencyconvertervk.domain.Currency

class ConverterRepositoryImpl(
    private val apiService: ApiService
): ConverterRepository {

    override suspend fun convertCurrency(from: String, to: String, amount: Double): Double {
        val result = apiService.convertCurrency(
            from = from,
            to = to,
            amount = amount
        )
        return result.convertedValue
    }

    override suspend fun getCurrencies(): List<Currency> {
        val response = apiService.getCurrencies()
        val currenciesListDomain = response.currenciesList.map { currencyDto ->
            currencyDto.toCurrencyDomain()
        }
        return currenciesListDomain
    }

}