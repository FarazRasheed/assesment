package com.ewind.newsapi.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ewind.newsapi.data.local.model.UserDB

@Dao
interface UserDao {

    @Query("SELECT * FROM userdb")
    abstract fun getAll(): List<UserDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(userDB: UserDB)

    @Query("DELETE FROM userdb")
    abstract fun delete()

}