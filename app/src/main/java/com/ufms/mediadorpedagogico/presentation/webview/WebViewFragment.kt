package br.com.dietaetreino.presentation.view.dashboard.profile.settings.webview

import android.content.Context
import android.os.Bundle
import android.view.*
import android.webkit.WebChromeClient
import androidx.navigation.fragment.navArgs
import com.ufms.mediadorpedagogico.databinding.FragmentWebViewBinding
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.webview.WebViewViewModel
import org.koin.android.ext.android.inject

class WebViewFragment : BaseFragment() {
    override val baseViewModel: BaseViewModel get() = viewModel
    override val toolbarTitle: String get() = args.toolbarTitle
    override val titleHelp: String get() = ""
    override val descriptionHelp: String get() = ""

    override fun openHelp() {}

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setHasOptionsMenu(false)
    }

    private val viewModel: WebViewViewModel by inject()
    private lateinit var binding: FragmentWebViewBinding
    private val args by navArgs<WebViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        configureWebView()
        subscribeUi()
        return binding.root
    }

    private fun configureWebView() {
        with(binding.webview) {
            webChromeClient = WebChromeClient()
            settings.javaScriptEnabled = true
            loadDataWithBaseURL(
                "", args.url, "text/html", "UTF-8", ""
            )
        }
    }
}
