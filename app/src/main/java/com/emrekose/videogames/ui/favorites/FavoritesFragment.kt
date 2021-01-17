package com.emrekose.videogames.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.emrekose.videogames.R
import com.emrekose.videogames.databinding.FragmentFavoritesBinding
import com.emrekose.videogames.ui.home.GamesRecyclerViewAdapter
import com.emrekose.videogames.ui.model.GameItem
import com.emrekose.videogames.utils.gone
import com.emrekose.videogames.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private var binding: FragmentFavoritesBinding? = null
    private val viewModel by viewModels<FavoritesViewModel>()
    private lateinit var adapter: GamesRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GamesRecyclerViewAdapter(this::onGameClick)
        binding?.favoritesRecyclerview?.adapter = adapter

        viewModel.getAllFavGames()
        viewModel.allFavGamesLiveData.observe(viewLifecycleOwner, { games ->
            if (games.isNotEmpty()) {
                binding?.favoritesRecyclerview?.visible()
                binding?.favoritesEmptyWarning?.gone()
                adapter.submitList(games)
            } else {
                binding?.favoritesRecyclerview?.gone()
                binding?.favoritesEmptyWarning?.visible()
            }
        })
    }

    private fun onGameClick(game: GameItem?) {
        val bundle = bundleOf(
            "game" to game,
        )
        findNavController().navigate(R.id.action_favoritesFragment_to_detailFragment, bundle)
    }
}