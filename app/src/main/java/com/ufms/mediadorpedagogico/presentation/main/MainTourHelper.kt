package com.ufms.mediadorpedagogico.presentation.main

import android.app.Activity
import android.view.Gravity
import android.view.animation.AlphaAnimation
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentMainBinding
import tourguide.tourguide.Pointer
import tourguide.tourguide.TourGuide

object MainTourHelper {
    fun setupMainTour(binding: FragmentMainBinding, activity: Activity, callBack: () -> Unit) {

        val enterAnimation = AlphaAnimation(0f, 1f)
            .apply {
                duration = 600
                fillAfter = true
            }
        val exitAnimation = AlphaAnimation(1f, 0f)
            .apply {
                duration = 600
                fillAfter = true
            }

        with(activity) {
            val tourGuide = TourGuide.create(this) {
                pointer { Pointer() }
                toolTip {
                    title { getString(R.string.help_notice_list_title) }
                    description { getString(R.string.help_notice_list_description) }
                    gravity { Gravity.CENTER_VERTICAL or Gravity.END }
                    mWidth = MainFragment.HELP_WIDTH
                }
                overlay {
                    setEnterAnimation(enterAnimation)
                    setExitAnimation(exitAnimation)
                }
            }

            with(binding) {
                cardViewNotice.setOnClickListener {
                    tourGuide.apply {
                        cleanUp()
                        toolTip {
                            title { getString(R.string.help_homework_list_title) }
                            description { getString(R.string.help_homework_list_description) }
                            gravity { Gravity.BOTTOM }
                            mWidth = MainFragment.HELP_WIDTH
                        }
                    }.playOn(cardViewHomework)
                }

                cardViewHomework.setOnClickListener {
                    tourGuide.apply {
                        cleanUp()
                        toolTip {
                            title { getString(R.string.help_news_list_title) }
                            description { getString(R.string.help_news_list_description) }
                            gravity { Gravity.CENTER_VERTICAL or Gravity.START }
                            mWidth = MainFragment.HELP_WIDTH
                        }
                    }.playOn(cardViewNews)
                }

                cardViewNews.setOnClickListener {
                    tourGuide.apply {
                        cleanUp()
                        toolTip {
                            title { getString(R.string.help_calendar_title) }
                            description { getString(R.string.help_calendar_description) }
                            gravity { Gravity.END or Gravity.CENTER_VERTICAL }
                            mWidth = MainFragment.HELP_WIDTH
                        }
                    }.playOn(cardViewCalendar)
                }

                cardViewCalendar.setOnClickListener {
                    tourGuide.apply {
                        cleanUp()
                        toolTip {
                            title { getString(R.string.help_bullying_title) }
                            description { getString(R.string.help_bullying_description) }
                            gravity { Gravity.CENTER_VERTICAL }
                            mWidth = MainFragment.HELP_WIDTH
                        }
                    }.playOn(cardViewBullying)
                }

                cardViewBullying.setOnClickListener {
                    tourGuide.apply {
                        cleanUp()
                        toolTip {
                            title { getString(R.string.help_guild_title) }
                            description { getString(R.string.help_guild_description) }
                            gravity { Gravity.CENTER_VERTICAL or Gravity.START }
                            mWidth = MainFragment.HELP_WIDTH
                        }
                    }.playOn(cardViewGuild)
                }

                cardViewGuild.setOnClickListener {
                    tourGuide.apply {
                        cleanUp()
                        toolTip {
                            title { getString(R.string.library_topics_title) }
                            description { getString(R.string.library_topics_description) }
                            gravity { Gravity.TOP or Gravity.END }
                            mWidth = MainFragment.HELP_WIDTH
                        }
                    }.playOn(cardViewLibrary)
                }

                cardViewLibrary.setOnClickListener {
                    tourGuide.apply {
                        cleanUp()
                        toolTip {
                            title { getString(R.string.help_about_title) }
                            description { getString(R.string.help_about_description) }
                            gravity { Gravity.TOP }
                            mWidth = MainFragment.HELP_WIDTH
                        }
                    }.playOn(cardViewAbout)
                }

                cardViewAbout.setOnClickListener {
                    tourGuide.apply {
                        cleanUp()
                        toolTip {
                            title { getString(R.string.help_settings_title) }
                            description { getString(R.string.help_settings_description) }
                            gravity { Gravity.TOP or Gravity.START }
                            mWidth = MainFragment.HELP_WIDTH
                        }
                    }.playOn(cardViewSettings)
                }

                cardViewSettings.setOnClickListener {
                    tourGuide.cleanUp()
                    callBack()
                }

                tourGuide.playOn(cardViewNotice)
            }
        }
    }
}