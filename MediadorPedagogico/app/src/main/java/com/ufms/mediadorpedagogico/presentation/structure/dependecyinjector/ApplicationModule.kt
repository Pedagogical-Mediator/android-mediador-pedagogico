package com.ufms.mediadorpedagogico.presentation.structure.dependecyinjector

import com.ufms.mediadorpedagogico.domain.boundary.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.domain.boundary.resources.StringsProvider
import com.ufms.mediadorpedagogico.presentation.util.resources.AndroidStringProvider
import com.ufms.mediadorpedagogico.presentation.util.resources.DefaultSchedulerProvider
import org.koin.dsl.module

val applicationModule = module {
    factory<StringsProvider> { AndroidStringProvider(get()) }
    factory<SchedulerProvider> { DefaultSchedulerProvider() }
}