package com.ufms.mediadorpedagogico.presentation.util.structure.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.ufms.mediadorpedagogico.presentation.main.dashboard.DashboardActivity
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.shortToast
import com.ufms.mediadorpedagogico.presentation.util.extensions.showDialog
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.Navigator
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.DialogData

abstract class BaseFragment : Fragment() {

    abstract val baseViewModel: BaseViewModel
    abstract val toolbarTitle: String

    private var dialog: Dialog? = null

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupToolbar()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    open fun subscribeUi() {
        baseViewModel.dialog.observeEvent(this, ::onNextDialog)
        baseViewModel.goTo.observeEvent(this, ::onNextNavigation)
        baseViewModel.toast.observeEvent(this, ::onNextToast)
    }
    private fun onNextToast(text: String?) {
        text?.let {
            context?.shortToast(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onNextDialog(dialogData: DialogData?) {
        dialog?.dismiss()
        dialog = dialogData?.let { context?.showDialog(it) }
    }

    private fun setupToolbar() {
        (activity as? DashboardActivity)?.onNextTitle(toolbarTitle)
    }

    private fun onNextNavigation(navData: NavData?) {
        navData?.let {
            Navigator.goTo(this.context, it)
        }
    }
}