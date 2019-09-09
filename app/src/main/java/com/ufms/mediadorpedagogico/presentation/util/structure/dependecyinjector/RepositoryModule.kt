package com.ufms.mediadorpedagogico.presentation.util.structure.dependecyinjector

import com.ufms.mediadorpedagogico.data.remote.repository.DefaultHomeworkRepository
import com.ufms.mediadorpedagogico.data.remote.repository.DefaultNoticeRepository
import com.ufms.mediadorpedagogico.data.remote.repository.DefaultUserRepository
import com.ufms.mediadorpedagogico.domain.boundary.HomeworkRepository
import com.ufms.mediadorpedagogico.domain.boundary.NoticeRepository
import com.ufms.mediadorpedagogico.domain.boundary.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository> { DefaultUserRepository(get()) }
    factory<HomeworkRepository> { DefaultHomeworkRepository(get()) }
    factory<NoticeRepository> { DefaultNoticeRepository(get()) }
}