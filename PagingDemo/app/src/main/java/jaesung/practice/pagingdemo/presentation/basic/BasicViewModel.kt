package jaesung.practice.pagingdemo.presentation.basic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import jaesung.practice.pagingdemo.data.basic.model.Article
import jaesung.practice.pagingdemo.data.basic.repository.ArticleRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

private const val ITEMS_PER_PAGE = 50
class BasicViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

//    val items: StateFlow<List<Article>> = articleRepository.articleStream
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(),
//            initialValue = listOf()
//        )

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

    val items: Flow<PagingData<Article>> = Pager(
        config = PagingConfig(
            pageSize = ITEMS_PER_PAGE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = { articleRepository.articlePagingSource() }
    ).flow.cachedIn(viewModelScope)

}