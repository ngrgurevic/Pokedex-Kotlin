package com.example.finalproject

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.finalproject.databinding.ActivityMainBinding
import com.google.android.material.internal.ContextUtils.getActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val navBarConfiguration = AppBarConfiguration(setOf(
        R.id.pokedexListFragment,
        R.id.pokedexFavoriteFragment
    ))

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = (supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController

        binding.bottomNav.setupWithNavController(navController)

        setupActionBarWithNavController(navController,navBarConfiguration)

        navController.addOnDestinationChangedListener { controller, destination, bundle ->
            when(destination.id) {
                R.id.pokedexListFragment,
                R.id.pokedexFavoriteFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.pokedexDetailFragment -> binding.bottomNav.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu!!.findItem(R.id.search_action)
        val searchView = searchItem!!.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        searchIntent(intent)
    }

    private fun searchIntent(newIntent: Intent?) {
        newIntent?.let { intent ->
            if (Intent.ACTION_SEARCH == intent.action) {
                intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                    val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                    val visibleFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)

                    if (visibleFragment is PokedexSearchable) {
                        visibleFragment.search(query)
                    }
                }
            }
        }
    }

}