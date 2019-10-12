package com.ufms.mediadorpedagogico.domain.interactor.notification

import com.ufms.mediadorpedagogico.data.firebase.FirebaseMessagingServiceHandler.Companion.KEY_TOPIC_NEWS
import com.ufms.mediadorpedagogico.domain.boundary.resources.Cache

class ManageNews(
    private val cache: Cache
) {
    fun isSubscribed(): Boolean? {
        return try {
            cache.get(KEY_TOPIC_NEWS, Boolean::class.java)
        } catch (t: Throwable) {
            null
        }
    }

    fun subscribe() {
        cache.set(KEY_TOPIC_NEWS, true)
    }
}