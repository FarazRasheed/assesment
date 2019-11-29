package com.ewind.newsapi.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ewind.newsapi.R
import com.ewind.newsapi.data.local.model.PreferencesDB
import com.ewind.newsapi.util.Constant
import java.util.concurrent.Executors

class DatabaseClient(val context: Context) : RoomDatabase.Callback() {

    private var appDatabase: AppDatabases =
        Room.databaseBuilder(
            context,
            AppDatabases::class.java,
            context.getString(R.string.app_name)
        ).allowMainThreadQueries()
            .addCallback(this)
            .build()

    fun appDatabases(): AppDatabases = appDatabase

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Executors.newSingleThreadScheduledExecutor().execute {
            val list = Constant.PRE_ARRAY.map {
                PreferencesDB(it)
            }
            appDatabase.preferenceDao().insertAll(list)
        }
    }
}
