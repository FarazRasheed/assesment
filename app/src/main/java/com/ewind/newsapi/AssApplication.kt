package com.ewind.newsapi

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.ewind.newsapi.di.dataBaseModule
import com.ewind.newsapi.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by cuongpm on 11/29/18.
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