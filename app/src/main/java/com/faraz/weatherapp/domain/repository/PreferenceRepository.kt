package com.faraz.weatherapp.domain.repository

import com.faraz.weatherapp.domain.model.Category
import io.reactivex.Observable

interface PreferenceRepository {
    fun getAllPref(): Observable<List<Category>>
}