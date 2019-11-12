//package com.ufms.mediadorpedagogico.extensions
//
//import com.ufms.mediadorpedagogico.MocksEntities
//import com.ufms.mediadorpedagogico.domain.boundary.AboutRepository
//import com.ufms.mediadorpedagogico.domain.interactor.about.GetAbout
//import io.reactivex.Single
//import junit.framework.Assert
//import org.junit.Before
//import org.junit.Test
//import org.mockito.Mockito
//
//class StringTests {
//
//    @Test
//    fun executeSuccess() {
//        Mockito.`when`(mockedRepository.getAboutInformation())
//            .thenReturn(Single.just(MocksEntities.mockedAbout))
//        val about = GetAbout(mockedRepository).execute().blockingGet()
//        assert(about == MocksEntities.mockedAbout)
//    }
//}
