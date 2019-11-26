package com.ufms.mediadorpedagogico.presentation.main

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.navigateSafe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainFragmentTest {


    @Before
    fun initValidString() {
    }

    @Test
    fun openScreen() {
        val scenario = launchFragmentInContainer<MainFragment>(null, R.style.AppTheme)
        val navController = mock(NavController::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.card_view_homework))
            .check(ViewAssertions.matches(isDisplayed()))
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.card_view_homework))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.card_view_homework))
            .perform(click())
        verify(navController).navigateSafe(MainFragmentDirections.actionMainFragmentToHomeworkListFragment())
    }
}