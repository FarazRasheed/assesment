package com.ewind.boilerplate.domain.model

data class DResponse(
    val totalResults: Int?,
    val articles: List<DArticles>?
)