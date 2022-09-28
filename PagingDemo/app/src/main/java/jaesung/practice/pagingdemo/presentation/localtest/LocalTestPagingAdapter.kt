package jaesung.practice.pagingdemo.presentation.localtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import jaesung.practice.pagingdemo.data.localtest.model.Item
import jaesung.practice.pagingdemo.databinding.ItemLocalTestBinding

class LocalTestPagingAdapter(
    private val itemClicked: (Item) -> Unit
) : PagingDataAdapter<Item, LocalTestPagingAdapter.PagingViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLocalTestBinding.inflate(inflater, parent, false)
        return PagingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item, itemClicked)
        }
    }

    class PagingViewHolder(private val binding: ItemLocalTestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item, itemClicked: (Item) -> Unit) {
            binding.clContainer.setOnClickListener {
                itemClicked.invoke(item)
            }
            binding.item = item
            binding.executePendingBindings()

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }
}