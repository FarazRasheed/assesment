package com.ewind.newsapi.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ewind.newsapi.data.local.model.UserDB

@Database(entities = [UserDB::class], version = 6)
abstract class AppDatabases : RoomDatabase() {
    abstract fun userDao(): AuthorDao
}