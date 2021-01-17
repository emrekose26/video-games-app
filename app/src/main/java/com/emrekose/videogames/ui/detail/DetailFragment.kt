package com.emrekose.videogames.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emrekose.videogames.R
import com.emrekose.videogames.common.Resource
import com.emrekose.videogames.databinding.FragmentDetailBinding
import com.emrekose.videogames.utils.gone
import com.emrekose.videogames.utils.loadImage
import com.emrekose.videogames.utils.toast
import com.emrekose.videogames.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var binding: FragmentDetailBinding? = null
    private val viewModel by viewModels<GameDetailViewModel>()
    var isFavorited: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameId = DetailFragmentArgs.fromBundle(requireArguments()).game.gameId

        getGameDetails(gameId)
        getGameFromDb(gameId)
    }

    private fun getGameDetails(gameId: Int) {
        viewModel.getGameDetails(gameId)
        viewModel.gameDetailsLiveData.observe(viewLifecycleOwner, { resource ->
            when(resource) {
                is Resource.Loading -> {
                    binding?.apply {
                        detailContent.gone()
                        detailProgressbar.visible()
                    }
                }
                is Resource.Success -> {
                    binding?.apply {
                        detailContent.visible()
                        detailProgressbar.gone()

                        detailGameName.text = resource.data.name
                        detailGameReleaseDate.text = resource.data.released
                        detailGameMetacriticRate.text = resource.data.metacritic.toString()
                        detailGameDescription.text = resource.data.description
                        detailImage.loadImage(resource.data.backgroundImage)
                    }

                    binding?.detailFab?.setOnClickListener {
                        if(!isFavorited) {
                            viewModel.addGameToDb(resource.data)
                            activity?.toast(getString(R.string.added_to_favorites))
                        } else {
                            viewModel.deleteGameFromDb(gameId)
                            activity?.toast(getString(R.string.deleted_from_favorites))
                        }
                    }
                }
                is Resource.Error -> {
                    Timber.e(resource.exception.localizedMessage)
                }
            }
        })
    }

    private fun getGameFromDb(gameId: Int) {
        viewModel.getFavGameById(gameId)
        viewModel.favGameLiveData.observe(viewLifecycleOwner, { game ->
            if(game != null) {
                isFavorited = true
                binding?.detailFab?.setImageResource(R.drawable.ic_favorite)
            } else {
                isFavorited = false
                binding?.detailFab?.setImageResource(R.drawable.ic_favorite_border)
            }
        })
    }
}