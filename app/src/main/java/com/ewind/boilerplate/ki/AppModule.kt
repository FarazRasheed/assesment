package com.ewind.boilerplate.ki

import com.ewind.boilerplate.BuildConfig
import com.ewind.boilerplate.data.remote.apis.NewsApi
import com.ewind.boilerplate.util.network.SupportInterceptor
import com.ewind.boilerplate.util.network.createNetworkClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

private val tokenAuthenticator = SupportInterceptor(BuildConfig.API_KEY)

val networkModule: Module = module {
    single { tokenAuthenticator }
    single { NEWS_API }
}

val retrofit: Retrofit =
    createNetworkClient(
        BuildConfig.API_URL,
        BuildConfig.DEBUG,
        tokenAuthenticator
    )

val NEWS_API: NewsApi = retrofit.create(NewsApi::class.java)
