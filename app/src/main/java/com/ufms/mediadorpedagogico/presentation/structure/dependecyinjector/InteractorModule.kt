package com.ufms.mediadorpedagogico.presentation.structure.dependecyinjector

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
}