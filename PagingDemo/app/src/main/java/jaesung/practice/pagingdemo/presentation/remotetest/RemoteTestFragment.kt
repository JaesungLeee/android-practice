package jaesung.practice.pagingdemo.presentation.remotetest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import jaesung.practice.pagingdemo.R
import jaesung.practice.pagingdemo.databinding.FragmentRemoteTestBinding
import jaesung.practice.pagingdemo.util.BaseFragment
import jaesung.practice.pagingdemo.util.ViewModelFactory

class RemoteTestFragment : BaseFragment<FragmentRemoteTestBinding>(R.layout.fragment_remote_test) {

    private val viewModel: RemoteViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
    }

    private fun bindViews() = with(binding) {
        tbRemote.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}