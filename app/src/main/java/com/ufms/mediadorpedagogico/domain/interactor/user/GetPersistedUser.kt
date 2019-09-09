package com.ufms.mediadorpedagogico.domain.interactor.user

import com.ufms.mediadorpedagogico.domain.boundary.UserRepository
import com.ufms.mediadorpedagogico.domain.boundary.resources.Cache
import com.ufms.mediadorpedagogico.domain.entity.User

class GetPersistedUser(
    private val cache: Cache
) {
    fun execute(): User? {
        return try {
            cache.get(UserRepository.CURRENT_USER, User::class.java) as User
        } catch (t: Throwable) {
            null
        }
    }
}