package com.pavelshelkovenko.currencyconvertervk.data

import com.google.gson.annotations.SerializedName
import com.pavelshelkovenko.currencyconvertervk.domain.Currency

data class CurrencyDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("short_code")
    val shortCode: String?,
    @SerializedName("symbol")
    val symbol: String,
)

fun CurrencyDto.toCurrencyDomain(): Currency = Currency(
    id = this.id,
    symbol = this.symbol,
    shortCode = this.shortCode ?: "NO SHORT_CODE",
)
