package com.ufms.mediadorpedagogico.presentation.util.structure.dependecyinjector

import com.ufms.mediadorpedagogico.domain.interactor.homework.GetHomework
import com.ufms.mediadorpedagogico.domain.interactor.notice.GetNotice
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import com.ufms.mediadorpedagogico.domain.interactor.user.RecoverPassword
import com.ufms.mediadorpedagogico.domain.interactor.user.SignIn
import com.ufms.mediadorpedagogico.domain.interactor.user.SignUp
import org.koin.dsl.module

val interactorModule = module {
    single { GetPersistedUser(get()) }
    single { SignIn(get()) }
    single { SignUp(get()) }
    single { RecoverPassword(get()) }
    single { GetHomework(get()) }
    single { GetNotice(get()) }
}