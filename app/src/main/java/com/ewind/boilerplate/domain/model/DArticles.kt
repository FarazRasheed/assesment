package com.ewind.boilerplate.domain.model

data class DArticles(
    val source: DSource?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)