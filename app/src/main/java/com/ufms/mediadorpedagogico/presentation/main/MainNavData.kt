package com.ufms.mediadorpedagogico.presentation.main

import android.content.Context
import android.content.Intent
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData

class MainNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return MainActivity.createIntent(context)
    }
}