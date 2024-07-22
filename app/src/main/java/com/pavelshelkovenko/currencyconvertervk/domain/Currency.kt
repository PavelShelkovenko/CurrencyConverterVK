package com.pavelshelkovenko.currencyconvertervk.domain

data class Currency(
    val id: Int,
    val shortCode: String,
    val symbol: String,
) {

    companion object {

        fun getUSDCurrency(): Currency = Currency(
            id = 9320948,
            shortCode = "USD",
            symbol = "$"
        )

        fun getRUBCurrency(): Currency = Currency(
            id = 438432,
            shortCode = "RUB",
            symbol = "₽",
        )
    }
}
