package com.ximikat.mafiarating

import android.app.Application
import com.ximikat.mafiarating.di.databaseModule
import com.ximikat.mafiarating.di.repositoryModule
import com.ximikat.mafiarating.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(viewModelModule, repositoryModule, databaseModule)
            androidContext(this@App)
        }
    }

}