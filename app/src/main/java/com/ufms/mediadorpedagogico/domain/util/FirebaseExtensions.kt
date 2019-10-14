package com.ufms.mediadorpedagogico.domain.util

import com.google.firebase.messaging.FirebaseMessaging

fun subscriberHandler(topic: String, shouldSubscribe: Boolean = true) {
    if (shouldSubscribe) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
    } else {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic)
    }
}