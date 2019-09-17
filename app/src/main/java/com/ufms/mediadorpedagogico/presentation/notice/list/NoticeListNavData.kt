package com.ufms.mediadorpedagogico.presentation.notice.list

import android.content.Context
import android.content.Intent
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData

class NoticeListNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return NoticeListActivity.createIntent(context)
    }
}