package com.ufms.mediadorpedagogico.domain.interactor.help

import com.ufms.mediadorpedagogico.domain.boundary.resources.Cache
import java.lang.Exception

class HelpManager (
    private val cache: Cache
) {

    internal fun isFirstTime(): Boolean {
        return try {
            cache.get(TOUR, Boolean::class.java)
        } catch (e: Exception) {
            false
        }
    }

    internal fun tourWasPlayed() {
        cache.set(TOUR, true)
    }

    companion object {
        private const val TOUR = "TOUR"
    }
}