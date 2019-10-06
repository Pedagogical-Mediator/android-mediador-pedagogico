package com.ufms.mediadorpedagogico.presentation.util.dependecyinjector

import com.ufms.mediadorpedagogico.domain.interactor.bullying.GetBullying
import com.ufms.mediadorpedagogico.domain.interactor.homework.GetHomework
import com.ufms.mediadorpedagogico.domain.interactor.news.GetNews
import com.ufms.mediadorpedagogico.domain.interactor.notice.GetNotice
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import com.ufms.mediadorpedagogico.domain.interactor.user.SignIn
import org.koin.dsl.module

val interactorModule = module {
    single { GetPersistedUser(get()) }
    single { SignIn(get()) }
    single { GetHomework(get()) }
    single { GetNotice(get()) }
    single { GetNews(get()) }
    single { GetBullying(get()) }
}