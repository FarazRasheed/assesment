package com.faraz.weatherapp.presentation.main.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faraz.weatherapp.domain.model.DUser
import com.faraz.weatherapp.util.Msg
import com.faraz.weatherapp.util.Resource
import com.faraz.weatherapp.util.ext.setError
import com.faraz.weatherapp.util.ext.setLoading
import com.faraz.weatherapp.util.ext.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CityViewModel() : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val userLiveData = MutableLiveData<Resource<DUser>>()
    val updateLiveData = MutableLiveData<Resource<String>>()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    companion object {
        @JvmField
        val CITIES = arrayOf(
            "London",
            "Holborn",
            "St Giles",
            "Clerkenwell",
            "Finsbury",
            "St Pancras",
            "Bloomsbury",
            "Strand",
            "Shoreditch",
            "St Luke's"
        )
    }

}