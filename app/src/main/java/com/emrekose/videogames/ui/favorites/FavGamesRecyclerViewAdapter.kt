package com.emrekose.videogames.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emrekose.videogames.common.BaseDiffCallback
import com.emrekose.videogames.databinding.ItemGameBinding
import com.emrekose.videogames.ui.model.FavGameItem
import com.emrekose.videogames.utils.loadImage

class FavGamesRecyclerViewAdapter(
    private val onGameClick: (FavGameItem?) -> Unit
): ListAdapter<FavGameItem, FavGamesRecyclerViewAdapter.ViewHolder>(FavGamesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(LayoutInflater.from(parent.context), parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), onGameClick)

    class ViewHolder(private val binding: ItemGameBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(layoutInflater: LayoutInflater, parent: ViewGroup?): ViewHolder {
                val itemBinding = ItemGameBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(itemBinding)
            }
        }

        fun bind(games: FavGameItem?, onGameClick: (FavGameItem?) -> Unit) {
            binding.gameName.text = games?.name
            binding.gameImage.loadImage(games?.backgroundImage)
            binding.gameRatingAndReleased.text = "${games?.metacritic} - ${games?.released}"

            binding.root.setOnClickListener {
                onGameClick(games)
            }
        }
    }

    object FavGamesDiffCallback : BaseDiffCallback<FavGameItem>() {
        override fun areContentsTheSame(oldItem: FavGameItem, newItem: FavGameItem): Boolean =
            oldItem.gameId == newItem.gameId
    }
}