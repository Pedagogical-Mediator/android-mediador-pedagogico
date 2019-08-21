package com.ufms.mediadorpedagogico.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ufms.mediadorpedagogico.domain.boundary.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.domain.extensions.defaultSched
import com.ufms.mediadorpedagogico.domain.interactor.user.InvalidFieldsException
import com.ufms.mediadorpedagogico.domain.interactor.user.LoginForm
import com.ufms.mediadorpedagogico.domain.interactor.user.SignIn
import com.ufms.mediadorpedagogico.presentation.password.RecoverPasswordNavData
import com.ufms.mediadorpedagogico.presentation.signup.SignUpNavData
import com.ufms.mediadorpedagogico.presentation.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultPlaceholders
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy


class LoginViewModel(
    private val signIn: SignIn,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val showEmailFieldError: LiveData<Boolean> get() = showEmailFieldErrorLiveData
    val showPasswordFieldError: LiveData<Boolean> get() = showPasswordFieldErrorLiveData
    val goToMain: LiveData<Boolean> get() = goToMainLiveData

    private val showEmailFieldErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val showPasswordFieldErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val goToMainLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private var form = LoginForm()
    private var signInDisposable: Disposable? = null


    fun onEmailChanged(email: String) {
        form.email = email
    }

    fun onPasswordChanged(password: String) {
        form.password = password
    }

    fun onFacebookButtonClicked() {}

    fun onGoogleButtonClicked() {}

    fun onRecoverPasswordClicked() {
        goTo(RecoverPasswordNavData())
    }

    fun onSignUpClicked() {
        goTo(SignUpNavData())
    }

    fun onSubmitClicked() {
        form.useForm(this::submit)?.let { showFieldErrors(it) }
    }

    private fun submit(email: String, password: String) {
        signInDisposable?.dispose()
        signInDisposable = signIn.default(email, password, null)
                .defaultPlaceholders(this::setPlaceholder)
                .defaultSched(schedulerProvider)
                .subscribeBy(this::onFailure) {
                    showEmailFieldErrorLiveData.value = false
                    showPasswordFieldErrorLiveData.value = false
                    goToMainLiveData.value = true
                }
    }

    private fun onFailure(throwable: Throwable) {
        if (throwable is InvalidFieldsException) {
            showFieldErrors(throwable)
        } else {
            setDialog(throwable, this::onSubmitClicked)
        }
    }

    private fun showFieldErrors(e: InvalidFieldsException) {
        for (field in e.getFields()) {
            showFieldError(field)
        }
    }

    private fun showFieldError(field: Int) {
        when (field) {
            InvalidFieldsException.EMAIL -> showEmailFieldErrorLiveData.value = true
            InvalidFieldsException.PASSWORD -> showPasswordFieldErrorLiveData.value = true
        }
    }


}