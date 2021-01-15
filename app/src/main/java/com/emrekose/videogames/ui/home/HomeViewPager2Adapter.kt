package com.emrekose.videogames.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.emrekose.videogames.ui.model.GameItem
import com.emrekose.videogames.utils.Constants.ARG_GAME

class HomeViewPager2Adapter(
    fragment: Fragment,
    val listOfGames: List<GameItem>
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = listOfGames.size

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPagerPlaceholderFragment()

        when(position) {
            0 -> fragment.arguments = Bundle().apply {
                    putParcelable(ARG_GAME, listOfGames[0])
                }
            1 -> fragment.arguments = Bundle().apply {
                    putParcelable(ARG_GAME, listOfGames[1])
                }
            2 -> fragment.arguments = Bundle().apply {
                    putParcelable(ARG_GAME, listOfGames[2])
                }
        }
        return fragment
    }
}