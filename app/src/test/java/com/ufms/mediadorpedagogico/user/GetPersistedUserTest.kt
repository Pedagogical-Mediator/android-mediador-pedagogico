package com.ufms.mediadorpedagogico.user

import com.ufms.mediadorpedagogico.MocksEntities.mockedUser
import com.ufms.mediadorpedagogico.domain.boundary.UserRepository.Companion.CURRENT_USER
import com.ufms.mediadorpedagogico.domain.boundary.resources.Cache
import com.ufms.mediadorpedagogico.domain.entity.User
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GetPersistedUserTest {

    private lateinit var mockedCache: Cache

    @Before
    fun init() {
        mockedCache = mock(Cache::class.java)
    }

    @Test
    fun executeSuccess() {
        `when`(mockedCache.get<User>(CURRENT_USER, User::class.java)).thenReturn(mockedUser)
        val getPersistedUser = GetPersistedUser(mockedCache)
        assert(getPersistedUser.execute() == mockedUser)
    }

    @Test
    fun executeFail() {
        val getPersistedUser = GetPersistedUser(mockedCache)
        assert(getPersistedUser.execute() == null)
    }
}
