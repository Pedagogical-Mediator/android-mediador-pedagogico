package com.ufms.mediadorpedagogico.library

import com.ufms.mediadorpedagogico.domain.interactor.library.GetLibResources
import com.ufms.mediadorpedagogico.domain.interactor.library.GetTopic
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.applicationModule
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.interactorModule
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.repositoryModule
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.viewModelModule
import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class LibResourcesTests : KoinTest {

    val getTopic by inject<GetTopic>()
    val getLibResourcesTests by inject<GetLibResources>()

    @BeforeEach
    fun before() {
        startKoin {
            modules(listOf(interactorModule, repositoryModule, applicationModule, viewModelModule))
        }
    }

    @Test
    fun getAbout() {
        val topics = getTopic.execute().blockingGet()
        Assert.assertNotNull(topics)
        Assert.assertNotNull(topics[0].id)
        val libResources = getLibResourcesTests.execute(0, topics.first().id!!).blockingGet()
        Assert.assertNotNull(libResources)
        Assert.assertNotNull(libResources.content)
        Assert.assertNotNull(libResources.content!![0])
    }

    @AfterEach
    fun after() {
        stopKoin()
    }
}
