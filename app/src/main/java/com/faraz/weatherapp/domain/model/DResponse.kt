package com.faraz.weatherapp.domain.model

data class DResponse(
    val totalResults: Int?,
    val articles: List<DArticles>?
)