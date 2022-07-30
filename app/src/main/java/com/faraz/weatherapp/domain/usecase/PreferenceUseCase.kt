package com.faraz.weatherapp.domain.usecase

import com.faraz.weatherapp.domain.model.Category
import com.faraz.weatherapp.domain.repository.PreferenceRepository
import io.reactivex.Observable

class PreferenceUseCase(val preferenceRepository: PreferenceRepository) {
    fun getPreferenceAll(): Observable<List<Category>> = preferenceRepository.getAllPref()
}