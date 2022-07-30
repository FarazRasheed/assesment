package com.faraz.weatherapp.domain.usecase

import com.faraz.weatherapp.domain.model.DResponse
import com.faraz.weatherapp.domain.repository.WeatherRepository
import io.reactivex.Observable

class WeatherUseCase(
    val weatherRepository: WeatherRepository
) {
    fun getWeatherForeCast(city: String) = weatherRepository.getWeather(city)

}