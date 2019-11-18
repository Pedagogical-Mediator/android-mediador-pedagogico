package com.ufms.mediadorpedagogico.presentation.util.resources

import android.content.Context
import androidx.annotation.StringRes
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.domain.boundary.resources.StringsProvider

class AndroidStringProvider(context: Context) : StringsProvider {
    override val sunday: String
        get() = res(R.string.sunday)
    override val monday: String
        get() = res(R.string.monday)
    override val tuesday: String
        get() = res(R.string.tuesday)
    override val wednesday: String
        get() = res(R.string.wednesday)
    override val thursday: String
        get() = res(R.string.thursday)
    override val friday: String
        get() = res(R.string.friday)
    override val saturday: String
        get() = res(R.string.saturday)

    private val context = context.applicationContext

    override val errorTitle: String get() = res(R.string.error_title)
    override val errorUnknown: String get() = res(R.string.error_unknown)
    override val errorNetwork: String get() = res(R.string.error_network)
    override val errorUnauthorizedLoginNow: String get() = res(R.string.error_unauthorized_login_now)
    override val errorFacebookDeniedPermissions: String get() = res(R.string.error_facebook_denied_permissions)
    override val errorFacebookSdk: String get() = res(R.string.error_facebook_sdk)
    override val errorLoginFields: String get() = res(R.string.activity_login_error)
    override val globalDoLogin: String get() = res(R.string.global_do_login)
    override val globalTryAgain: String get() = res(R.string.global_try_again)
    override val globalOk: String get() = res(R.string.global_ok)
    override val globalYes: String get() = res(R.string.global_yes)
    override val globalNo: String get() = res(R.string.global_no)
    override val errorNotFound: String get() = res(R.string.error_not_found)
    override val errorServerInternal: String get() = res(R.string.error_server_internal)
    override val errorTimeout: String get() = res(R.string.error_timeout)
    override val errorInvalidFields: String get() = res(R.string.error_invalid_fields)
    override val waitForResult: String get() = res(R.string.global_wait)
    override val activityRecoverPassword: String get() = res(R.string.activity_recover_password)
    override val activityRecoverPasswordSuccess: String get() = res(R.string.activity_recover_password_success)
    override val errorExpiredClassKey: String get() = res(R.string.error_expired_classkey)


    override fun errorUnprocessable(errors: String): String =
        context.getString(R.string.error_unprocessable, errors)

    private fun res(@StringRes stringId: Int) = context.getString(stringId)
}
