package com.ufms.mediadorpedagogico.notice

import android.content.Context
import android.net.wifi.WifiManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.presentation.notice.list.NoticeListViewHolder
import org.hamcrest.CoreMatchers.containsString
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
open class _2_CheckItemBelow10 : NoticeInstrumental() {

    @Test
    fun listHasLessThan10Items() {
        for (i in 1..3) {
            Thread.sleep(3000)
            val noticeList = getNoticeList()
            if (noticeList.size > 0) {
                val item = noticeList[0]
                onView(withId(R.id.recycler_view_notice))
                    .perform(
                        RecyclerViewActions
                            .actionOnItemAtPosition<NoticeListViewHolder>(0, click())
                    )
                Thread.sleep(1000)
                onView(withId(R.id.text_view_title)).check(matches(withText(containsString(item.title))))
                onView(withId(R.id.text_view_description)).check(matches(withText(containsString(item.description))))
                break
            }
        }
    }

    @After
    fun setNoInternet() {
        val wifiManager = activityRule.activity.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiManager.isWifiEnabled = false
    }
}
