package com.emrekose.videogames.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.emrekose.videogames.R
import com.emrekose.videogames.common.Resource
import com.emrekose.videogames.databinding.FragmentDetailBinding
import com.emrekose.videogames.ui.model.GameItem
import com.emrekose.videogames.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var binding: FragmentDetailBinding? = null
    private val viewModel by viewModels<GameDetailViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameId = DetailFragmentArgs.fromBundle(requireArguments()).game.gameId

        viewModel.getGameDetails(gameId)
        viewModel.gameDetailsLiveData.observe(viewLifecycleOwner, { resource ->
            when(resource) {
                is Resource.Loading -> {
                    Timber.e("Loading")
                }
                is Resource.Success -> {
                    binding?.detailGameName?.text = resource.data.name
                    binding?.detailGameReleaseDate?.text = resource.data.released
                    binding?.detailGameMetacriticRate?.text = resource.data.metacritic.toString()
                    binding?.detailGameDescription?.text = resource.data.description
                    binding?.detailImage?.loadImage(resource.data.backgroundImage)
                }
                is Resource.Error -> {
                    Timber.e(resource.exception.localizedMessage)
                }
            }
        })
    }
}