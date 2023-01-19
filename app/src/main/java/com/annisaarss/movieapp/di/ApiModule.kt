package com.annisaarss.movieapp.di

import com.annisaarss.movieapp.BuildConfig
import com.annisaarss.movieapp.data.lib.HeaderInterceptor
import com.annisaarss.movieapp.data.lib.ParameterInterceptor
import com.nbs.nucleo.data.PreferenceManager
import com.nbs.nucleo.data.libs.OkHttpClientFactory
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BASE_URL: String = "baseUrl"

val apiModule = module {

    single {
        return@single OkHttpClientFactory.create(
            interceptors = arrayOf(getHeaderInterceptor(get()), getParameterInterceptor()),
            showDebugLog = BuildConfig.DEBUG,
            authenticator = null
        )
    }

    single(named(BASE_URL)) { BuildConfig.BASE_URL }
}

private fun getParameterInterceptor(): Interceptor {
    val params = HashMap<String, String>()
    //define default parameter here

    return ParameterInterceptor(params)
}

private fun getHeaderInterceptor(preferenceManager: PreferenceManager): Interceptor {
    val headers = HashMap<String, String>()
    //define default headers here
    headers["Content-Type"] = "application/json"

    return HeaderInterceptor(headers, preferenceManager)
}