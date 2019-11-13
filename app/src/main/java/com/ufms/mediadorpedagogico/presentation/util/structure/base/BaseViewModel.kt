package com.ufms.mediadorpedagogico.presentation.util.structure.base

import androidx.annotation.CallSuper
import androidx.lifecycle.*
import com.ufms.mediadorpedagogico.data.firebase.FirebaseMessagingServiceHandler
import com.ufms.mediadorpedagogico.data.firebase.FirebaseMessagingServiceHandler.Companion.KEY_TOPIC_TOKEN
import com.ufms.mediadorpedagogico.domain.boundary.resources.Cache
import com.ufms.mediadorpedagogico.domain.boundary.resources.StringsProvider
import com.ufms.mediadorpedagogico.domain.util.isExpirationDay
import com.ufms.mediadorpedagogico.domain.util.subscriberHandler
import com.ufms.mediadorpedagogico.presentation.login.LoginNavData
import com.ufms.mediadorpedagogico.presentation.util.ErrorHandler
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.DialogData
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

open class BaseViewModel : LifecycleObserver, KoinComponent, ViewModel() {

    val goTo: LiveData<Event<NavData>> get() = goToLiveData
    val dialog: LiveData<Event<DialogData>> get() = dialogLiveData
    val toast: LiveData<Event<String>> get() = toastLiveData
    val placeholder: LiveData<Placeholder> get() = placeholderLiveData

    protected open val shouldDeny: Boolean = true

    private val goToLiveData = MutableLiveData<Event<NavData>>()
    private val dialogLiveData = MutableLiveData<Event<DialogData>>()
    private val toastLiveData = MutableLiveData<Event<String>>()
    private val placeholderLiveData = MutableLiveData<Placeholder>()
    private val errorHandler: ErrorHandler by inject()
    private val cache: Cache by inject()
    protected val strings: StringsProvider by inject()

    protected val disposables: CompositeDisposable = CompositeDisposable()

    open fun onLogout(logout: Boolean?) {
        val token = try {
            cache.get<String>(KEY_TOPIC_TOKEN, String::class.java)
        } catch (t: Throwable) {
            null
        }
        cache.clear()
        cache.set(KEY_TOPIC_TOKEN, token)
        unsubscribeTopics()
        goTo(LoginNavData(true))
    }

    fun setPlaceholder(placeholder: Placeholder) {
        placeholderLiveData.postValue(placeholder)
    }

    fun denyAccess(shouldDeny: Boolean) {
        dialogLiveData.postValue(
            Event(
                DialogData.error(
                    title = "", message = strings.errorExpiredClassKey,
                    onConfirm = ::deny, onDismiss = ::deny
                )
            )
        )
    }

    fun setPlaceholder(throwable: Throwable, retryAction: (() -> Unit)?) {
        setPlaceholder(errorHandler.getPlaceholder(throwable, retryAction))
    }

    fun setDialog(dialogData: DialogData) {
        dialogLiveData.postValue(Event(dialogData))
    }

    fun setToast(message: String) {
        toastLiveData.postValue(Event(message))
    }

    fun setDialog(
        throwable: Throwable, retryAction: (() -> Unit)? = null, onDismiss: (() -> Unit)? = null
    ) {
        setDialog(errorHandler.getDialogData(throwable, retryAction, onDismiss))
    }

    fun goTo(navData: NavData) {
        goToLiveData.postValue(Event(navData))
    }

    protected fun unsubscribeTopics() {
        subscriberHandler(FirebaseMessagingServiceHandler.KEY_TOPIC_NOTICES, false)
        subscriberHandler(FirebaseMessagingServiceHandler.KEY_TOPIC_NEWS, false)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @CallSuper
    protected open fun onCreate() {
        val calendar = Calendar.getInstance()
        if (calendar.isExpirationDay() && shouldDeny) {
            denyAccess(true)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        disposables.dispose()
    }

    private fun deny() {
        onLogout(true)
    }
}