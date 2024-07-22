package com.pavelshelkovenko.currencyconvertervk.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("convert")
    suspend fun convertCurrency(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double,
    ): ConvertResponse

    @GET("currencies")
    suspend fun getCurrencies(
        @Query("type") type: String = "fiat"
    ): CurrencyResponse
}

fun ApiService(
    baseUrl: String = "https://api.currencybeacon.com/v1/",
    okHttpClient: OkHttpClient? = null,
    ): ApiService {
    return retrofit(
        baseUrl = baseUrl,
        okHttpClient = okHttpClient
    ).create()

}

private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient?
): Retrofit {

    val modifiedOkHttpClient: OkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val newUrl = chain.request().url.newBuilder()
                .addQueryParameter(
                    name = "api_key",
                    value = "TJkXpemE507tjR6wrzBp2xUg9iJhZsJh"
                )
                .build()
            val request = chain.request().newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(request)
        }
        .addNetworkInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        )
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(modifiedOkHttpClient)
        .build()
}

