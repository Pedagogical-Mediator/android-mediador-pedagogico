package com.ufms.mediadorpedagogico.presentation.util.dependecyinjector

import com.ufms.mediadorpedagogico.presentation.bullying.delegate.BullyingDelegate
import com.ufms.mediadorpedagogico.presentation.bullying.delegate.DefaultBullyingDelegate
import com.ufms.mediadorpedagogico.presentation.guild.delegate.DefaultGuildDelegate
import com.ufms.mediadorpedagogico.presentation.guild.delegate.GuildDelegate
import org.koin.dsl.module

val delegateModule = module {
    factory<BullyingDelegate> { DefaultBullyingDelegate(get(), get()) }
    factory<GuildDelegate> { DefaultGuildDelegate(get(), get()) }
}