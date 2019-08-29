package com.ufms.mediadorpedagogico.presentation.homework

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivityHomeworkBinding
import com.ufms.mediadorpedagogico.databinding.ActivityRegisterBinding
import com.ufms.mediadorpedagogico.presentation.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.extensions.observe
import com.ufms.mediadorpedagogico.presentation.util.extensions.setupToolbar
import com.ufms.mediadorpedagogico.presentation.util.extensions.showHomeButton
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.inject

class HomeworkActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityHomeworkBinding
    private lateinit var homeworkAdapter: HomeworkAdapter
    private val viewModel: HomeworkViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        lifecycle.addObserver(viewModel)
        setupUi()
        setupAdapter()
        setupRecycler()
        showHomeButton()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.placeholder.observe(this, ::onNextPlaceholder)
    }

    private fun setupAdapter() {
        homeworkAdapter = HomeworkAdapter(viewModel::setupOnItemClicked)
    }

    private fun setupRecycler() {
        with(binding.recyclerViewHomework) {
            layoutManager = LinearLayoutManager(this@HomeworkActivity)
            adapter = transactionsAdapter
        }
    }

    private fun setupUi() {
        with(binding) {
        }
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.includedLoading.placeholder = it }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, HomeworkActivity::class.java)
        }
    }
}