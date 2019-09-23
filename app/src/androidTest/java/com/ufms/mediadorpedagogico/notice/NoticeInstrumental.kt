package com.ufms.mediadorpedagogico.notice

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import com.ufms.mediadorpedagogico.presentation.notice.list.NoticeListActivity
import com.ufms.mediadorpedagogico.presentation.notice.list.NoticeListAdapter
import com.ufms.mediadorpedagogico.presentation.util.extensions.hideSoftKeyboard
import org.junit.Ignore
import org.junit.Rule
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@Ignore("O Pai não precisa de testes")
open class NoticeInstrumental : AndroidJUnitRunner() {

    @get:Rule
    var activityRule: ActivityTestRule<NoticeListActivity> =
        object : ActivityTestRule<NoticeListActivity>(NoticeListActivity::class.java) {

            override fun afterActivityLaunched() {
                super.afterActivityLaunched()
                activity.hideSoftKeyboard()
            }
        }

    protected fun getNoticeList() =
        (retrieveBinding().recyclerViewNotice.adapter as NoticeListAdapter).listGet

    protected fun retrieveBinding() = activityRule.activity.binding
}
