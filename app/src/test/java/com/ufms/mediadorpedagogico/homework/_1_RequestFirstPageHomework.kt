package com.ufms.mediadorpedagogico.homework

import com.ufms.mediadorpedagogico.domain.interactor.homework.GetHomework
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import com.ufms.mediadorpedagogico.domain.interactor.user.SignIn
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.applicationModule
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.interactorModule
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.repositoryModule
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.viewModelModule
import junit.framework.Assert.assertNotNull
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito

class _1_RequestFirstPageHomework : KoinTest {

    val getHomework by inject<GetHomework>()

    @BeforeEach
    fun before() {
        val mockedPersistedUser = Mockito.mock(GetPersistedUser::class.java)
        Mockito.`when`(mockedPersistedUser.getUserId())
            .thenReturn(3)
        val interactorTestModule = module {
            single { SignIn(get()) }
            single { GetHomework(get(), mockedPersistedUser) }
        }
        startKoin {
            modules(listOf(interactorTestModule, repositoryModule, applicationModule, viewModelModule))
        }
    }

    @Test
    fun getHomework() {
        val homework = getHomework.execute(0, "terceiro").blockingGet()
        assertNotNull(homework)
        assertNotNull(homework.content?.get(0)?.id)
    }

    @AfterEach
    fun after() {
        stopKoin()
    }
}
