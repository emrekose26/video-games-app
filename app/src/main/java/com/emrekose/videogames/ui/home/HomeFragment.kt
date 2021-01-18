package com.emrekose.videogames.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.emrekose.videogames.R
import com.emrekose.videogames.common.Resource
import com.emrekose.videogames.databinding.FragmentHomeBinding
import com.emrekose.videogames.ui.model.GameItem
import com.emrekose.videogames.utils.gone
import com.emrekose.videogames.utils.visible
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val viewModel by viewModels<GamesViewModel>()
    private val sharedViewModel: SearchSharedViewModel by activityViewModels()
    private lateinit var adapter: GamesRecyclerViewAdapter

    var viewPagerList = listOf<GameItem>()
    var recyclerViewList = listOf<GameItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GamesRecyclerViewAdapter(this::onGameClick)
        binding?.homeRecyclerview?.adapter = adapter

        getAllGamesFromApi()
        getCachedGamesBySearchQuery()
    }

    private fun getAllGamesFromApi() {
        viewModel.getAllGames()
        viewModel.gamesLiveData.observe(viewLifecycleOwner, { resource ->
            when(resource) {
                is Resource.Loading -> {
                    binding?.apply {
                        homeProgressbar.visible()
                        homeTopArea.gone()
                        homeRecyclerview.gone()
                    }
                }
                is Resource.Success -> {
                    if(resource.data.isNotEmpty()) {
                        viewModel.saveGamesToLocale(resource.data)

                        viewPagerList = resource.data.subList(0, VIEWPAGER_GAME_COUNT)
                        recyclerViewList = resource.data.subList(VIEWPAGER_GAME_COUNT, resource.data.size)

                        binding?.apply {
                            homeProgressbar.gone()
                            homeTopArea.visible()
                            homeRecyclerview.visible()
                        }
                        adapter.submitList(recyclerViewList)

                        val pagerAdapter = HomeViewPager2Adapter(this, viewPagerList, this::onGameClick)
                        binding?.mainViewPager?.adapter = pagerAdapter

                        TabLayoutMediator(binding?.mainTabLayout!!, binding?.mainViewPager!!) { tab, position -> }.attach()
                    } else {

                    }
                }
                is Resource.Error -> {
                    Timber.e(resource.exception.localizedMessage)
                }
            }
        })
    }

    private fun getCachedGamesBySearchQuery() {
        sharedViewModel.searchQuery.observe(viewLifecycleOwner, { query ->
            viewModel.getGameFromLocale(query)
            if (query.length >= 3) {
                binding?.homeTopArea?.gone()
                viewModel.cachedGamesLiveData.observe(viewLifecycleOwner, { cachedGames ->
                    if(cachedGames.isNotEmpty()) {
                        binding?.homeEmptyText?.gone()
                        binding?.homeRecyclerview?.visible()
                        adapter.submitList(cachedGames)
                    } else {
                        binding?.homeEmptyText?.visible()
                        binding?.homeRecyclerview?.gone()
                    }
                })
            } else {
                binding?.homeTopArea?.visible()
                binding?.homeEmptyText?.gone()
                adapter.submitList(recyclerViewList)
            }
        })
    }

    private fun onGameClick(game: GameItem?) {
        val bundle = bundleOf(
            "gameId" to game?.gameId,
        )
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    companion object {
        const val VIEWPAGER_GAME_COUNT = 3
    }
}