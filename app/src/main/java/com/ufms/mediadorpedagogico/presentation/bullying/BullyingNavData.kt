package com.ufms.mediadorpedagogico.presentation.bullying

import android.content.Context
import android.content.Intent
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData

class BullyingNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return BullyingFragment.createIntent(context)
    }
}