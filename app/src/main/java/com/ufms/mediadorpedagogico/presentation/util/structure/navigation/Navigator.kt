package com.ufms.mediadorpedagogico.presentation.util.structure.navigation

import android.content.Context
import com.ufms.mediadorpedagogico.presentation.dashboard.MainActivity
import com.ufms.mediadorpedagogico.presentation.login.LoginActivity
import com.ufms.mediadorpedagogico.presentation.util.extensions.shouldClearTask

object Navigator {

    fun goTo(context: Context?, navData: NavData) {
        context?.let {
            val intent = navData.createIntent(it)
            it.startActivity(intent)
        }
    }

    fun goToMain(context: Context, clearTask: Boolean = false) {
        context.startActivity(MainActivity.createIntent(context).apply { shouldClearTask(clearTask) })
    }

    fun goToLogin(context: Context, clearTask: Boolean = false) {
        context.startActivity(LoginActivity.createIntent(context).apply { shouldClearTask(clearTask) })
    }

}