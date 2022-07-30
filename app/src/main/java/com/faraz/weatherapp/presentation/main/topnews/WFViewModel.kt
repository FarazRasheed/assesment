package com.faraz.weatherapp.presentation.main.topnews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faraz.weatherapp.data.source.remote.model.forecast.Weather
import com.faraz.weatherapp.domain.model.DArticles
import com.faraz.weatherapp.domain.usecase.WeatherUseCase
import com.faraz.weatherapp.util.Constant
import com.faraz.weatherapp.util.Resource
import com.faraz.weatherapp.util.TempVar
import com.faraz.weatherapp.util.ext.setError
import com.faraz.weatherapp.util.ext.setLoading
import com.faraz.weatherapp.util.ext.setSuccess
import com.faraz.weatherapp.util.network.ErrorHandler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WFViewModel(val topWeatherUseCase: WeatherUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val weatherliveDate = MutableLiveData<Resource<Weather>>()
    var isLoading = false

    fun getWeatherForeCast(city: String) {
        weatherliveDate.setLoading()
        compositeDisposable.add(
            topWeatherUseCase.getWeatherForeCast(city)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    isLoading = true
                }
                .doOnTerminate {
                    isLoading = false
                }
                .subscribe(
                    { response ->
                        weatherliveDate.setSuccess(response, null)
                    },
                    {
                        weatherliveDate.setError(ErrorHandler.getApiErrorMessage(it))
                    })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}