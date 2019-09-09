package com.ufms.mediadorpedagogico.presentation.util.structure.base

import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.shortToast
import com.ufms.mediadorpedagogico.presentation.util.extensions.showDialog
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.Navigator
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.DialogData

abstract class BaseActivity : AppCompatActivity() {

    abstract val baseViewModel: BaseViewModel

    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeUi()
    }

    open fun subscribeUi() {
        baseViewModel.dialog.observeEvent(this, ::onNextDialog)
        baseViewModel.goTo.observeEvent(this, ::onNextNavigation)
        baseViewModel.toast.observeEvent(this, ::onNextToast)
    }

    private fun onNextToast(text: String?) {
        text?.let {
            shortToast(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onNextDialog(dialogData: DialogData?) {
        dialog?.dismiss()
        dialog = dialogData?.let { showDialog(it) }
    }

    private fun onNextNavigation(navData: NavData?) {
        navData?.let {
            Navigator.goTo(this, it)
        }
    }
}