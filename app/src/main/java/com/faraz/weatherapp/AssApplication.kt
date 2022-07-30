package com.faraz.weatherapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.faraz.weatherapp.di.dataBaseModule
import com.faraz.weatherapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Faraz
 */

open class AssApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AssApplication)
            modules(listOf(networkModule, dataBaseModule))
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}