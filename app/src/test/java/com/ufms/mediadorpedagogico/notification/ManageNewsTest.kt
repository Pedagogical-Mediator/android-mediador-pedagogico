package com.ufms.mediadorpedagogico.notification

import com.ufms.mediadorpedagogico.data.firebase.FirebaseMessagingServiceHandler
import com.ufms.mediadorpedagogico.domain.boundary.resources.Cache
import com.ufms.mediadorpedagogico.domain.interactor.notification.ManageNews
import junit.framework.Assert.assertNull
import junit.framework.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ManageNewsTest {

    private lateinit var cache: Cache

    @Before
    fun init() {
        cache = mock(Cache::class.java)
    }

    @Test
    fun executeSuccess() {
        `when`(
            cache.get<Boolean>(
                FirebaseMessagingServiceHandler.KEY_TOPIC_NEWS,
                Boolean::class.java
            )
        )
            .thenReturn(true)
        val isSubscribed = ManageNews(cache).isSubscribed()
        isSubscribed?.run { assert(this) } ?: fail("Should be true")
    }

    @Test
    fun executeFail() {
        assertNull(ManageNews(cache).isSubscribed())
    }
}
