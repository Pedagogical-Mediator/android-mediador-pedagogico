package com.ufms.mediadorpedagogico.presentation.signup

import android.content.Context
import android.content.Intent
import com.ufms.mediadorpedagogico.presentation.structure.navigation.NavData

class SignUpNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return SignUpActivity.createIntent(context)
    }
}