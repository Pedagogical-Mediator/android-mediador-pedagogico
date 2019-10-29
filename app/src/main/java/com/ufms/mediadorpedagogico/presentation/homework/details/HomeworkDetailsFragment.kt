package com.ufms.mediadorpedagogico.presentation.homework.details

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentHomeworkDetailsBinding
import com.ufms.mediadorpedagogico.domain.entity.homework.Homework
import com.ufms.mediadorpedagogico.presentation.util.bindingadapter.DividerItemDecorator
import com.ufms.mediadorpedagogico.presentation.util.extensions.drawableCompat
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.shortToast
import com.ufms.mediadorpedagogico.presentation.util.structure.base.Base2
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.navigateSafe
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeworkDetailsFragment : Base2() {

    override val titleHelp: String get() = getString(R.string.help_homework_details_title)
    override val descriptionHelp: String get() = getString(R.string.help_homework_details_description)
    override val toolbarTitle: String get() = getString(R.string.activity_homework_label)
    override val baseViewModel: BaseViewModel get() = viewModel

    val args: HomeworkDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentHomeworkDetailsBinding
    private val viewModel: HomeworkDetailsViewModel by viewModel { parametersOf(args.homework) }
    private val navController by lazy { findNavController() }
    lateinit var homeworkDetailsAdapter: HomeworkDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHomeworkDetailsBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        setupAdapter()
        setupRecycler()
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            homeworkContent.observeEvent(viewLifecycleOwner, ::onHomeworkDetailsReceived)
        }
    }

    override fun openHelp() {
        navController.navigateSafe(HomeworkDetailsFragmentDirections.actionHomeworkDetailsFragmentToHelpBottomSheet(titleHelp, descriptionHelp))
    }

    private fun setupAdapter() {
        homeworkDetailsAdapter = HomeworkDetailsAdapter()
    }

    private fun setupRecycler() {
        with(binding.recyclerViewLink) {
            layoutManager = LinearLayoutManager(context)
            adapter = homeworkDetailsAdapter
            context.drawableCompat(R.drawable.recycler_view_divider)?.run {
                addItemDecoration(DividerItemDecorator(this))
            }
        }
    }

    private fun onHomeworkDetailsReceived(homeworkDetails: Homework?) {
        homeworkDetails?.run {
            binding.homeworkDetails = this
            homeworkDetailsAdapter.setItems(links)
            binding.webviewContent.loadDataWithBaseURL(
                "", description, "text/html", "UTF-8", ""
            )
            imageBase64?.let {
                try {
                    val decodedString = Base64.decode(it, Base64.DEFAULT)
                    val decodedByte =
                        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                    binding.imageViewResource.setImageBitmap(decodedByte)
                } catch (e: Exception) {
                    context?.shortToast(getString(R.string.error_image_decode))
                }
            }
        }
    }
}