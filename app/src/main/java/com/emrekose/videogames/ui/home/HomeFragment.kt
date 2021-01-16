package com.emrekose.videogames.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
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
    private lateinit var adapter: GamesRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GamesRecyclerViewAdapter(this::onGameClick)
        binding?.homeRecyclerview?.adapter = adapter

        viewModel.getAllGames()
        viewModel.gamesLiveData.observe(viewLifecycleOwner, { resource ->
            when(resource) {
                is Resource.Loading -> {
                    Timber.e("Loading")
                }
                is Resource.Success -> {
                    if(resource.data.isNotEmpty()) {
                        val viewPagerList = resource.data.subList(0, 3)
                        val recyclerViewList = resource.data.subList(3, resource.data.size)

                        binding?.homeProgressbar?.gone()
                        binding?.homeTopArea?.visible()
                        binding?.homeRecyclerview?.visible()

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

    private fun onGameClick(game: GameItem?) {
        val bundle = bundleOf(
            "game" to game,
        )
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }
}