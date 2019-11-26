package com.ufms.mediadorpedagogico.utils

import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.applicationModule
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.interactorModule
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.viewModelModule
import org.koin.core.context.startKoin

fun startMockedKoin() {
    startKoin {
        modules(listOf(interactorModule, mockedRepositoryModule, applicationModule, viewModelModule))
    }
}