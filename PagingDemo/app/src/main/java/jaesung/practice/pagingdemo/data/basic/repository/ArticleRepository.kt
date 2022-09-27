package jaesung.practice.pagingdemo.data.basic.repository

import jaesung.practice.pagingdemo.data.basic.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDateTime


private val firstArticleCreatedTime = LocalDateTime.now()

class ArticleRepository {

    val articleStream: Flow<List<Article>> = flowOf(
        (0..500).map { number ->
            Article(
                id = number,
                title = "Article $number",
                description = "This decribes article $number",
                created = firstArticleCreatedTime.minusDays(number.toLong())
            )
        }
    )
}