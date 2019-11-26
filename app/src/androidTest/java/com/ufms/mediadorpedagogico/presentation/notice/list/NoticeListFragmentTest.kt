package com.ufms.mediadorpedagogico.presentation.notice.list

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.intent.Intents.intended
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.domain.interactor.calendar.GetCalendar
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.*
import com.ufms.mediadorpedagogico.utils.mockedRepositoryModule
import com.ufms.mediadorpedagogico.utils.startMockedKoin
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.*
import org.koin.test.KoinTest
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
@LargeTest
class NoticeListFragmentTest : KoinTest {

    @Before
    fun before() {
        if(GlobalContext.getOrNull() != null)  {
            stopKoin()
        }
        startMockedKoin()
    }

    @Test
    fun openList() {
        val scenario = launchFragmentInContainer<NoticeListFragment>(null, R.style.AppTheme)
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
    }

    @Test
    fun openList2() {
        val scenario = launchFragmentInContainer<NoticeListFragment>(null, R.style.AppTheme)
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
    }

    @After
    fun after() {
        stopKoin()
    }
}