package com.ufms.mediadorpedagogico.homework

import com.ufms.mediadorpedagogico.domain.interactor.homework.GetHomework
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
import org.koin.test.KoinTest
import org.koin.test.inject

class _2_RequestSecondPageHomework : KoinTest {

    val getHomework by inject<GetHomework>()

    @BeforeEach
    fun before() {
        startKoin {
            modules(listOf(interactorModule, repositoryModule, applicationModule, viewModelModule))
        }
    }

    @Test
    fun getHomework() {
        val homework = getHomework.execute(1, "Abacaxi").blockingGet()
        homework?.content?.isNotEmpty()?.let {
            if (it) {
                assertNotNull(homework.content?.get(0)?.id)
            }
        }
    }

    @AfterEach
    fun after() {
        stopKoin()
    }
}
