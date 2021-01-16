package com.emrekose.videogames.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emrekose.videogames.common.BaseDiffCallback
import com.emrekose.videogames.databinding.ItemGameBinding
import com.emrekose.videogames.ui.model.GameItem
import com.emrekose.videogames.utils.loadImage
import timber.log.Timber

class GamesRecyclerViewAdapter(private val onGameClick: (GameItem?) -> Unit): ListAdapter<GameItem, GamesRecyclerViewAdapter.ViewHolder>(GamesDiffCallback) {

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

        fun bind(games: GameItem?, onGameClick: (GameItem?) -> Unit) {
            binding.gameName.text = games?.name
            binding.gameImage.loadImage(games?.backgroundImage)
            binding.gameRatingAndReleased.text = "${games?.metacritic} - ${games?.released}"

            binding.root.setOnClickListener {
                onGameClick(games)
            }
        }
    }

    object GamesDiffCallback : BaseDiffCallback<GameItem>() {
        override fun areContentsTheSame(oldItem: GameItem, newItem: GameItem): Boolean =
            oldItem.gameId == newItem.gameId
    }
}