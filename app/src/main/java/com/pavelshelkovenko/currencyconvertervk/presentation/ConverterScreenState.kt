package com.pavelshelkovenko.currencyconvertervk.presentation

import com.pavelshelkovenko.currencyconvertervk.domain.Currency

data class ConverterScreenState(
    val from: Currency = Currency.getRUBCurrency(),
    val to: Currency = Currency.getUSDCurrency(),
    val fromAmount: Double = 0.0,
    val convertedAmount: Double = 0.0,
    val currencyList: List<Currency> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
