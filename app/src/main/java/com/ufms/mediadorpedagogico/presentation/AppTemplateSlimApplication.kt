package com.ufms.mediadorpedagogico.presentation

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppTemplateSlimApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppTemplateSlimApplication)
            Stetho.initializeWithDefaults(this@AppTemplateSlimApplication)
            modules(
                listOf(
                    interactorModule,
                    repositoryModule,
                    applicationModule,
                    viewModelModule,
                    delegateModule
                )
            )
        }
    }
}
