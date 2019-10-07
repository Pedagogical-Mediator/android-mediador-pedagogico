package com.ufms.mediadorpedagogico.news

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import com.ufms.mediadorpedagogico.presentation.news.NewsListFragment
import com.ufms.mediadorpedagogico.presentation.news.NewsListAdapter
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
open class NewsInstrumental : AndroidJUnitRunner() {

    @get:Rule
    var activityRule: ActivityTestRule<NewsListFragment> =
        object : ActivityTestRule<NewsListFragment>(NewsListFragment::class.java) {

            override fun afterActivityLaunched() {
                super.afterActivityLaunched()
                activity.hideSoftKeyboard()
            }
        }

    protected fun getNewsList() =
        (retrieveBinding().recyclerViewNews.adapter as NewsListAdapter).listGet

    protected fun retrieveBinding() = activityRule.activity.binding
}
