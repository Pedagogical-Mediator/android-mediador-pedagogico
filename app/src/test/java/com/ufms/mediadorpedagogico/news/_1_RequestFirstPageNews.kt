package com.ufms.mediadorpedagogico.news

import com.ufms.mediadorpedagogico.domain.interactor.news.GetNews
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

class _1_RequestFirstPageNews : KoinTest {

    val getNews by inject<GetNews>()

    @BeforeEach
    fun before() {
        startKoin {
            modules(listOf(interactorModule, repositoryModule, applicationModule, viewModelModule))
        }
    }

    @Test
    fun getNews() {
        val news = getNews.execute(0).blockingGet()
        assertNotNull(news)
        assertNotNull(news.content?.get(0)?.id)
    }

    @AfterEach
    fun after() {
        stopKoin()
    }
}
