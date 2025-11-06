package com.chapter.android.nav3

import android.app.Application
import com.chapter.android.nav3.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        org.koin.core.context.startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                Modules
            )
        }
    }
}