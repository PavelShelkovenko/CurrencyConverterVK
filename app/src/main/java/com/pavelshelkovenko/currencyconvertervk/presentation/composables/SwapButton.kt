package com.pavelshelkovenko.currencyconvertervk.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pavelshelkovenko.currencyconvertervk.R
import com.pavelshelkovenko.currencyconvertervk.presentation.ui.theme.Blue
import com.pavelshelkovenko.currencyconvertervk.presentation.ui.theme.CurrencyConverterVKTheme

@Composable
fun SwapButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 12.dp,
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable { onClick() }
            .background(Color.White)
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_swap_vert_24),
            modifier = Modifier
                .size(42.dp),
            tint = Blue,
            contentDescription = ""
        )
    }

}

@Preview
@Composable
fun SwapButtonPreview() {
    CurrencyConverterVKTheme {
        SwapButton(
            onClick = {}
        )
    }
}