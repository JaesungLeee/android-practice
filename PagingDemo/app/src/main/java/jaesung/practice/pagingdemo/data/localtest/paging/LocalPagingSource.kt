package jaesung.practice.pagingdemo.data.localtest.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import jaesung.practice.pagingdemo.data.localtest.database.LocalTestDao
import jaesung.practice.pagingdemo.data.localtest.model.Item
import kotlinx.coroutines.delay
import timber.log.Timber
import java.lang.Exception

class LocalPagingSource(
    private val localDao: LocalTestDao
) : PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val page = params.key ?: 0

        return try {
            val entity = localDao.getPaginationList(params.loadSize, page * params.loadSize)

            Timber.e("${params.loadSize} / ${params.key}")
            Timber.e("$entity")
            if (page != 0) delay(1000L)

            LoadResult.Page(
                data = entity,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entity.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}