package jaesung.practice.pagingdemo.presentation.localtest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import jaesung.practice.pagingdemo.R
import jaesung.practice.pagingdemo.databinding.FragmentLocalTestBinding
import jaesung.practice.pagingdemo.util.BaseFragment
import jaesung.practice.pagingdemo.util.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocalTestFragment : BaseFragment<FragmentLocalTestBinding>(R.layout.fragment_local_test){

    private val viewModel: LocalViewModel by viewModels { ViewModelFactory(requireContext()) }

    private val pagingAdapter: LocalTestPagingAdapter by lazy { LocalTestPagingAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
        initRecyclerView()
        observe()
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pagedData.collectLatest {
                    pagingAdapter.submitData(it)
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvLocalTest.apply {
            adapter = pagingAdapter
            val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
        }
    }

    private fun bindViews() = with(binding) {
        tbLocal.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }


}