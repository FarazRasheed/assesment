package com.faraz.weatherapp.data.source.remote.model.forecast

data class Weather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)