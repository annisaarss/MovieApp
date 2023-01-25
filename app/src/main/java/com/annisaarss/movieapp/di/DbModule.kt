package com.annisaarss.movieapp.di

import com.annisaarss.movieapp.data.AppDatabase
import org.koin.dsl.module

val dbModule = module {

    single { AppDatabase.getAppDatabase(get()) }
}