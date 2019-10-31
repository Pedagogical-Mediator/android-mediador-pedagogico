package com.ufms.mediadorpedagogico.presentation.util.dependecyinjector

import com.ufms.mediadorpedagogico.presentation.about.delegate.AboutDelegate
import com.ufms.mediadorpedagogico.presentation.about.delegate.DefaultAboutDelegate
import com.ufms.mediadorpedagogico.presentation.bullying.delegate.BullyingDelegate
import com.ufms.mediadorpedagogico.presentation.bullying.delegate.DefaultBullyingDelegate
import com.ufms.mediadorpedagogico.presentation.calendar.delegate.CalendarDelegate
import com.ufms.mediadorpedagogico.presentation.calendar.delegate.DefaultCalendarDelegate
import com.ufms.mediadorpedagogico.presentation.guild.delegate.DefaultGuildDelegate
import com.ufms.mediadorpedagogico.presentation.guild.delegate.GuildDelegate
import com.ufms.mediadorpedagogico.presentation.library.delegate.DefaultLibraryDelegate
import com.ufms.mediadorpedagogico.presentation.library.delegate.LibraryDelegate
import org.koin.dsl.module

val delegateModule = module {
    factory<BullyingDelegate> { DefaultBullyingDelegate(get(), get()) }
    factory<AboutDelegate> { DefaultAboutDelegate(get(), get()) }
    factory<GuildDelegate> { DefaultGuildDelegate(get(), get()) }
    factory<CalendarDelegate> { DefaultCalendarDelegate(get(), get()) }
    factory<LibraryDelegate> { DefaultLibraryDelegate(get(), get(), get()) }
}