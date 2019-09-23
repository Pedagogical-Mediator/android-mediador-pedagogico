package com.ufms.mediadorpedagogico.data.remote.repository

import com.ufms.mediadorpedagogico.data.remote.client.ApiClient
import com.ufms.mediadorpedagogico.data.remote.entity.ApiUser
import com.ufms.mediadorpedagogico.domain.boundary.UserRepository
import com.ufms.mediadorpedagogico.domain.boundary.resources.Cache
import com.ufms.mediadorpedagogico.domain.entity.User
import io.reactivex.Single

class DefaultUserRepository(private val cache: Cache) : UserRepository {
    override fun signIn(classGroup: String, name: String): Single<User> {
        return ApiClient.signIn(classGroup, name).map(ApiUser.ApiUserToUserMapper::transform)
    }

    override fun cacheUser(user: User) {
        cache.set(UserRepository.CURRENT_USER, user)
    }
}