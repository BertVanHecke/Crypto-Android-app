package com.bertvanhecke.cryptocurrencyapp.screens.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bertvanhecke.cryptocurrencyapp.databinding.FavoriteItemBinding
import com.bertvanhecke.cryptocurrencyapp.models.Coin

class FavoriteAdapter(val clickListener: CoinListener): ListAdapter<Coin, FavoriteAdapter.ViewHolder>(FeedDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(item: Coin, clickListener: CoinListener) {
            binding.coin = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = FavoriteItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

}

class FeedDiffCallback: DiffUtil.ItemCallback<Coin>(){
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }

}

class CoinListener(val clickListener: (coin: Coin) -> Unit){
    fun onClick(coin: Coin) = clickListener(coin)
}