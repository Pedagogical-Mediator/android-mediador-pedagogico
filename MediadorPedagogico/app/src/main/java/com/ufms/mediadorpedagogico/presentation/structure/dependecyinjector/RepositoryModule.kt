package com.ufms.mediadorpedagogico.presentation.structure.dependecyinjector

import com.ufms.mediadorpedagogico.data.remote.repository.DefaultUserRepository
import com.ufms.mediadorpedagogico.domain.boundary.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository> { DefaultUserRepository(get()) }
}