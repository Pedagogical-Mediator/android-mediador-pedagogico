package com.ufms.mediadorpedagogico

import com.ufms.mediadorpedagogico.UserUtilsTest.mockedUser
import com.ufms.mediadorpedagogico.domain.boundary.UserRepository
import com.ufms.mediadorpedagogico.domain.interactor.user.SignIn
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.NullPointerException

/**
 * Unit tests for [LogIn]
 * */
class LoginTest {

    @Test
    fun executeSuccessful() {
        val logIn = SignIn(returnMockedRepository())
        val result = logIn.default("Abacaxi", "Aluno").blockingGet()
        assert(result == mockedUser)
    }

    @Test
    fun executeFailure() {
        val login = SignIn(returnMockedRepository())
        try {
            login.default("Sem abacaxi", "Aluno").blockingGet()
            fail(Throwable("Should have happened a NullPointerException"))
        } catch (e: NullPointerException) {}
    }

    private fun returnMockedRepository(): UserRepository {
        val mockRepository = mock(UserRepository::class.java)
        `when`(mockRepository.signIn("Abacaxi", "Aluno")).thenReturn(Single.just(mockedUser))
        return mockRepository
    }
}
