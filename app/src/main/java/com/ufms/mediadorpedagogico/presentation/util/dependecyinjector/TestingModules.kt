package com.ufms.mediadorpedagogico.presentation.util.dependecyinjector

import android.app.Application
import android.content.Context
import org.koin.dsl.bind
import org.koin.dsl.module

val androidContextModule = module {
    factory { Application() } bind Context::class
}