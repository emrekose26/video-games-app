package com.emrekose.videogames.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.emrekose.videogames.common.Resource
import com.emrekose.videogames.databinding.FragmentHomeBinding
import com.emrekose.videogames.ui.model.GameItem
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val viewModel by viewModels<GamesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllGames()
        viewModel.gamesLiveData.observe(viewLifecycleOwner, { resource ->
            when(resource) {
                is Resource.Loading -> {
                    Timber.e("Loading")
                }
                is Resource.Success -> {
                    if(resource.data.isNotEmpty()) {
                        // change visibility progress bar and recylerview
                        val viewPagerList = resource.data.subList(0, 3)
                        val recyclerViewList = resource.data.subList(3, resource.data.size)

                        Timber.e("------------ ViewPager List ----------------")
                        viewPagerList.forEach {
                            Timber.e(it.toString())
                        }

                        Timber.e("------------ Recycler List ----------------")
                        recyclerViewList.forEach {
                            Timber.e(it.toString())
                        }
                    } else {

                    }
                }
                is Resource.Error -> {
                    Timber.e(resource.exception.localizedMessage)
                }
            }
        })

        // TODO sublist(take 3 element) instead of testList
        val testList = listOf(
            GameItem(1, "Game 1", "", 12, "111"),
            GameItem(2, "Game 2", "", 18, "222"),
            GameItem(3, "Game 3", "", 45, "333")
        )

        val pagerAdapter = HomeViewPager2Adapter(this, testList)
        binding?.mainViewPager?.adapter = pagerAdapter

        TabLayoutMediator(binding?.mainTabLayout!!, binding?.mainViewPager!!) { tab, position -> }.attach()
    }
}