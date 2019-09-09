package com.ufms.mediadorpedagogico.presentation.util.resources

import android.content.Context
import com.ufms.mediadorpedagogico.domain.boundary.resources.Cache
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.Navigator

class LoginAction constructor(
    private val context: Context,
    private val cache: Cache
) {
    fun execute() {
        cache.clear()
        Navigator.goToLogin(context, true)
    }
}
