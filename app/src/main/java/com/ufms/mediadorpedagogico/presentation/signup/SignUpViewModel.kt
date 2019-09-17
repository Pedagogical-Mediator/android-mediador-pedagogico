package com.ufms.mediadorpedagogico.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ufms.mediadorpedagogico.domain.extensions.defaultSched
import com.ufms.mediadorpedagogico.domain.interactor.user.InvalidFieldsException
import com.ufms.mediadorpedagogico.domain.interactor.user.SignUp
import com.ufms.mediadorpedagogico.domain.interactor.user.SignUpForm
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultPlaceholders
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import java.io.File

class SignUpViewModel(
    private val signUp: SignUp,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val errors: LiveData<Event<InvalidFieldsException>> get() = errorsLiveData
    val goToMain: LiveData<Boolean> get() = goToMainLiveData
    val requestPermission: LiveData<Event<Boolean>> get() = requestPermissionLiveData

    private val errorsLiveData: MutableLiveData<Event<InvalidFieldsException>> = MutableLiveData()
    private val goToMainLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val requestPermissionLiveData: MutableLiveData<Event<Boolean>> = MutableLiveData()

    private val form: SignUpForm = SignUpForm()

    fun onNameChanged(name: String) {
        form.name = name
    }

    fun onEmailChanged(email: String) {
        form.email = email
    }

    fun onCpfChanged(cpf: String) {
        form.cpf = cpf
    }

    fun onPhoneChanged(phone: String) {
        form.phone = phone
    }

    fun onPasswordChanged(password: String) {
        form.password = password
    }

    fun onPasswordConfirmationChanged(passwordConfirmation: String) {
        form.passwordConfirmation = passwordConfirmation
    }

    fun onSubmitClicked() {
        form.useForm(this::submit)?.let { showFieldErrors(it) }
    }

    fun onAvatarClicked() {
        requestPermissionLiveData.value = Event(true)
    }

    private fun submit(
        email: String,
        password: String,
        name: String,
        cpf: String,
        phone: String,
        confirmationPassword: String,
        avatarPath: String?
    ) {
        SignUp.Fields(name, email, phone, cpf, password, confirmationPassword, avatarPath)
            .let { fields ->
                signUp.execute(fields)
                    .defaultSched(schedulerProvider)
                    .defaultPlaceholders(this::setPlaceholder)
                    .subscribeBy(this::onFailure) {
                        goToMainLiveData.value = true
                    }.also { disposables.add(it) }
            }
    }

    fun onImagePickerSuccess(file: File) {
        form.avatarPath = file.absolutePath
    }

    fun onImagePickerFailure(throwable: Throwable) {
        setDialog(throwable)
    }

    private fun showFieldErrors(errors: Throwable) {
        if (errors is InvalidFieldsException) errorsLiveData.value = Event(errors)
    }

    private fun onFailure(throwable: Throwable) {
        if (throwable is InvalidFieldsException) {
            showFieldErrors(throwable)
        } else {
            setDialog(throwable, this::onSubmitClicked)
        }
    }
}