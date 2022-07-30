package com.ewind.newsapi.data.source.remote.model.forecast

data class Weather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)