package jaesung.practice.pagingdemo.presentation.basic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jaesung.practice.pagingdemo.data.basic.model.Article
import jaesung.practice.pagingdemo.data.basic.repository.ArticleRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class BasicViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    val items: StateFlow<List<Article>> = articleRepository.articleStream
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = listOf()
        )

//    private val _items =  MutableStateFlow<List<Article>>(emptyList())
//    val items: StateFlow<List<Article>> = _items.asStateFlow()
//
//
//    fun loadItems() {
//        Timber.e("Load Start")
//        viewModelScope.launch {
//            articleRepository.articleStream.collect {
//                _items.value = it
//            }
//        }
//    }

}