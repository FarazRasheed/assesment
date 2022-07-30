package com.faraz.weatherapp.data.source.remote.apis


import com.faraz.weatherapp.data.source.remote.model.forecast.Weather
import com.faraz.weatherapp.util.network.QueryConst
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json")
    fun getWeather(
        @Query(QueryConst.KEY) key: String? = null,
        @Query(QueryConst.Q) q: String? = null,
        @Query(QueryConst.DAYS) days: String? = null,
        @Query(QueryConst.AQI) aqi: String? = null,
        @Query(QueryConst.ALERTS) alerts: String? = null
    ): Observable<Weather>
}