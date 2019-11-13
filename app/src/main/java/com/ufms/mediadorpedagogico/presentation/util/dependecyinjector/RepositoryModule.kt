package com.ufms.mediadorpedagogico.presentation.util.dependecyinjector

import com.ufms.mediadorpedagogico.data.remote.repository.*
import com.ufms.mediadorpedagogico.domain.boundary.*
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository> { DefaultUserRepository(get()) }
    factory<HomeworkRepository> { DefaultHomeworkRepository(get()) }
    factory<NoticeRepository> { DefaultNoticeRepository(get()) }
    factory<NewsRepository> { DefaultNewsRepository(get()) }
    factory<BullyingRepository> { DefaultBullyingRepository(get()) }
    factory<GuildRepository> { DefaultGuildRepository(get()) }
    factory<AboutRepository> { DefaultAboutRepository(get()) }
    factory<CalendarRepository> { DefaultCalendarRepository(get()) }
    factory<LibraryRepository> { DefaultLibraryRepository(get()) }
    factory<TeacherRepository> { DefaultTeacherRepository(get()) }
}