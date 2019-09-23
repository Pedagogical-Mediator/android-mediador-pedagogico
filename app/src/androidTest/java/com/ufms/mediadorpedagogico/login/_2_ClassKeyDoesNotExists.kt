package com.ufms.mediadorpedagogico.login

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import com.ufms.mediadorpedagogico.R
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
open class _2_ClassKeyDoesNotExists : LoginInstrumental() {
    @Test
    fun classKeyDoesNotExists() {
        sleep(500)
        onView(withId(R.id.text_input_name))
            .perform(
                typeText("Meu nome")
            )
        closeSoftKeyboard()
        sleep(500)
        onView(withId(R.id.text_input_class_code))
            .perform(
                typeText("oiev4whvnn994n key does not exists")
            )
        closeSoftKeyboard()

        onView(withId(R.id.submit_button))
            .perform(click())
        sleep(5000)
        onView(withText(_activity().getString(R.string.global_try_again)))
            .check(
                matches(isDisplayed())
            )
    }
}