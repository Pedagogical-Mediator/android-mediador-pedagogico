package com.ufms.mediadorpedagogico

import com.ufms.mediadorpedagogico.MocksEntities.mockedAbout
import com.ufms.mediadorpedagogico.MocksEntities.mockedUser
import com.ufms.mediadorpedagogico.domain.boundary.AboutRepository
import com.ufms.mediadorpedagogico.domain.boundary.UserRepository
import io.reactivex.Single
import org.mockito.Mockito

object MocksRepository {

    fun returnMockedUserRepository(): UserRepository {
        val mockRepository = Mockito.mock(UserRepository::class.java)
        Mockito.`when`(mockRepository.signIn("Abacaxi", "Aluno")).thenReturn(Single.just(mockedUser))
        return mockRepository
    }

    fun returnMockedAboutRepository(): AboutRepository {
        val mockRepository = Mockito.mock(AboutRepository::class.java)
        Mockito.`when`(mockRepository.getAboutInformation()).thenReturn(Single.just(mockedAbout))
        return mockRepository
    }
}
