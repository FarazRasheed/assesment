package com.faraz.weatherapp.domain.model

import com.faraz.weatherapp.data.source.local.model.PreferencesDB
import com.faraz.weatherapp.data.source.local.model.UserDB


fun PreferencesDB.toViewModel(): Category = Category(keyword)

fun Category.toDBModel(): PreferencesDB = PreferencesDB(
    key
)

fun UserDB.toViewModel(): DUser = DUser(
    name
)

fun DUser.toDBModel(): UserDB = UserDB(
    name
)