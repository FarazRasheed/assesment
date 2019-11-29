package com.ewind.newsapi.data.local.database

import android.content.Context
import androidx.room.Room
import com.ewind.newsapi.R

class DatabaseClient(val context: Context) {

    private var appDatabase: AppDatabases =
        Room.databaseBuilder(
            context,
            AppDatabases::class.java,
            context.getString(R.string.app_name)
        )
            .allowMainThreadQueries().build()

    fun appDatabases(): AppDatabases = appDatabase
}