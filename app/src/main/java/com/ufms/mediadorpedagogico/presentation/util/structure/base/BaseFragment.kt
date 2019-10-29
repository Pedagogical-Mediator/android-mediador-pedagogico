package com.ufms.mediadorpedagogico.presentation.util.structure.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.presentation.main.MainFragmentDirections
import com.ufms.mediadorpedagogico.presentation.main.dashboard.DashboardActivity
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.shortToast
import com.ufms.mediadorpedagogico.presentation.util.extensions.showDialog
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.Navigator
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.navigateSafe
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.DialogData

abstract class BaseFragment : Fragment() {
    abstract val baseViewModel: BaseViewModel
    abstract val toolbarTitle: String
    abstract val titleHelp: String
    abstract val descriptionHelp: String

    private var dialog: Dialog? = null

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupToolbar()
        subscribeUi()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @CallSuper
    open fun subscribeUi() {
        baseViewModel.dialog.observeEvent(this, ::onNextDialog)
        baseViewModel.goTo.observeEvent(this, ::onNextNavigation)
        baseViewModel.toast.observeEvent(this, ::onNextToast)
//        baseViewModel.deniedAccess.observeAction(this, ::onDenyAccess)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_question, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_question_mark -> {
                openHelp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    abstract fun openHelp()

    private fun onNextDialog(dialogData: DialogData?) {
        dialog?.dismiss()
        dialog = dialogData?.let { context?.showDialog(it) }
    }

    private fun onNextNavigation(navData: NavData?) {
        navData?.let {
            Navigator.goTo(this.context, it)
        }
    }

    private fun onNextToast(text: String?) {
        text?.let {
            context?.shortToast(it)
        }
    }

    private fun onDenyAccess(shouldDeny: Boolean?) {

    }

    private fun setupToolbar() {
        (activity as? DashboardActivity)?.onNextTitle(toolbarTitle)
    }
}