package com.pavelshelkovenko.currencyconvertervk.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.pavelshelkovenko.currencyconvertervk.domain.Currency
import com.pavelshelkovenko.currencyconvertervk.presentation.FlagResIdProvider

@Composable
fun CurrencyDropdownItem(
    currency: Currency,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItem(
        onClick = onClick,
        modifier = modifier,
        leadingIcon = {
            Image(
                painter = painterResource(
                    id = FlagResIdProvider.provideFlagResIdByShortCode(
                        currency.shortCode
                    )
                ),
                contentDescription = currency.shortCode,
                modifier = Modifier.size(24.dp)
            )
        },
        text = {
            Text(text = currency.shortCode.lowercase().capitalize(Locale.current))
        }
    )
}