package com.ufms.mediadorpedagogico.presentation.util.structure.base

import android.app.Dialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ufms.mediadorpedagogico.presentation.util.extensions.showDialog
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.Navigator
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.DialogData

open class BaseBottomSheetDialog : BottomSheetDialogFragment() {

    private var fragmentDialog: Dialog? = null

    open fun onGetDialog(dialogData: DialogData?) {
        dialogData?.let {
            fragmentDialog?.dismiss()
            fragmentDialog = activity?.showDialog(it)
        }
    }

    open fun onGoTo(navData: NavData?) {
        navData?.let {
            Navigator.goTo(activity, it)
        }
    }
}