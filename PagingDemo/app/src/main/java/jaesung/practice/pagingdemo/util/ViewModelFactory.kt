package jaesung.practice.pagingdemo.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import jaesung.practice.pagingdemo.data.basic.repository.ArticleRepository
import jaesung.practice.pagingdemo.data.localtest.repository.LocalTestRepository
import jaesung.practice.pagingdemo.presentation.basic.BasicViewModel
import jaesung.practice.pagingdemo.presentation.localtest.LocalViewModel
import jaesung.practice.pagingdemo.presentation.remotetest.RemoteViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return when {
            modelClass.isAssignableFrom(LocalViewModel::class.java) -> {
                val dao = ServiceLocator.provideDatabase(context.applicationContext).localTestDao()
                val repository = LocalTestRepository(dao)
                LocalViewModel(repository) as T
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