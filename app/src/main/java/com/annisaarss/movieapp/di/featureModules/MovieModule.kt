package com.annisaarss.movieapp.di.featureModules

import com.annisaarss.movieapp.data.movie.MovieDataStore
import com.annisaarss.movieapp.data.movie.MovieRepository
import com.annisaarss.movieapp.data.movie.remote.MovieApi
import com.annisaarss.movieapp.data.movie.remote.MovieApiClient
import com.annisaarss.movieapp.di.BASE_URL
import com.annisaarss.movieapp.domain.movie.MovieInteractor
import com.annisaarss.movieapp.domain.movie.MovieUseCase
import com.annisaarss.movieapp.viewmodel.PopularViewModel
import com.annisaarss.movieapp.viewmodel.HomeViewModel
import com.nbs.nucleo.data.libs.ApiService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieModule = module {
    single { ApiService.createReactiveService(MovieApiClient::class.java, get(), get(named(BASE_URL))) }

    single { MovieApi(get()) }

    single<MovieRepository> { MovieDataStore(get()) }

    single<MovieUseCase> { MovieInteractor(get()) }

    viewModel { PopularViewModel(get(), get()) }

    viewModel { HomeViewModel(get(), get()) }
}