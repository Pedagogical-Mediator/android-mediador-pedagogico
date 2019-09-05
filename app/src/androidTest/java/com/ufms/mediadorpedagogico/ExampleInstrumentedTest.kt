package com.ufms.mediadorpedagogico

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.ufms.mediadorpedagogico.presentation.homework.list.HomeworkListActivity
import com.ufms.mediadorpedagogico.presentation.homework.list.HomeworkListViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(HomeworkListActivity::class.java)

    @Test
    fun listHasItems () {
        Thread.sleep(5000)
        onView(withId(R.id.recycler_view_homework)).perform(
            RecyclerViewActions.actionOnItemAtPosition<HomeworkListViewHolder>(
                0,
                click()
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun listHasMoreThan10Items () {
        //TODO continuar depois de carregamento dinâmico funcionar
        var counter = 0
        if (counter < 5) {
            try {
                Thread.sleep(1000)
                onView(withId(R.id.recycler_view_homework)).perform(
                    RecyclerViewActions.actionOnItemAtPosition<HomeworkListViewHolder>(
                        0,
                        click()
                    )
                ).check(matches(isDisplayed()))
            } catch (e: Exception) {
                counter++
            }
        }
    }

    private fun getRVcount(): Int {
        val recyclerView = activityRule.activity.findViewById(R.id.recycler_view_homework) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }
}
