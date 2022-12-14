package jaesung.practice.pagingdemo.presentation.basic

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jaesung.practice.pagingdemo.R
import jaesung.practice.pagingdemo.databinding.FragmentBasicBinding
import jaesung.practice.pagingdemo.util.BaseFragment
import jaesung.practice.pagingdemo.util.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class BasicFragment : BaseFragment<FragmentBasicBinding>(R.layout.fragment_basic) {

    private val viewModel: BasicViewModel by viewModels { ViewModelFactory(requireContext()) }
//    private val articleAdapter by lazy { ArticleAdapter() }
    private val pagingAdapter by lazy { BasicPagingAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()

        observe()

//        viewModel.loadItems()
    }

    private fun bindViews() {
        binding.tbBasic.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        with(binding.list) {
            adapter = pagingAdapter
            layoutManager = LinearLayoutManager(context)
            val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
        }
    }


    private fun observe() {
        val items = viewModel.items

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                items.collect {
//                    Timber.e("$it")
//                    articleAdapter.submitList(it)
//                }
//            }
//        }

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                Timber.e("${viewModel.items.value}")
//                articleAdapter.submitList(viewModel.items.value)
//            }
//        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                items.collectLatest { pagingData ->
                    pagingAdapter.submitData(pagingData)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                pagingAdapter.loadStateFlow.collect {
                    binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                }
            }
        }
    }
}