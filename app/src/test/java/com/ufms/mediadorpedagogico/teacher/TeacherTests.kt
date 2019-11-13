package com.ufms.mediadorpedagogico.teacher

import com.ufms.mediadorpedagogico.domain.interactor.teacher.GetTeacher
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

class TeacherTests : KoinTest {

    val getTeacher by inject<GetTeacher>()

    @BeforeEach
    fun before() {
        startKoin {
            modules(listOf(interactorModule, repositoryModule, applicationModule, viewModelModule))
        }
    }

    @Test
    fun getAbout() {
        val teachers = getTeacher.executeList().blockingGet()
        Assert.assertNotNull(teachers)
        Assert.assertNotNull(teachers[0].id)
        Assert.assertNotNull(teachers[0].name)
    }

    @AfterEach
    fun after() {
        stopKoin()
    }
}
