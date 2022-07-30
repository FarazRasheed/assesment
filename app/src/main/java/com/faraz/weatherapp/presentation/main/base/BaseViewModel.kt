package com.faraz.weatherapp.presentation.main.base

import androidx.lifecycle.ViewModel

/**
 * Created by Faraz Rasheed.
 */

abstract class BaseViewModel : ViewModel() {

    abstract fun start()

    abstract fun stop()
}