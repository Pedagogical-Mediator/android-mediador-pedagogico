package com.ufms.mediadorpedagogico.about

import com.ufms.mediadorpedagogico.MocksEntities.mockedAbout
import com.ufms.mediadorpedagogico.domain.boundary.AboutRepository
import com.ufms.mediadorpedagogico.domain.interactor.about.GetAbout
import io.reactivex.Single
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GetAboutTest {

    private lateinit var mockedRepository: AboutRepository

    @Before
    fun init() {
        mockedRepository = mock(AboutRepository::class.java)
    }

    @Test
    fun executeSuccess() {
        `when`(mockedRepository.getAboutInformation()).thenReturn(Single.just(mockedAbout))
        val about = GetAbout(mockedRepository).execute().blockingGet()
        assert(about == mockedAbout)
    }

    @Test
    fun executeFail() {
        val about = GetAbout(mockedRepository).execute().blockingGet()
        assertNull(about)
    }
}
