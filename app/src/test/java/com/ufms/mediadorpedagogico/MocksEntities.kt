package com.ufms.mediadorpedagogico

import com.ufms.mediadorpedagogico.domain.boundary.resources.Cache
import com.ufms.mediadorpedagogico.domain.entity.About
import com.ufms.mediadorpedagogico.domain.entity.User
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
import org.mockito.Mockito

object MocksEntities {

    val mockedUser = User(
        id = "1",
        name = "Arthur Thomas Mário Martins",
        token = "Abacaxi",
        classKey = "Abacaxi"
    )

    val mockedAbout = About(
        id = 1,
        description = "SobreSobreSobreSobreSobreSobreSobreSobre",
        image = "BASE64",
        link = "google.com"
    )
}

/**
 * TODO testes
 * TODO testes
 * TODO testes
 * TODO testes
 *
 * Library
 *
 *
 * */