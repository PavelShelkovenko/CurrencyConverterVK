package com.pavelshelkovenko.currencyconvertervk.di


import com.pavelshelkovenko.currencyconvertervk.data.ApiService
import com.pavelshelkovenko.currencyconvertervk.data.ConverterRepositoryImpl
import com.pavelshelkovenko.currencyconvertervk.domain.ConverterRepository
import com.pavelshelkovenko.currencyconvertervk.presentation.ConverterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::ConverterViewModel)
    singleOf(::ConverterRepositoryImpl){ bind<ConverterRepository>() }
    single<ApiService> { ApiService() }
}