package com.ewind.newsapi.ui.main.home

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ewind.newsapi.R
import com.ewind.newsapi.data.local.database.DatabaseClient
import com.ewind.newsapi.data.local.model.PreferencesDB
import com.ewind.newsapi.ki.injectFeature
import com.ewind.newsapi.ui.main.base.BaseActivity
import com.ewind.newsapi.util.Constant
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject

class HomeActivity : BaseActivity() {

    private val databaseClient by inject<DatabaseClient>()

    override fun onCreate(savedInstanceState: Bundle?) {
        injectFeature()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_top_news, R.id.navigation_news, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val list = Constant.PRE_ARRAY.map {
            PreferencesDB(it)
        }
        //databaseClient.appDatabases().preferenceDao().insertAll(list)
    }
}
