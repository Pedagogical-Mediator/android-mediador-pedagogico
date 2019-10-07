package com.ufms.mediadorpedagogico.presentation.homework.list

import android.content.Context
import android.content.Intent
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData

class HomeworkListNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return HomeworkListFragment.createIntent(context)
    }
}