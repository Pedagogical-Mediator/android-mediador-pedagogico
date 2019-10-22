package com.ufms.mediadorpedagogico.presentation.login

import android.content.Context
import android.content.Intent
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData

class LoginNavData(private val shouldClearTask: Boolean) : NavData {
    override fun createIntent(context: Context): Intent {
        return LoginActivity.createIntent(context, shouldClearTask)
    }
}