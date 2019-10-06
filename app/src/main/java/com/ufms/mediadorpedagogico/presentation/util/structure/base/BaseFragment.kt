package com.ufms.mediadorpedagogico.presentation.util.structure.base

import android.app.Dialog
import androidx.fragment.app.Fragment
import com.ufms.mediadorpedagogico.presentation.util.extensions.showDialog
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.Navigator
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.DialogData

open class BaseFragment : Fragment() {

    private var dialog: Dialog? = null

    open fun onGetDialog(dialogData: DialogData?) {
        dialogData?.let {
            dialog?.dismiss()
            dialog = activity?.showDialog(it)
        }
    }

    open fun onGoTo(navData: NavData?) {
        navData?.let {
            Navigator.goTo(activity, it)
        }
    }
}