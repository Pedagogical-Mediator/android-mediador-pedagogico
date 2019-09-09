package com.ufms.mediadorpedagogico.homework

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.presentation.dashboard.TEST_HOMEWORK_PER_REQUEST
import com.ufms.mediadorpedagogico.presentation.homework.list.HomeworkListViewHolder
import org.hamcrest.CoreMatchers.containsString
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
open class _1_CheckItemAbove10 : HomeworkInstrumental() {

    @Test
    fun listHasMoreThan10Items() {
        Thread.sleep(3000)
        val homeworkList = getHomeworkList()
        if (homeworkList.size > TEST_HOMEWORK_PER_REQUEST - 1) {
            onView(withId(R.id.recycler_view_homework)).perform(
                RecyclerViewActions.scrollToPosition<HomeworkListViewHolder>(TEST_HOMEWORK_PER_REQUEST - 1)
            )
            Thread.sleep(3000)
        }
        val newHomeworkList = getHomeworkList()
        if (newHomeworkList.size > TEST_HOMEWORK_PER_REQUEST) {
            val newItem = newHomeworkList[TEST_HOMEWORK_PER_REQUEST]
            onView(withId(R.id.recycler_view_homework))
                .perform(
                    RecyclerViewActions
                        .actionOnItemAtPosition<HomeworkListViewHolder>(TEST_HOMEWORK_PER_REQUEST, click())
                )
            Thread.sleep(1000)
            onView(withId(R.id.text_view_title)).check(matches(withText(containsString(newItem.title))))
            onView(withId(R.id.text_view_description)).check(matches(withText(containsString(newItem.description))))
        }
    }
}
