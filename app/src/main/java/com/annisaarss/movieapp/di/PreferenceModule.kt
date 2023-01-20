package com.annisaarss.movieapp.di

import com.annisaarss.movieapp.utils.constants.AppConstants
import com.nbs.nucleo.data.PreferenceManager
import com.nbs.nucleo.data.PreferenceManagerImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val PREFERENCE_NAME = "preference_name"

val preferenceModule = module {

    single(named(PREFERENCE_NAME)) { AppConstants.PREF_NAME }

    single<PreferenceManager> { PreferenceManagerImpl(get(), get(named(PREFERENCE_NAME)), get()) }

}