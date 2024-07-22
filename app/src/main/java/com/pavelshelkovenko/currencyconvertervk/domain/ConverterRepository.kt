package com.pavelshelkovenko.currencyconvertervk.domain

interface ConverterRepository {

    suspend fun convertCurrency(
        from: String,
        to: String,
        amount: Double
    ): Double

    suspend fun getCurrencies(): List<Currency>
}