package com.ufms.mediadorpedagogico.notice

import com.ufms.mediadorpedagogico.domain.interactor.notice.GetNotice
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

class _2_RequestSecondPageNotices : KoinTest {

    val getNotices by inject<GetNotice>()

    @BeforeEach
    fun before() {
        startKoin {
            modules(listOf(interactorModule, repositoryModule, applicationModule, viewModelModule))
        }
    }

    @Test
    fun getNotices() {
        val notices = getNotices.execute(1).blockingGet()
        notices?.content?.isNotEmpty()?.let {
            if (it) {
                assertNotNull(notices.content?.get(0)?.id)
            }
        }
    }

    @AfterEach
    fun after() {
        stopKoin()
    }
}
