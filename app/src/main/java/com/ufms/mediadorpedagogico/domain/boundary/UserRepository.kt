package com.ufms.mediadorpedagogico.domain.boundary

import com.ufms.mediadorpedagogico.domain.entity.User
import io.reactivex.Single

interface UserRepository {
    companion object {
        const val CURRENT_USER = "CURRENT_USER"
    }

    fun signIn(classGroup: String, name: String): Single<User>
    fun cacheUser(user: User)
}