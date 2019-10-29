package com.ufms.mediadorpedagogico.bullying

import com.ufms.mediadorpedagogico.domain.interactor.bullying.GetBullying
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

class BullyingTests : KoinTest {

    val getBullying by inject<GetBullying>()

    @BeforeEach
    fun before() {
        startKoin {
            modules(listOf(interactorModule, repositoryModule, applicationModule, viewModelModule))
        }
    }

    @Test
    fun getAbout() {
        val bullying = getBullying.execute().blockingGet()
        assertNotNull(bullying)
        assertNotNull(bullying.id)
        assertNotNull(bullying.description)
        assertNotNull(bullying.image)
        assertNotNull(bullying.link)
    }

    @AfterEach
    fun after() {
        stopKoin()
    }
}
