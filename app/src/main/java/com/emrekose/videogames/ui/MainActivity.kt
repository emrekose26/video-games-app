package com.emrekose.videogames.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.emrekose.videogames.R
import com.emrekose.videogames.common.SimpleTextWatcher
import com.emrekose.videogames.databinding.ActivityMainBinding
import com.emrekose.videogames.ui.home.SearchSharedViewModel
import com.emrekose.videogames.utils.gone
import com.emrekose.videogames.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val sharedViewModel: SearchSharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupViews()
        searchViewHandler()
    }

    private fun setupViews() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment,R.id.favoritesFragment, R.id.detailFragment))

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)

        bottomNavVisibilityHandler(navController)
    }

    private fun bottomNavVisibilityHandler(navController: NavController) {
        navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id) {
                R.id.homeFragment -> {
                    binding.bottomNav.visible()
                    binding.editSearch.visible()
                }
                R.id.favoritesFragment -> {
                    binding.bottomNav.visible()
                    binding.editSearch.gone()
                }
                R.id.detailFragment -> {
                    binding.bottomNav.gone()
                    binding.editSearch.gone()
                }
            }
        }
    }

    private fun searchViewHandler() {
        binding.editSearch.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sharedViewModel.setSearchQuery(s.toString())
            }
        })
    }
}