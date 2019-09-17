package com.ufms.mediadorpedagogico.homework

import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import com.ufms.mediadorpedagogico.R
import junit.framework.AssertionFailedError
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
open class _3_NoContentOnAPI : HomeworkInstrumental() {

    @Before
    fun setNoInternet() {
        val wifiManager = activityRule.activity.getSystemService(WIFI_SERVICE) as WifiManager
        wifiManager.isWifiEnabled = false
    }

    @Test
    fun noContentOnAPI() {
        try {
            Thread.sleep(1000)
            onView(withText(activityRule.activity.getString(R.string.global_try_again))).check(matches(isDisplayed()))
            Assert.assertTrue(true)
        } catch (e: AssertionFailedError) {
            Assert.assertTrue(false)
        }
    }

    @After
    fun setInternet() {
        val wifiManager = activityRule.activity.getSystemService(WIFI_SERVICE) as WifiManager
        wifiManager.isWifiEnabled = true
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
