package com.ewind.boilerplate.ki

import com.ewind.boilerplate.data.repository.NewsRepositoryImpl
import com.ewind.boilerplate.domain.repository.NewsRepository
import com.ewind.boilerplate.domain.usecase.NewsUseCase
import com.ewind.boilerplate.ui.main.home.HomeViewModel
import com.ewind.boilerplate.ui.main.news.NewsViewModel
import com.ewind.boilerplate.ui.main.profile.ProfileViewModel
import com.ewind.boilerplate.ui.main.topnews.TopNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModelModule,
            useCaseModule,
            repositoryModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel { HomeViewModel() }
    viewModel { NewsViewModel(newsUseCase = get()) }
    viewModel { ProfileViewModel() }
    viewModel { TopNewsViewModel(topNewsUseCase = get()) }
}

val useCaseModule: Module = module {
    factory { NewsUseCase(newsRepository = get()) }
}

val repositoryModule: Module = module {
    single<NewsRepository> {
        NewsRepositoryImpl(
            newsApi = get()
        )
    }
}