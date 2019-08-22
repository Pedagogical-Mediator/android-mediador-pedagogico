package com.ufms.mediadorpedagogico.presentation

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.ufms.mediadorpedagogico.presentation.structure.sl.ServiceLocator
import org.koin.core.context.startKoin

class AppTemplateSlimApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator.getInstance(this)
        Stetho.initializeWithDefaults(this)
        startKoin {
            androidLogger()
            androidContext(this@AppTemplateSlimApplication)
            modules(listOf(providerModule, applicationModule, viewModelModule))
        }
    }
}
