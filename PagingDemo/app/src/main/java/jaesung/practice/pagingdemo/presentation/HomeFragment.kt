package jaesung.practice.pagingdemo.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import jaesung.practice.pagingdemo.R
import jaesung.practice.pagingdemo.databinding.FragmentHomeBinding
import jaesung.practice.pagingdemo.util.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
    }

    private fun bindViews() = with(binding) {
        btnBasic.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_basic)
        }
        btnLocal.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_local)
        }

        btnRemote.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_remote)
        }

    }
}