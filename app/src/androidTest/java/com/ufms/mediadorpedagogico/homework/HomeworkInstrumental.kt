//package com.ufms.mediadorpedagogico.homework
//
//import android.os.Bundle
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions
//import androidx.test.espresso.assertion.ViewAssertions.matches
//import androidx.test.espresso.matcher.ViewMatchers.withId
//import androidx.test.espresso.matcher.ViewMatchers.withText
//import androidx.test.rule.ActivityTestRule
//import androidx.test.runner.AndroidJUnit4
//import androidx.test.runner.AndroidJUnitRunner
//import com.ufms.mediadorpedagogico.R
//import com.ufms.mediadorpedagogico.databinding.FragmentHomeworkListBinding
//import com.ufms.mediadorpedagogico.presentation.homework.list.HomeworkListAdapter
//import com.ufms.mediadorpedagogico.presentation.homework.list.HomeworkListFragment
//import com.ufms.mediadorpedagogico.presentation.main.dashboard.DashboardActivity
//import com.ufms.mediadorpedagogico.presentation.util.extensions.hideSoftKeyboard
//import org.junit.Ignore
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
///**
// * Instrumented test, which will execute on an Android device.
// *
// * See [testing documentation](http://d.android.com/tools/testing).
// */
//@RunWith(AndroidJUnit4::class)
//@Ignore("O Pai não precisa de testes")
//open class HomeworkInstrumental : AndroidJUnitRunner() {
//
//    @get:Rule
//    var activityRule: ActivityTestRule<DashboardActivity> =
//        object : ActivityTestRule<DashboardActivity>(DashboardActivity::class.java) {
//
//            override fun afterActivityLaunched() {
//                super.afterActivityLaunched()
//                activity.hideSoftKeyboard()
//
//            }
//        }
//
//    protected fun getHomeworkList() =
//        (retrieveBinding().recyclerViewHomework.adapter as HomeworkListAdapter).listGet
//
//    protected fun retrieveBinding(): FragmentHomeworkListBinding{
//        return (activityRule.activity.supportFragmentManager.fragments[0] as HomeworkListFragment).binding
//    }
//}
//
//
