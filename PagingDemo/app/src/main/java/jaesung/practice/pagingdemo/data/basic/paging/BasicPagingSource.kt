package jaesung.practice.pagingdemo.data.basic.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import jaesung.practice.pagingdemo.data.basic.model.Article
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import kotlin.math.max


private val firstArticleCreatedTime = LocalDateTime.now()
private const val STARTING_KEY = 0

class BasicPagingSource : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = article.id - (state.config.pageSize / 2))
    }

    /**
     * 스크롤 시 더 많은 데이터를 비동기로 가져오기 위한 메서드
     * 첫 load시 LoadParams.key는 null
     * LoadResult를 반환함
     *
     * LoadResult.Page - 로드 성공
     * LoadResult.Error - 오류 발생
     * LoadResult.Invalid - PagingSource가 결과의 무결성을 보장할 수 없으므로 무효화
     *
     * LoadResult.Page에는 data, prevKey, nextKey가 존재
     * data는 List 형태,
     * prevKey, nextKey는 load 메서드에서 사용하는 키
     *
     * 더이상 로드할 데이터가 없는 경우 nextKey 또는 prevKey는 null
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val startKey = params.key ?: STARTING_KEY

        val range = startKey.until(startKey + params.loadSize)
        if (startKey != STARTING_KEY) delay(3000L)

        return LoadResult.Page(
            data = range.map { number ->
                Article(
                    id = number,
                    title = "Article $number",
                    description = "This describes article $number",
                    created = firstArticleCreatedTime.minusDays(number.toLong())
                )
            },
            prevKey = when (startKey) {
                STARTING_KEY -> null
                else -> ensureValidKey(key = range.first - params.loadSize)
            },
            nextKey = range.last + 1
        )
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}