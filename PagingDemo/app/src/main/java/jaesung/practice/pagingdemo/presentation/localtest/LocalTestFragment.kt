package jaesung.practice.pagingdemo.presentation.localtest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import jaesung.practice.pagingdemo.R
import jaesung.practice.pagingdemo.databinding.FragmentLocalTestBinding
import jaesung.practice.pagingdemo.util.BaseFragment
import jaesung.practice.pagingdemo.util.ViewModelFactory

class LocalTestFragment : BaseFragment<FragmentLocalTestBinding>(R.layout.fragment_local_test){

    private val viewModel: LocalViewModel by viewModels { ViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
    }

    private fun bindViews() = with(binding) {
        tbLocal.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}