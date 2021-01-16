package com.emrekose.videogames.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emrekose.videogames.databinding.FragmentMainPlaceholderBinding
import com.emrekose.videogames.ui.model.GameItem
import com.emrekose.videogames.utils.Constants.ARG_GAME
import com.emrekose.videogames.utils.loadImage
import timber.log.Timber

class ViewPagerPlaceholderFragment(private val onGameClick: (GameItem?) -> Unit): Fragment() {

    private var binding: FragmentMainPlaceholderBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainPlaceholderBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val game: GameItem? = arguments?.getParcelable(ARG_GAME)

        binding?.placeholderGameName?.text = game?.name
        binding?.placeholderGameImg?.loadImage(game?.backgroundImage)

        binding?.root?.setOnClickListener {
            onGameClick(game)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}