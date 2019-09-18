package com.ufms.mediadorpedagogico.presentation.util.structure.dependecyinjector

import com.ufms.mediadorpedagogico.domain.entity.homework.Homework
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import com.ufms.mediadorpedagogico.presentation.main.MainViewModel
import com.ufms.mediadorpedagogico.presentation.homework.details.HomeworkDetailsViewModel
import com.ufms.mediadorpedagogico.presentation.homework.list.HomeworkListViewModel
import com.ufms.mediadorpedagogico.presentation.landing.SplashViewModel
import com.ufms.mediadorpedagogico.presentation.login.LoginViewModel
import com.ufms.mediadorpedagogico.presentation.notice.details.NoticeDetailsViewModel
import com.ufms.mediadorpedagogico.presentation.notice.list.NoticeListViewModel
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { MainViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { HomeworkListViewModel(get(), get()) }
    viewModel { (homework: Homework) -> HomeworkDetailsViewModel(homework) }
    viewModel { NoticeListViewModel(get(), get()) }
    viewModel { (notice: Notice) -> NoticeDetailsViewModel(notice) }
}