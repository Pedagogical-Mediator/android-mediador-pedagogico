package com.ufms.mediadorpedagogico.presentation.homework

import android.content.Context
import android.content.Intent
import com.ufms.mediadorpedagogico.presentation.structure.navigation.NavData

class HomeworkNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return HomeworkActivity.createIntent(context)
    }
}