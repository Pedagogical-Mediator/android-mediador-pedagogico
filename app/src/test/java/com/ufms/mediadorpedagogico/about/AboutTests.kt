package com.ufms.mediadorpedagogico.about

import com.ufms.mediadorpedagogico.domain.interactor.about.GetAbout
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

class AboutTests : KoinTest {

    val getAbout by inject<GetAbout>()

    @BeforeEach
    fun before() {
        startKoin {
            modules(listOf(interactorModule, repositoryModule, applicationModule, viewModelModule))
        }
    }

    @Test
    fun getAbout() {
        val about = getAbout.execute().blockingGet()
        assertNotNull(about)
        assertNotNull(about.id)
        assertNotNull(about.description)
        assertNotNull(about.image)
        assertNotNull(about.link)
    }

    @AfterEach
    fun after() {
        stopKoin()
    }
}
