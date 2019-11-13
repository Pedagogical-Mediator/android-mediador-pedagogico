package com.ufms.mediadorpedagogico.calendar

import com.ufms.mediadorpedagogico.domain.interactor.calendar.GetCalendar
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

class CalendarTests : KoinTest {

    val getCalendar by inject<GetCalendar>()

    @BeforeEach
    fun before() {
        startKoin {
            modules(listOf(interactorModule, repositoryModule, applicationModule, viewModelModule))
        }
    }

    @Test
    fun getAbout() {
        val calendar = getCalendar.execute().blockingGet()
        Assert.assertNotNull(calendar)
        Assert.assertNotNull(calendar.id)
        Assert.assertNotNull(calendar.link)
    }

    @AfterEach
    fun after() {
        stopKoin()
    }
}
