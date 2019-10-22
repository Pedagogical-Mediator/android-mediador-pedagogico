package com.ufms.mediadorpedagogico.presentation.util.dependecyinjector

import com.ufms.mediadorpedagogico.presentation.bullying.delegate.BullyingDelegate
import com.ufms.mediadorpedagogico.presentation.bullying.delegate.DefaultBullyingDelegate
import org.koin.dsl.module

val delegateModule = module {
    factory<BullyingDelegate> { DefaultBullyingDelegate(get(), get()) }
}