package com.annisaarss.movieapp.base

import com.annisaarss.movieapp.di.apiModule
import com.annisaarss.movieapp.di.rxModule
import com.annisaarss.movieapp.di.utilityModule
import com.nbs.nucleo.presentation.BaseApplication
import com.nbs.nucleo.utils.ContextProvider
import com.nbs.nucleo.utils.ThemeManager
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import timber.log.Timber

class MovieApplication : BaseApplication() {

    private val themeManager: ThemeManager by inject()

    override fun getDefinedModules(): List<Module> {

        return listOf(
            apiModule,
            rxModule,
            utilityModule
        )
    }

    override fun initApp() {
        ContextProvider.initialize(this)
        themeManager.setTheme()
        Timber.plant(Timber.DebugTree())
    }
}