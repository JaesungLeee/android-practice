package jaesung.practice.pagingdemo.presentation.basic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import jaesung.practice.pagingdemo.data.basic.model.Article
import jaesung.practice.pagingdemo.data.basic.model.createdText
import jaesung.practice.pagingdemo.databinding.ItemArticleBinding

class BasicPagingAdapter: PagingDataAdapter<Article, BasicPagingAdapter.BasicViewHolder>(ARTICLE_DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArticleBinding.inflate(inflater, parent, false)
        return BasicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasicViewHolder, position: Int) {
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
        }
    }

    class BasicViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.title.text = article.title
            binding.description.text = article.description
            binding.created.text = article.createdText
        }

    }


    companion object {
        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }
}