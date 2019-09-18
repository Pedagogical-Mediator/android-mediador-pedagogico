package com.ufms.mediadorpedagogico.domain.interactor.user

import com.ufms.mediadorpedagogico.domain.boundary.UserRepository
import com.ufms.mediadorpedagogico.domain.entity.User
import io.reactivex.Single

class SignIn(private val repository: UserRepository) {

    fun default(classKey: String, name: String): Single<User> {
        return Single.just(FormFields().withClassKey(classKey).withName(name))
            .doOnSuccess { formFields -> if (!formFields.isValid) throw formFields.exception }
            .flatMap { repository.signIn(classKey, name) }
            .doAfterSuccess { repository.cacheUser(it) }
    }
}