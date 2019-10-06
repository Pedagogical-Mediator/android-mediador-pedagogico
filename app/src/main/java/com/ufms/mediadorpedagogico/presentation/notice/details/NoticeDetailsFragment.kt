package com.ufms.mediadorpedagogico.presentation.notice.details

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentNoticeDetailsBinding
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import com.ufms.mediadorpedagogico.presentation.util.bindingadapter.DividerItemDecorator
import com.ufms.mediadorpedagogico.presentation.util.extensions.drawableCompat
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.shortToast
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NoticeDetailsFragment : BaseFragment() {
    override val toolbarTitle: String
        get() = getString(R.string.activity_notice_label)

    val args: NoticeDetailsFragmentArgs by navArgs()

    override val baseViewModel: BaseViewModel get() = viewModel
    private lateinit var binding: FragmentNoticeDetailsBinding
    private val viewModel: NoticeDetailsViewModel by viewModel { parametersOf(args.notice) }
    lateinit var noticeDetailsAdapter: NoticeDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentNoticeDetailsBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        setupAdapter()
        setupRecycler()
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            noticeContent.observeEvent(this@NoticeDetailsFragment, ::onNoticeDetailsReceived)
        }
    }

    private fun setupAdapter() {
        noticeDetailsAdapter = NoticeDetailsAdapter()
    }

    private fun setupRecycler() {
        with(binding.recyclerViewLink) {
            layoutManager = LinearLayoutManager(this@NoticeDetailsFragment.context)
            adapter = noticeDetailsAdapter
            context.drawableCompat(R.drawable.recycler_view_divider)?.run {
                addItemDecoration(DividerItemDecorator(this))
            }
        }
    }

    private fun onNoticeDetailsReceived(noticeDetails: Notice?) {
        noticeDetails?.run {
            binding.noticeDetails = this
            noticeDetailsAdapter.setItems(links)
            binding.webviewContent.loadDataWithBaseURL(
                ""
                , description, "text/html", "UTF-8", ""
            )
            imageBase64?.let {
                try {
                    val decodedString = Base64.decode(it, Base64.DEFAULT)
                    val decodedByte =
                        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                    binding.imageViewResource.setImageBitmap(decodedByte)
                } catch (e: Exception) {
                    context?.shortToast(getString(R.string.activity_main_error_image_decode))
                }
            }
        }
    }

    companion object {
        const val NOTICE_KEY = "NOTICE"

        fun createIntent(context: Context, notice: Notice): Intent {
            return Intent(context, NoticeDetailsFragment::class.java).putExtra(NOTICE_KEY, notice)
        }
    }
}