package com.ufms.mediadorpedagogico.presentation

import androidx.multidex.MultiDexApplication
import com.ufms.mediadorpedagogico.presentation.util.structure.dependecyinjector.applicationModule
import com.ufms.mediadorpedagogico.presentation.util.structure.dependecyinjector.interactorModule
import com.ufms.mediadorpedagogico.presentation.util.structure.dependecyinjector.repositoryModule
import com.ufms.mediadorpedagogico.presentation.util.structure.dependecyinjector.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppTemplateSlimApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppTemplateSlimApplication)
            modules(listOf(interactorModule, repositoryModule, applicationModule, viewModelModule))
        }
    }
}
