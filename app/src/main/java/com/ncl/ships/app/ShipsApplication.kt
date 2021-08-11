package com.ncl.ships.app

import android.app.Application
import com.ncl.ships.di.dataModule
import com.ncl.ships.di.domainModule
import com.ncl.ships.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ShipsApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@ShipsApplication)
            modules(presentationModule, dataModule, domainModule)
        }
    }
}
