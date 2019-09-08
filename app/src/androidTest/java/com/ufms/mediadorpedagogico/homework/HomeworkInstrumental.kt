package com.ufms.mediadorpedagogico.homework

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import com.ufms.mediadorpedagogico.presentation.homework.list.HomeworkListActivity
import com.ufms.mediadorpedagogico.presentation.homework.list.HomeworkListAdapter
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
open class HomeworkInstrumental : AndroidJUnitRunner() {

    @get:Rule
    var activityRule: ActivityTestRule<HomeworkListActivity> =
        object : ActivityTestRule<HomeworkListActivity>(HomeworkListActivity::class.java) {

            override fun afterActivityLaunched() {
                super.afterActivityLaunched()
                activity.hideSoftKeyboard()
            }
        }

    protected fun getHomeworkList() = (retrieveBinding().recyclerViewHomework.adapter as HomeworkListAdapter).listGet
    protected fun retrieveBinding() = activityRule.activity.binding
}
