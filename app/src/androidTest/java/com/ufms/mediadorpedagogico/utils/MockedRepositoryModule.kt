package com.ufms.mediadorpedagogico.utils

import com.ufms.mediadorpedagogico.data.remote.repository.*
import com.ufms.mediadorpedagogico.domain.boundary.*
import com.ufms.mediadorpedagogico.mocks.MockDefaultNoticeRepository
import org.koin.dsl.module

val mockedRepositoryModule = module {
    factory<UserRepository> { DefaultUserRepository(get()) }
    factory<HomeworkRepository> { DefaultHomeworkRepository(get()) }
    factory<NoticeRepository> { MockDefaultNoticeRepository() }
    factory<NewsRepository> { DefaultNewsRepository(get()) }
    factory<BullyingRepository> { DefaultBullyingRepository(get()) }
    factory<GuildRepository> { DefaultGuildRepository(get()) }
    factory<AboutRepository> { DefaultAboutRepository(get()) }
    factory<CalendarRepository> { DefaultCalendarRepository(get()) }
    factory<LibraryRepository> { DefaultLibraryRepository(get()) }
    factory<TeacherRepository> { DefaultTeacherRepository(get()) }
}