package com.faraz.weatherapp.domain.repository

import com.faraz.weatherapp.data.source.remote.model.forecast.Weather
import com.faraz.weatherapp.domain.model.DResponse
import io.reactivex.Observable

interface WeatherRepository {
    fun getWeather(city: String): Observable<Weather>
}