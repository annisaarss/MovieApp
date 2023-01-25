package com.annisaarss.movieapp.di.featureModules

import com.annisaarss.movieapp.data.AppDatabase
import com.annisaarss.movieapp.data.favorite.FavoriteDataStore
import com.annisaarss.movieapp.data.favorite.FavoriteRepository
import com.annisaarss.movieapp.domain.favorite.FavoriteInteractor
import com.annisaarss.movieapp.domain.favorite.FavoriteUseCase
import com.annisaarss.movieapp.viewmodel.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {

    single {
        val appDatabase: AppDatabase = get()
        return@single appDatabase.favoriteDao()
    }

    single<FavoriteRepository> { FavoriteDataStore(get()) }

    single<FavoriteUseCase> { FavoriteInteractor(get()) }

    viewModel { FavoriteViewModel(get(), get()) }
}