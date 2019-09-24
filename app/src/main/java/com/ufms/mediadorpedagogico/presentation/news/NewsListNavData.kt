package com.ufms.mediadorpedagogico.presentation.news

import android.content.Context
import android.content.Intent
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData

class NewsListNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return NewsListActivity.createIntent(context)
    }
}