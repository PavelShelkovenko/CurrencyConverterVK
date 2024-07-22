package com.pavelshelkovenko.currencyconvertervk.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavelshelkovenko.currencyconvertervk.domain.ConverterRepository
import com.pavelshelkovenko.currencyconvertervk.domain.Currency
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class ConverterViewModel(
    private val repository: ConverterRepository
) : ViewModel() {

    var screenState = MutableStateFlow(ConverterScreenState())
        private set

    fun onEvent(event: ConverterScreenEvent) {
        when (event) {
            is ConverterScreenEvent.ChangeAmount -> changeAmount(newAmount = event.newAmount)
            is ConverterScreenEvent.ConvertCurrencies -> convertCurrencies(
                fromShortCode = event.fromShortCode,
                toShortCode = event.toShortCode,
                amount = event.amount
            )

            ConverterScreenEvent.LoadCurrencyList -> loadCurrencyList()
            ConverterScreenEvent.SwapCurrencies -> swapCurrencies()
            is ConverterScreenEvent.ChangeCurrency -> changeCurrency(
                event.newCurrency,
                event.converterDestination
            )
        }
    }

    private fun changeCurrency(newCurrency: Currency, converterDestination: ConverterDestination) {
        when(converterDestination) {
            ConverterDestination.FROM -> {
                screenState.value = screenState.value.copy(
                    from = newCurrency
                )
            }
            ConverterDestination.TO -> {
                screenState.value = screenState.value.copy(
                    to = newCurrency
                )
            }
        }

    }

    private fun convertCurrencies(fromShortCode: String, toShortCode: String, amount: Double) {
        viewModelScope.launch {
            screenState.value = screenState.value.copy(
                isLoading = true
            )
            try {
                val convertedAmount = repository.convertCurrency(
                    from = fromShortCode,
                    to = toShortCode,
                    amount = amount
                )
                val roundedAmount = (convertedAmount * 100).roundToInt() / 100.0
                screenState.value = screenState.value.copy(
                    convertedAmount = roundedAmount,
                    isLoading = false,
                    isError = false
                )
            } catch (e: Exception) {
                screenState.value = screenState.value.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

    private fun loadCurrencyList() {
        viewModelScope.launch {
            screenState.value = screenState.value.copy(
                isLoading = true
            )
            try {
                val currencyListFromNetwork = repository.getCurrencies()
                screenState.value = screenState.value.copy(
                    currencyList = currencyListFromNetwork,
                    isLoading = false,
                    isError = false
                )
            } catch (e: Exception) {
                screenState.value = screenState.value.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

    private fun changeAmount(newAmount: Double) {
        screenState.value = screenState.value.copy(
            fromAmount = newAmount
        )
    }

    private fun swapCurrencies() {
        screenState.value = screenState.value.copy(
            from = screenState.value.to,
            fromAmount = screenState.value.convertedAmount,
            to = screenState.value.from,
            convertedAmount = screenState.value.fromAmount
        )
    }

}