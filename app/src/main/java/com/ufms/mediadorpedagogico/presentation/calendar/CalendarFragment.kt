package com.ufms.mediadorpedagogico.presentation.calendar

import android.content.Context
import android.os.Bundle
import android.view.*
import android.webkit.WebChromeClient
import com.ufms.mediadorpedagogico.databinding.FragmentCalendarBinding
import com.ufms.mediadorpedagogico.domain.entity.Calendar
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import org.koin.android.ext.android.inject

class CalendarFragment : BaseFragment() {
    override val baseViewModel: BaseViewModel get() = viewModel
    override val toolbarTitle: String get() = ""
    override val titleHelp: String get() = ""
    override val descriptionHelp: String get() = ""

    override fun openHelp() {}

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setHasOptionsMenu(false)
    }

    private val viewModel: CalendarViewModel by inject()
    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        subscribeUi()
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.calendarReceived.observeEvent(viewLifecycleOwner, ::configureWebView)
    }

    private fun configureWebView(calendar: Calendar?) {
        calendar?.link?.let {
            with(binding.webview) {
                webChromeClient = WebChromeClient()
                settings.javaScriptEnabled = true
                loadDataWithBaseURL(
                    "", it, "text/html", "UTF-8", ""
                )
            }
        }
    }
}
