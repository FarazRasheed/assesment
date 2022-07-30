package com.faraz.weatherapp.di

import com.faraz.weatherapp.data.repository.WeatherRepositoryImpl
import com.faraz.weatherapp.data.repository.PreferenceRepositoryImpl
import com.faraz.weatherapp.domain.repository.WeatherRepository
import com.faraz.weatherapp.domain.repository.PreferenceRepository
import com.faraz.weatherapp.domain.usecase.WeatherUseCase
import com.faraz.weatherapp.domain.usecase.PreferenceUseCase
import com.faraz.weatherapp.presentation.main.home.HomeViewModel
import com.faraz.weatherapp.presentation.main.profile.CityViewModel
import com.faraz.weatherapp.presentation.main.topnews.WFViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModelModule,
            useCaseModule,
            repositoryModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel { HomeViewModel() }
    viewModel { CityViewModel() }
    viewModel { WFViewModel(topWeatherUseCase = get()) }
}

val useCaseModule: Module = module {
    factory { WeatherUseCase(weatherRepository = get()) }
    factory { PreferenceUseCase(preferenceRepository = get()) }
}

val repositoryModule: Module = module {
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            weatherApi = get()
        )
    }
    single<PreferenceRepository> {
        PreferenceRepositoryImpl(
            databaseClient = get()
        )
    }
}