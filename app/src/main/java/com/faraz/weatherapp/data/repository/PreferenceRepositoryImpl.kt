package com.faraz.weatherapp.data.repository

import com.faraz.weatherapp.data.source.local.database.DatabaseClient
import com.faraz.weatherapp.domain.model.Category
import com.faraz.weatherapp.domain.model.toViewModel
import com.faraz.weatherapp.domain.repository.PreferenceRepository
import io.reactivex.Observable

class PreferenceRepositoryImpl(private val databaseClient: DatabaseClient) : PreferenceRepository {
    override fun getAllPref(): Observable<List<Category>> =
        databaseClient.appDatabases().preferenceDao().getAll().map {
            it.map { pre -> pre.toViewModel() }
        }
}