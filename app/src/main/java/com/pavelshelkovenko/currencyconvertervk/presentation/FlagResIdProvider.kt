package com.pavelshelkovenko.currencyconvertervk.presentation

import com.pavelshelkovenko.currencyconvertervk.R

object FlagResIdProvider {

    fun provideFlagResIdByShortCode(shortCode: String?): Int {
        return when(shortCode) {
            "RUB" -> R.drawable.russian
            "CNY" -> R.drawable.chinese
            "KRW" -> R.drawable.korean
            "INR" -> R.drawable.hindi
            "UAE" -> R.drawable.arabic
            "UAH" -> R.drawable.ukrainian
            "JPY" -> R.drawable.japanese
            "TRY" -> R.drawable.turkish
            "USD" -> R.drawable.usd
            "EUR" -> R.drawable.european_union
            else -> R.drawable.question_mark
        }
    }
}