package com.ufms.mediadorpedagogico.presentation.notice.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentNoticeDetailsBinding
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import com.ufms.mediadorpedagogico.presentation.util.bindingadapter.DividerItemDecorator
import com.ufms.mediadorpedagogico.presentation.util.bindingadapter.loadHTML
import com.ufms.mediadorpedagogico.presentation.util.bindingadapter.setImage
import com.ufms.mediadorpedagogico.presentation.util.extensions.drawableCompat
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
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
            noticeContent.observeEvent(viewLifecycleOwner, ::onNoticeDetailsReceived)
        }
    }

    private fun setupAdapter() {
        noticeDetailsAdapter = NoticeDetailsAdapter()
    }

    private fun setupRecycler() {
        with(binding.recyclerViewLink) {
            layoutManager = LinearLayoutManager(context)
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
            binding.webviewContent.loadHTML(description)
            imageBase64?.run(binding.imageViewResource::setImage)
        }
    }
}