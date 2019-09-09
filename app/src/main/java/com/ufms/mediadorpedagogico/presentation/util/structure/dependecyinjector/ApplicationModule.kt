package com.ufms.mediadorpedagogico.presentation.util.structure.dependecyinjector

import com.ufms.mediadorpedagogico.data.remote.client.ApiClient
import com.ufms.mediadorpedagogico.data.storage.PreferencesCache
import com.ufms.mediadorpedagogico.domain.boundary.resources.Cache
import com.ufms.mediadorpedagogico.domain.boundary.resources.Logger
import com.ufms.mediadorpedagogico.domain.boundary.resources.StringsProvider
import com.ufms.mediadorpedagogico.presentation.util.ErrorHandler
import com.ufms.mediadorpedagogico.presentation.util.resources.AndroidLogger
import com.ufms.mediadorpedagogico.presentation.util.resources.AndroidStringProvider
import com.ufms.mediadorpedagogico.presentation.util.resources.LoginAction
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import org.koin.dsl.bind
import org.koin.dsl.module

val applicationModule = module {
    factory<StringsProvider> { AndroidStringProvider(get()) }
    factory { SchedulerProvider() }
    factory { LoginAction(get(), get()) }
    //single(named(NAME_HERE)) {MyObject()}
    factory { AndroidLogger(get()) } bind Logger::class
    factory { PreferencesCache.init(get()) } bind Cache::class
    factory { ErrorHandler(get(), get(), get()) }
    factory { ApiClient }
}