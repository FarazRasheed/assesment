package com.faraz.weatherapp.data.repository

import android.util.Log
import com.faraz.weatherapp.BuildConfig
import com.faraz.weatherapp.data.source.remote.apis.WeatherApi
import com.faraz.weatherapp.data.source.remote.model.forecast.Weather
import com.faraz.weatherapp.domain.repository.WeatherRepository
import io.reactivex.Observable

class WeatherRepositoryImpl(val weatherApi: WeatherApi) : WeatherRepository {

    override fun getWeather(city: String): Observable<Weather> {
        Log.e("Calling==>getWeather", "getWeather===>")
        return weatherApi.getWeather(
            key = BuildConfig.API_KEY,
            q = city,
            days = BuildConfig.DAYS,
            aqi = BuildConfig.AQI,
            alerts = BuildConfig.ALERTS
        )
    }

}