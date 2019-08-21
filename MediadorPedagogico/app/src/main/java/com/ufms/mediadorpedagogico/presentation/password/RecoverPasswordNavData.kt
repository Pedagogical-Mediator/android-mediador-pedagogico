package com.ufms.mediadorpedagogico.presentation.password

import android.content.Context
import android.content.Intent
import com.ufms.mediadorpedagogico.presentation.structure.navigation.NavData

class RecoverPasswordNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return RecoverPasswordActivity.createIntent(context)
    }
}