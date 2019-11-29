package com.ewind.boilerplate.domain.model

import com.ewind.boilerplate.data.remote.model.Articles
import com.ewind.boilerplate.data.remote.model.Response
import com.ewind.boilerplate.data.remote.model.Source

fun Response.toViewModel(): DResponse = DResponse(
    totalResults, articles?.map { it.toViewModel() }
)

fun Articles.toViewModel(): DArticles = DArticles(
    source?.toViewModel(), author, title, description, url, urlToImage, publishedAt, content
)

fun Source.toViewModel(): DSource = DSource(
    id, name
)