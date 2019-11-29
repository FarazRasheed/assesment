package com.ewind.newsapi.ui.main.base

import androidx.lifecycle.ViewModel

/**
 * Created by cuongpm on 12/10/18.
 */

abstract class BaseViewModel : ViewModel() {

    abstract fun start()

    abstract fun stop()
}