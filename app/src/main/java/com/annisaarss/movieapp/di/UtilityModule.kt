package com.annisaarss.movieapp.di

import com.google.gson.Gson
import com.nbs.nucleo.utils.ThemeManager
import org.greenrobot.eventbus.EventBus
import org.koin.dsl.module

val utilityModule = module {

    single { Gson() }

    single { EventBus() }

    single { ThemeManager(get()) }

}