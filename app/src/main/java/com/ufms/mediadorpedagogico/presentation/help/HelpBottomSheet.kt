package com.ufms.mediadorpedagogico.presentation.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ufms.mediadorpedagogico.databinding.FragmentHelpBinding
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseBottomSheetDialog

class HelpBottomSheet : BaseBottomSheetDialog() {

    private lateinit var binding: FragmentHelpBinding
    private val navController: NavController by lazy { findNavController() }
    val args: HelpBottomSheetArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHelpBinding.inflate(inflater, container, false)
        setupUi()
        return binding.root
    }

    private fun setupUi() {
        with(binding) {
            description.text = args.description
            title.text = args.title
            buttonConfirm.setOnClickListener {
                dismiss()
            }
        }
    }
}