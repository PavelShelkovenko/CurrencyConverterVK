package com.pavelshelkovenko.currencyconvertervk.data

import com.google.gson.annotations.SerializedName

data class ConvertResponse(
    @SerializedName("value")
    val convertedValue: Double
)
