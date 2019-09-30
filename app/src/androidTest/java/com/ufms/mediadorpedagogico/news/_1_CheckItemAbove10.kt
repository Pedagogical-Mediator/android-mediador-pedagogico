package com.ufms.mediadorpedagogico.news

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.presentation.news.NewsListViewHolder
import com.ufms.mediadorpedagogico.utils.TEST_NEWS_PER_REQUEST
import org.hamcrest.CoreMatchers.containsString
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
open class _1_CheckItemAbove10 : NewsInstrumental() {

    @Test
    fun listHasMoreThan10Items() {
        Thread.sleep(3000)
        val homeworkList = getNewsList()
        if (homeworkList.size > TEST_NEWS_PER_REQUEST - 1) {
            onView(withId(R.id.recycler_view_homework)).perform(
                RecyclerViewActions.scrollToPosition<NewsListViewHolder>(
                    TEST_NEWS_PER_REQUEST - 1
                )
            )
            Thread.sleep(3000)
        }
        val newNewsList = getNewsList()
        if (newNewsList.size > TEST_NEWS_PER_REQUEST) {
            val newItem = newNewsList[TEST_NEWS_PER_REQUEST]
            onView(withId(R.id.recycler_view_homework))
                .perform(
                    RecyclerViewActions
                        .actionOnItemAtPosition<NewsListViewHolder>(
                            TEST_NEWS_PER_REQUEST,
                            click()
                        )
                )
            Thread.sleep(1000)
            onView(withId(R.id.text_view_title)).check(matches(withText(containsString(newItem.title))))
            onView(withId(R.id.text_view_description)).check(matches(withText(containsString(newItem.description))))
        }
    }
}
