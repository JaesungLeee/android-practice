package jaesung.practice.pagingdemo.presentation.localtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import jaesung.practice.pagingdemo.data.localtest.repository.LocalTestRepository

class LocalViewModel(private val repository: LocalTestRepository) : ViewModel() {

    val pagedData = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            initialLoadSize = 20
        ),
        pagingSourceFactory = { repository.getPagedList() }
    ).flow.cachedIn(viewModelScope)

}