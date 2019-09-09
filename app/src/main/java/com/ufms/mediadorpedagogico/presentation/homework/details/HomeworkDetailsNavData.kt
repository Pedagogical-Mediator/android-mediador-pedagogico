package com.ufms.mediadorpedagogico.presentation.homework.details

import android.content.Context
import android.content.Intent
import com.ufms.mediadorpedagogico.domain.entity.homework.Homework
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData

class HomeworkDetailsNavData(
    val homework: Homework
) : NavData {
    override fun createIntent(context: Context): Intent {
        return HomeworkDetailsActivity.createIntent(context, homework)
    }
}