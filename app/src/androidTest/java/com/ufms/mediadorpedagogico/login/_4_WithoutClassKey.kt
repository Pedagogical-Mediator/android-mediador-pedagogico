package com.ufms.mediadorpedagogico.login

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import com.ufms.mediadorpedagogico.R
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
open class _4_WithoutClassKey : LoginInstrumental() {

    @Test
    fun withoutClassKey() {
        // Preparing
        Thread.sleep(500)
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.text_input_name))
            .perform(
                typeText("this name doesnt matter")
            )
        Espresso.closeSoftKeyboard()

        onView(withId(R.id.submit_button))
            .perform(click())
        Thread.sleep(5000)

        // Checking
        onView(withText(_activity().getString(R.string.error_invalid_fields)))
            .check(
                matches(isDisplayed())
            )
    }
}
