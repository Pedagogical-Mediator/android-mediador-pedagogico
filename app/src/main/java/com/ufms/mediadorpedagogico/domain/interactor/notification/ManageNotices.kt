package com.ufms.mediadorpedagogico.domain.interactor.notification

import com.ufms.mediadorpedagogico.data.firebase.FirebaseMessagingServiceHandler.Companion.KEY_TOPIC_NOTICES
import com.ufms.mediadorpedagogico.domain.boundary.resources.Cache

class ManageNotices(
    private val cache: Cache
) {
    fun isSubscribed(): Boolean? {
        return try {
            cache.get(KEY_TOPIC_NOTICES, Boolean::class.java)
        } catch (t: Throwable) {
            null
        }
    }

    fun subscribe(shouldSubscribe: Boolean) {
        cache.set(KEY_TOPIC_NOTICES, shouldSubscribe)
    }
}