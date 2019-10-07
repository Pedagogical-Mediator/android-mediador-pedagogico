package com.ufms.mediadorpedagogico.notice

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentHomeworkListBinding
import com.ufms.mediadorpedagogico.databinding.FragmentNoticeListBinding
import com.ufms.mediadorpedagogico.presentation.homework.list.HomeworkListFragment
import com.ufms.mediadorpedagogico.presentation.main.dashboard.DashboardActivity
import com.ufms.mediadorpedagogico.presentation.notice.list.NoticeListAdapter
import com.ufms.mediadorpedagogico.presentation.notice.list.NoticeListFragment
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
    var activityRule: ActivityTestRule<DashboardActivity> =
        object : ActivityTestRule<DashboardActivity>(DashboardActivity::class.java) {

            override fun afterActivityLaunched() {
                super.afterActivityLaunched()
                activity.hideSoftKeyboard()
                Espresso.onView(ViewMatchers.withId(R.id.card_view_notice)).perform(ViewActions.click())
            }
        }
    protected fun getNoticeList() =
        (retrieveBinding().recyclerViewNotice.adapter as NoticeListAdapter).listGet

    protected fun retrieveBinding(): FragmentNoticeListBinding {
        return (activityRule.activity.supportFragmentManager.fragments[0] as NoticeListFragment).binding
    }
}
