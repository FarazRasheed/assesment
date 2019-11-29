package com.ewind.boilerplate.ui.main.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ewind.boilerplate.domain.model.DArticles
import com.ewind.boilerplate.domain.usecase.NewsUseCase
import com.ewind.boilerplate.util.Constant
import com.ewind.boilerplate.util.Resource
import com.ewind.boilerplate.util.TempVar
import com.ewind.boilerplate.util.ext.setError
import com.ewind.boilerplate.util.ext.setLoading
import com.ewind.boilerplate.util.ext.setSuccess
import com.ewind.boilerplate.util.network.ErrorHandler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel(val newsUseCase: NewsUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val newsliveDate = MutableLiveData<Resource<MutableList<DArticles>>>()
    var isLoading = false
    var currentPage: Int = 1
    var totalCount: Int? = null
    var keyword: String = "all"

    fun getNews() {
        newsliveDate.setLoading()
        compositeDisposable.add(
            newsUseCase.getNews(keyword, currentPage)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    isLoading = true
                }
                .doOnTerminate {
                    isLoading = false
                }
                .subscribe(
                    { response ->
                        totalCount = response.totalResults
                        val listWork = response.articles
                        if (!listWork.isNullOrEmpty()) {
                            TempVar.per_page = Constant.PER_PAGE
                            newsliveDate.setSuccess(listWork.toMutableList(), null)
                        }
                    },
                    {
                        newsliveDate.setError(ErrorHandler.getApiErrorMessage(it))
                    })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}