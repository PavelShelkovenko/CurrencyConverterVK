package com.pavelshelkovenko.currencyconvertervk.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pavelshelkovenko.currencyconvertervk.presentation.composables.CurrencyCard
import com.pavelshelkovenko.currencyconvertervk.presentation.composables.SwapButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun ConverterScreen(
    modifier: Modifier = Modifier,
) {

    val converterViewModel = koinViewModel<ConverterViewModel>()
    val screenState = converterViewModel.screenState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        converterViewModel.onEvent(ConverterScreenEvent.LoadCurrencyList)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Currency Converter",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        item {
            CurrencyCard(
                currenciesForDropdown = screenState.value.currencyList,
                currency = screenState.value.from,
                isEditingAmountEnable = true,
                amount = screenState.value.fromAmount,
                onAmountChange = {
                    converterViewModel.onEvent(ConverterScreenEvent.ChangeAmount(newAmount = it.toDouble()))
                },
                onSelectCurrency = { newCurrency ->
                    converterViewModel.onEvent(
                        ConverterScreenEvent.ChangeCurrency(
                            newCurrency,
                            ConverterDestination.FROM
                        )
                    )
                }
            )
        }
        item {
            Spacer(modifier = Modifier.height(15.dp))
            SwapButton(
                onClick = {
                    converterViewModel.onEvent(ConverterScreenEvent.SwapCurrencies)
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
        item {
            CurrencyCard(
                currenciesForDropdown = screenState.value.currencyList,
                currency = screenState.value.to,
                isEditingAmountEnable = false,
                amount = screenState.value.convertedAmount,
                onAmountChange = {
                    converterViewModel.onEvent(ConverterScreenEvent.ChangeAmount(newAmount = it.toDouble()))
                },
                onSelectCurrency = { newCurrency ->
                    converterViewModel.onEvent(
                        ConverterScreenEvent.ChangeCurrency(
                            newCurrency,
                            ConverterDestination.FROM
                        )
                    )
                }
            )
        }
        item {
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    converterViewModel.onEvent(
                        event = ConverterScreenEvent.ConvertCurrencies(
                            fromShortCode = screenState.value.from.shortCode,
                            toShortCode = screenState.value.to.shortCode,
                            amount = screenState.value.fromAmount
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(0.8f),
            ) {
                Text(
                    text = "CONVERT",
                )
            }
        }
    }
}