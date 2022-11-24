package com.example.finalproject.ui


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.PokedexSearchable
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentPokedexListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PokedexListFragment : Fragment(R.layout.fragment_pokedex_list), PokedexSearchable {
    private val  viewModel: PokedexViewModel by activityViewModels()
    private lateinit var binding: FragmentPokedexListBinding

    private val adapter = PokedexAdapter { pokedex ->
        val direction = PokedexListFragmentDirections.actionGlobalPokedexDetail(pokedex)
        findNavController().navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokedexListBinding.bind(view)

        binding.pokedexContainer.layoutManager = LinearLayoutManager(requireContext())
        binding.pokedexContainer.adapter = adapter

        viewModel.pokedexData.observe(viewLifecycleOwner) { pokemon ->
            adapter.setData(pokemon)
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Pokedex"
    }

    override fun search(query: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val data = viewModel.search(query)
            withContext(Dispatchers.Main) {
                adapter.setData(data)
            }
        }
    }

}