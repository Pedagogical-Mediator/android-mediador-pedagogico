package com.ufms.mediadorpedagogico.domain.util

import com.google.firebase.messaging.FirebaseMessaging

fun subscribeToTopic(topic: String) {
    FirebaseMessaging.getInstance().subscribeToTopic(topic)
}