package com.ufms.mediadorpedagogico.user

import com.ufms.mediadorpedagogico.MocksRepository.returnMockedUserRepository
import com.ufms.mediadorpedagogico.MocksEntities.mockedUser
import com.ufms.mediadorpedagogico.domain.interactor.user.SignIn
import org.junit.Test
import org.junit.jupiter.api.fail
import java.lang.NullPointerException

/**
 * Unit tests for [LogIn]
 * */
class LoginTest {

    @Test
    fun executeSuccessful() {
        val logIn = SignIn(returnMockedUserRepository())
        val result = logIn.default("Abacaxi", "Aluno").blockingGet()
        assert(result == mockedUser)
    }

    @Test
    fun executeFailure() {
        val login = SignIn(returnMockedUserRepository())
        try {
            login.default("Sem abacaxi", "Aluno").blockingGet()
            fail(Throwable("Should have happened a NullPointerException"))
        } catch (e: NullPointerException) {}
    }
}
