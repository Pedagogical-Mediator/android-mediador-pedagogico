package com.ufms.mediadorpedagogico.notice

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.ufms.mediadorpedagogico.domain.interactor.notice.GetNotice
import com.ufms.mediadorpedagogico.presentation.AppTemplateSlimApplication
import com.ufms.mediadorpedagogico.presentation.main.dashboard.DashboardActivity
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.applicationModule
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.interactorModule
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.repositoryModule
import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.viewModelModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = AppTemplateSlimApplication::class, sdk = [28])
class NoticeRoboletric {

//    val app: AppTemplateSlimApplication = ApplicationProvider.getApplicationContext()
//    val getNotice by inject<GetNotice>()

    @Test
    fun testServiceStatus_isFetched() {
        val scenario = ActivityScenario.launch(DashboardActivity::class.java)
//        onScreen<MainScreen> {
//            status {
//                hasText("This is a good service")
//            }
//        }

        // WHEN
        scenario.moveToState(Lifecycle.State.CREATED)

        // THEN
        scenario.onActivity { activity ->
            assertThat(activity.onNextTitle("")).isNull()
        }
    }

    @BeforeEach
    fun before() {
        startKoin {
            modules(listOf(interactorModule, repositoryModule, applicationModule, viewModelModule))
        }
    }

    @AfterEach
    fun after() {
        stopKoin()
    }
}