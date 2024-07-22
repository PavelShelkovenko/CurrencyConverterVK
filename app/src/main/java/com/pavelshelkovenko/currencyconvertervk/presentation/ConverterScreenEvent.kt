package com.pavelshelkovenko.currencyconvertervk.presentation

import com.pavelshelkovenko.currencyconvertervk.domain.Currency

sealed interface ConverterScreenEvent {

    data object LoadCurrencyList: ConverterScreenEvent

    data class ConvertCurrencies(
        val fromShortCode: String,
        val toShortCode: String,
        val amount: Double
    ): ConverterScreenEvent

    data object SwapCurrencies: ConverterScreenEvent

    data class ChangeAmount(val newAmount: Double): ConverterScreenEvent

    data class ChangeCurrency(
        val newCurrency: Currency,
        val converterDestination: ConverterDestination
    ): ConverterScreenEvent
}