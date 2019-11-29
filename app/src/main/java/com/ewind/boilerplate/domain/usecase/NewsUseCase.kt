package com.ewind.boilerplate.domain.usecase

import com.ewind.boilerplate.domain.model.DResponse
import com.ewind.boilerplate.domain.repository.NewsRepository
import io.reactivex.Observable

class NewsUseCase(val newsRepository: NewsRepository) {
    fun getTopNews(page: Int, country: String) = newsRepository.getTopNews(page, country)
    fun getNews(keyword: String?, page: Int): Observable<DResponse> =
        newsRepository.getNews(keyword, page)
}