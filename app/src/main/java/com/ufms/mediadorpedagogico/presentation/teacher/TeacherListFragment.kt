package com.ufms.mediadorpedagogico.presentation.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentTeachersBinding
import com.ufms.mediadorpedagogico.domain.entity.Teacher
import com.ufms.mediadorpedagogico.presentation.util.extensions.*
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class TeacherListFragment : BaseFragment() {

    override val titleHelp: String get() = getString(R.string.help_homework_list_title)
    override val descriptionHelp: String get() = getString(R.string.help_homework_list_description)
    override val toolbarTitle: String get() = getString(R.string.teachers)
    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: FragmentTeachersBinding
    private var teacherListAdapter: TeacherListAdapter? = null
    private val viewModel: TeacherListViewModel by inject()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTeachersBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observeAction(viewLifecycleOwner, ::onNextPlaceholder)
            teachers.observeEvent(viewLifecycleOwner, ::onTeachersLoaded)
            noContentReturned.observeEvent(viewLifecycleOwner, ::onNoContentReturned)
        }
    }

    override fun openHelp() {
//        navController.navigateSafe(
//            HomeworkListFragmentDirections.actionHomeworkListFragmentToHelpBottomSheet(
//                titleHelp,
//                descriptionHelp
//            )
//        )
    }

    private fun setupRecyclerView() {
        teacherListAdapter.ifNull {
            teacherListAdapter = TeacherListAdapter()
        }
        with(binding.recyclerViewTeacher) {
            adapter.ifNull {
                layoutManager = LinearLayoutManager(context)
                adapter = teacherListAdapter
            }
        }
    }

    private fun onTeachersLoaded(teachers: List<Teacher>?) {
        safeLet(teachers, teacherListAdapter) { _teachers, adapter ->
            adapter.setItems(_teachers)
        }
    }

    fun onNoContentReturned(noContentReturned: Boolean?) {
        noContentReturned?.let {
            if (teacherListAdapter?.itemCount == 0) {
                binding.includedPlaceholderNoResults.root.setVisible(true)
            }
        }
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.placeholder = it }
    }
}