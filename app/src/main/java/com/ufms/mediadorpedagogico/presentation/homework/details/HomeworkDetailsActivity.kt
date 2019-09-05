package com.ufms.mediadorpedagogico.presentation.homework.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivityHomeworkDetailsBinding
import com.ufms.mediadorpedagogico.domain.entity.Homework
import com.ufms.mediadorpedagogico.presentation.util.bindingadapter.DividerItemDecorator
import com.ufms.mediadorpedagogico.presentation.util.extensions.drawableCompat
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.setupCustomizedToolbar
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeworkDetailsActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityHomeworkDetailsBinding
    private val viewModel: HomeworkDetailsViewModel by viewModel { parametersOf(homeworkDetails) }
    lateinit var homeworkDetailsAdapter: HomeworkDetailsAdapter

    private val homeworkDetails by lazy {
        (intent.extras?.getSerializable(HOMEWORK_KEY) as? Homework)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homework_details)
        setupCustomizedToolbar(binding.toolbarCustomized, true, getString(R.string.activity_homework_label))
        lifecycle.addObserver(viewModel)
        setupAdapter()
        setupRecycler()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            homeworkContent.observeEvent(this@HomeworkDetailsActivity, ::onHomeworkDetailsReceived)
        }
    }

    private fun setupAdapter() {
        homeworkDetailsAdapter = HomeworkDetailsAdapter()
    }

    private fun setupRecycler() {
        with(binding.recyclerViewLink) {
            layoutManager = LinearLayoutManager(this@HomeworkDetailsActivity)
            adapter = homeworkDetailsAdapter
            drawableCompat(R.drawable.recycler_view_divider)?.run {
                addItemDecoration(DividerItemDecorator(this))
            }
        }
    }

    private fun onHomeworkDetailsReceived(homeworkDetails: Homework?) {
        homeworkDetails?.run {
            binding.homeworkDetails = this
            homeworkDetailsAdapter.setItems(homeworkLinks)
        }
    }

    companion object {
        const val HOMEWORK_KEY = "HOMEWORK"

        fun createIntent(context: Context, homework: Homework): Intent {
            return Intent(context, HomeworkDetailsActivity::class.java).putExtra(HOMEWORK_KEY, homework)
        }
    }
}