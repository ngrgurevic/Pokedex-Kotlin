package com.example.finalproject

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.databinding.FragmentPokedexFavoriteBinding
import com.example.finalproject.extensions.titlecase
import com.example.finalproject.repository.FavoritesRepository
import com.example.finalproject.ui.PokedexAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PokedexFavoriteFragment : Fragment(R.layout.fragment_pokedex_favorite), PokedexSearchable {
    private lateinit var binding: FragmentPokedexFavoriteBinding
    private lateinit var repo: FavoritesRepository

    private val adapter = PokedexAdapter { pokedex ->
        val direction = PokedexFavoriteFragmentDirections.actionGlobalPokedexDetail(pokedex)
        findNavController().navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentPokedexFavoriteBinding.bind(view)
        repo = FavoritesRepository(requireContext())

        searchIntent()

        binding.pokedexFavorites.adapter = adapter
        binding.pokedexFavorites.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch(Dispatchers.IO) {
            val data = repo.getAllPokemon()
            withContext(Dispatchers.Main) {
                adapter.setData(data)
            }
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Favorites"
    }

    private fun searchIntent() {
       activity?.intent?.let { intent ->
            if (Intent.ACTION_SEARCH == intent.action) {
                intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                }
            }
        }
    }

    override fun search(query: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val data = repo.searchByName(query)
            withContext(Dispatchers.Main) {
                adapter.setData(data)
            }
        }
    }

}