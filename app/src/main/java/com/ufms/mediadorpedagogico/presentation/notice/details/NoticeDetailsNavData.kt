package com.ufms.mediadorpedagogico.presentation.notice.details

import android.content.Context
import android.content.Intent
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData

class NoticeDetailsNavData(
    val notice: Notice
) : NavData {
    override fun createIntent(context: Context): Intent {
        return NoticeDetailsActivity.createIntent(context, notice)
    }
}