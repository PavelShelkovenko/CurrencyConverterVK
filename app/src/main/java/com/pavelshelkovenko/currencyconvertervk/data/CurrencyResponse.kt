package com.pavelshelkovenko.currencyconvertervk.data

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("response")
    val currenciesList: List<CurrencyDto>
)
