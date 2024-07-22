package com.pavelshelkovenko.currencyconvertervk.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pavelshelkovenko.currencyconvertervk.presentation.FlagResIdProvider
import com.pavelshelkovenko.currencyconvertervk.domain.Currency
import com.pavelshelkovenko.currencyconvertervk.presentation.ui.theme.LightBlue


@Composable
fun CurrencyDropdown(
    currencies: List<Currency>,
    selectedCurrencyShortCode: String,
    isOpen: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onSelectCurrency: (Currency) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = onDismiss
        ) {
            currencies.forEach { currency ->
                CurrencyDropdownItem(
                    currency = currency,
                    onClick = {
                        onSelectCurrency(currency)
                        onDismiss()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(LightBlue)
                .clickable(onClick = onClick)
        ) {
            Image(
                painter = painterResource(
                    id = FlagResIdProvider.provideFlagResIdByShortCode(
                        selectedCurrencyShortCode
                    )
                ),
                contentDescription = selectedCurrencyShortCode,
                modifier = Modifier.size(28.dp).padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = selectedCurrencyShortCode,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                modifier = Modifier.padding(12.dp),
                contentDescription = "",
            )
        }
    }
}