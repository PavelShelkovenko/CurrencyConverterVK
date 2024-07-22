package com.pavelshelkovenko.currencyconvertervk.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.pavelshelkovenko.currencyconvertervk.domain.Currency
import com.pavelshelkovenko.currencyconvertervk.presentation.ui.theme.CurrencyConverterVKTheme
import com.pavelshelkovenko.currencyconvertervk.presentation.ui.theme.LightBlue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrencyCard(
    modifier: Modifier = Modifier,
    currenciesForDropdown: List<Currency>,
    onSelectCurrency: (Currency) -> Unit,
    currency: Currency,
    amount: Double,
    onAmountChange: (String) -> Unit,
    isEditingAmountEnable: Boolean,
) {

    val isOpen = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 14.dp,
                shape = RoundedCornerShape(18.dp)
            )
            .background(Color.White)
            .wrapContentHeight()
            .padding(20.dp)
    ) {
        Text(
            text = "Select your currency type",
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        CurrencyDropdown(
            currencies = currenciesForDropdown,
            selectedCurrencyShortCode = currency.shortCode,
            isOpen = isOpen.value,
            onClick = { isOpen.value = !isOpen.value },
            onDismiss = { isOpen.value = false },
            onSelectCurrency = onSelectCurrency
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Enter your currency",
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = currency.symbol,
                fontSize = 24.sp,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(14.dp))
            TextField(
                value = amount.toString(),
                onValueChange = onAmountChange,
                textStyle = MaterialTheme.typography.bodyMedium,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors().copy(
                    unfocusedContainerColor = LightBlue,
                    focusedContainerColor = LightBlue,
                    disabledContainerColor = LightBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = isEditingAmountEnable,
            )
        }
    }
}

@Preview
@Composable
fun CurrencyCardPreview() {
    CurrencyConverterVKTheme {
        CurrencyCard(
           currenciesForDropdown = listOf(), currency = Currency(
                id = 2854,
                shortCode = "condimentum",
                symbol = "ius",
            ),
            onSelectCurrency = {},
            isEditingAmountEnable = true,
            onAmountChange = {},
            amount = 12.655
        )
    }
}