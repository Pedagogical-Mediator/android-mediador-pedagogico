package com.ufms.mediadorpedagogico.presentation.util.structure.dependecyinjector

import com.ufms.mediadorpedagogico.domain.entity.Homework
import com.ufms.mediadorpedagogico.presentation.homework.details.HomeworkDetailsViewModel
import com.ufms.mediadorpedagogico.presentation.homework.list.HomeworkListViewModel
import com.ufms.mediadorpedagogico.presentation.landing.SplashViewModel
import com.ufms.mediadorpedagogico.presentation.login.LoginViewModel
import com.ufms.mediadorpedagogico.presentation.signup.SignUpViewModel
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { SignUpViewModel(get(), get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { HomeworkListViewModel(get(), get()) }
    viewModel { (homework: Homework) -> HomeworkDetailsViewModel(get(), homework) }
}