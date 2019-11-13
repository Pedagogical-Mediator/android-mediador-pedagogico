package com.ufms.mediadorpedagogico.presentation.util.dependecyinjector

import com.ufms.mediadorpedagogico.domain.interactor.about.GetAbout
import com.ufms.mediadorpedagogico.domain.interactor.bullying.GetBullying
import com.ufms.mediadorpedagogico.domain.interactor.calendar.GetCalendar
import com.ufms.mediadorpedagogico.domain.interactor.guild.GetGuild
import com.ufms.mediadorpedagogico.domain.interactor.help.HelpManager
import com.ufms.mediadorpedagogico.domain.interactor.homework.GetHomework
import com.ufms.mediadorpedagogico.domain.interactor.library.GetLibResources
import com.ufms.mediadorpedagogico.domain.interactor.library.GetTopic
import com.ufms.mediadorpedagogico.domain.interactor.news.GetNews
import com.ufms.mediadorpedagogico.domain.interactor.notice.GetNotice
import com.ufms.mediadorpedagogico.domain.interactor.notification.ManageNews
import com.ufms.mediadorpedagogico.domain.interactor.notification.ManageNotices
import com.ufms.mediadorpedagogico.domain.interactor.teacher.GetTeacher
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import com.ufms.mediadorpedagogico.domain.interactor.user.SignIn
import org.koin.dsl.module

val interactorModule = module {
    single { GetPersistedUser(get()) }
    single { SignIn(get()) }
    single { GetHomework(get(), get()) }
    single { GetNotice(get()) }
    single { GetNews(get()) }
    single { GetBullying(get()) }
    single { GetGuild(get()) }
    single { GetAbout(get()) }
    single { GetCalendar(get()) }
    single { GetTopic(get()) }
    single { GetLibResources(get()) }
    single { ManageNews(get()) }
    single { ManageNotices(get()) }
    single { HelpManager(get()) }
    single { GetTeacher(get()) }
}