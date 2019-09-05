package com.ufms.mediadorpedagogico.presentation.homework.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivityHomeworkDetailsBinding
import com.ufms.mediadorpedagogico.databinding.ActivityHomeworkListBinding
import com.ufms.mediadorpedagogico.domain.entity.Homework
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.extensions.*
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeworkDetailsActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityHomeworkDetailsBinding
    private val viewModel: HomeworkDetailsViewModel by viewModel { parametersOf(homeworkDetails) }
    lateinit var homeworkListAdapter: HomeworkListAdapter

    private val homeworkDetails by lazy {
        (intent.extras?.getSerializable(HOMEWORK_KEY) as? Homework)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homework_details)
        setupCustomizedToolbar(binding.toolbarCustomized, true, getString(R.string.activity_homework_label))
        lifecycle.addObserver(viewModel)
        setupUi()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observe(this@HomeworkDetailsActivity, ::onNextPlaceholder)
            homeworkContent.observeEvent(this@HomeworkDetailsActivity, ::onHomeworkDetailsReceived)
        }
    }

    private fun onHomeworkDetailsReceived(homeworkDetails: Homework?) {
        binding.homeworkDetails = homeworkDetails
    }

    private fun setupUi() {
        //TODO botar click
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.placeholder = it }
    }

    companion object {
        const val HOMEWORK_KEY = "HOMEWORK"

        fun createIntent(context: Context, homework: Homework): Intent {
            return Intent(context, HomeworkDetailsActivity::class.java).putExtra(HOMEWORK_KEY, homework)
        }
    }
}