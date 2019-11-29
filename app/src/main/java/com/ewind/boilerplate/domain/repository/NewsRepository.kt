package com.ewind.boilerplate.domain.repository

import com.ewind.boilerplate.domain.model.DResponse
import io.reactivex.Observable

interface NewsRepository {
    fun getTopNews(page: Int, country: String): Observable<DResponse>
    fun getNews(keyword: String? = null, page: Int): Observable<DResponse>
}