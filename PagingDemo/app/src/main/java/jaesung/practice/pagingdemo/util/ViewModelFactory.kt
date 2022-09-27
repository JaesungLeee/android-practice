package jaesung.practice.pagingdemo.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import jaesung.practice.pagingdemo.data.basic.repository.ArticleRepository
import jaesung.practice.pagingdemo.presentation.basic.BasicViewModel
import jaesung.practice.pagingdemo.presentation.localtest.LocalViewModel
import jaesung.practice.pagingdemo.presentation.remotetest.RemoteViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return when {
            modelClass.isAssignableFrom(LocalViewModel::class.java) -> {
                LocalViewModel() as T
            }
            modelClass.isAssignableFrom(RemoteViewModel::class.java) -> {
                RemoteViewModel() as T
            }
            modelClass.isAssignableFrom(BasicViewModel::class.java) -> {
                val repository = ArticleRepository()
                BasicViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Failed to create ViewModel : ${modelClass.name}")
        }
    }
}