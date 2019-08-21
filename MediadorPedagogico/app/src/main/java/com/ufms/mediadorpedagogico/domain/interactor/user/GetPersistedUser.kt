package com.ufms.mediadorpedagogico.domain.interactor.user

import com.ufms.mediadorpedagogico.domain.boundary.UserRepository
import com.ufms.mediadorpedagogico.domain.entity.User
import com.ufms.mediadorpedagogico.presentation.structure.sl.ServiceLocator

class GetPersistedUser {
    fun execute(): User? {
        return try {
            ServiceLocator.getInstance()?.run {
                cache.get(UserRepository.CURRENT_USER, User::class.java) as User
            }
        } catch (t: Throwable) {
            null
        }
    }
}