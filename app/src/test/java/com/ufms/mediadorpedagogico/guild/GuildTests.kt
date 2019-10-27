package com.ufms.mediadorpedagogico.guild

import com.ufms.mediadorpedagogico.domain.interactor.guild.GetGuild
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

class GuildTests : KoinTest {

    val getGuild by inject<GetGuild>()

    @BeforeEach
    fun before() {
        startKoin {
            modules(listOf(interactorModule, repositoryModule, applicationModule, viewModelModule))
        }
    }

    @Test
    fun getGuild() {
        val guild = getGuild.execute().blockingGet()
        assertNotNull(guild)
        assertNotNull(guild.id)
        assertNotNull(guild.description)
        assertNotNull(guild.image)
        assertNotNull(guild.link)
    }

    @AfterEach
    fun after() {
        stopKoin()
    }
}
