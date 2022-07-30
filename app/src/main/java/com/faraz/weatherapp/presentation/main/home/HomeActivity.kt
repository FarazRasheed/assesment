package com.faraz.weatherapp.presentation.main.home

import android.os.Bundle
import androidx.navigation.findNavController
import com.faraz.weatherapp.R
import com.faraz.weatherapp.di.injectFeature
import com.faraz.weatherapp.presentation.main.base.BaseActivity

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectFeature()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navController = findNavController(R.id.nav_host_fragment)
    }
}
