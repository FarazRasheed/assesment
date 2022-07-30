package com.faraz.weatherapp.di

import com.faraz.weatherapp.BuildConfig
import com.faraz.weatherapp.data.source.local.database.DatabaseClient
import com.faraz.weatherapp.data.source.remote.apis.WeatherApi
import com.faraz.weatherapp.util.network.SupportInterceptor
import com.faraz.weatherapp.util.network.createNetworkClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

private val tokenAuthenticator = SupportInterceptor(BuildConfig.API_KEY)

val networkModule: Module = module {
    single { tokenAuthenticator }
    single {
        createNetworkClient(
            androidContext(),
            BuildConfig.API_URL,
            BuildConfig.DEBUG,
            tokenAuthenticator
        )
    }
    single { get<Retrofit>().create(WeatherApi::class.java) }
}


val dataBaseModule: Module = module {
    single { DatabaseClient(androidApplication()) }
}