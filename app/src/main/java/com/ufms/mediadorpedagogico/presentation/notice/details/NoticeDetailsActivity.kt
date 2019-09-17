package com.ufms.mediadorpedagogico.presentation.notice.details

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivityNoticeDetailsBinding
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import com.ufms.mediadorpedagogico.presentation.util.bindingadapter.DividerItemDecorator
import com.ufms.mediadorpedagogico.presentation.util.extensions.drawableCompat
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.setupCustomizedToolbar
import com.ufms.mediadorpedagogico.presentation.util.extensions.shortToast
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NoticeDetailsActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityNoticeDetailsBinding
    private val viewModel: NoticeDetailsViewModel by viewModel { parametersOf(noticeDetails) }
    lateinit var noticeDetailsAdapter: NoticeDetailsAdapter

    private val noticeDetails by lazy {
        (intent.extras?.getSerializable(NOTICE_KEY) as? Notice)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notice_details)
        setupCustomizedToolbar(binding.toolbarCustomized, true, getString(R.string.activity_notice_label))
        lifecycle.addObserver(viewModel)
        setupAdapter()
        setupRecycler()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            noticeContent.observeEvent(this@NoticeDetailsActivity, ::onNoticeDetailsReceived)
        }
    }

    private fun setupAdapter() {
        noticeDetailsAdapter = NoticeDetailsAdapter()
    }

    private fun setupRecycler() {
        with(binding.recyclerViewLink) {
            layoutManager = LinearLayoutManager(this@NoticeDetailsActivity)
            adapter = noticeDetailsAdapter
            drawableCompat(R.drawable.recycler_view_divider)?.run {
                addItemDecoration(DividerItemDecorator(this))
            }
        }
    }

    private fun onNoticeDetailsReceived(noticeDetails: Notice?) {
        noticeDetails?.run {
            binding.noticeDetails = this
            noticeDetailsAdapter.setItems(links)
            imageBase64?.let {
                try {
                    val decodedString = Base64.decode(it, Base64.DEFAULT)
                    val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                    binding.imageViewResource.setImageBitmap(decodedByte)
                } catch (e: Exception) {
                    shortToast(getString(R.string.activity_main_error_image_decode))
                }
            }
        }
    }

    companion object {
        const val NOTICE_KEY = "NOTICE"

        fun createIntent(context: Context, notice: Notice): Intent {
            return Intent(context, NoticeDetailsActivity::class.java).putExtra(NOTICE_KEY, notice)
        }
    }
}