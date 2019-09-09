package com.ufms.mediadorpedagogico.domain.interactor.user

import com.ufms.mediadorpedagogico.domain.boundary.UserRepository
import com.ufms.mediadorpedagogico.domain.entity.User
import io.reactivex.Single

class SignIn(private val repository: UserRepository) {

    fun default(email: String, password: String, token: String?): Single<User> {
        return Single.just(FormFields().withEmail(email).withPassword(password))
            .doOnSuccess { formFields -> if (!formFields.isValid) throw formFields.exception }
            .flatMap { repository.signIn(email, password, token) }
            .doAfterSuccess { repository.cacheUser(it) }
    }
}