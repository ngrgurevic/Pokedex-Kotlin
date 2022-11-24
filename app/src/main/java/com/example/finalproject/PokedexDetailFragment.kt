package com.example.finalproject

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.example.finalproject.databinding.FragmentPokedexDetailBinding
import com.example.finalproject.extensions.titlecase
import com.example.finalproject.repository.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokedexDetailFragment : Fragment(R.layout.fragment_pokedex_detail) {
    private lateinit var binding: FragmentPokedexDetailBinding
    private val args: PokedexDetailFragmentArgs by navArgs()
    private lateinit var favoritesRepository: FavoritesRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokedexDetailBinding.bind(view)
        favoritesRepository = FavoritesRepository(requireContext())

        binding.pokemonName.text = "Name: " + args.navArgs.name.titlecase()
        binding.pokemonId.text = "#" + args.navArgs.id.toString()

        binding.pokemonImage.load(args.navArgs.imageUrl)
        binding.pokemonType.text = "Type: " + args.navArgs.type.titlecase()
        binding.pokemonDescription.text = args.navArgs.description

        binding.attackBar.progress = args.navArgs.attack
        binding.defenseBar.progress = args.navArgs.defense
        binding.heightBar.progress = args.navArgs.height
        binding.weightBar.progress = args.navArgs.weight

        binding.pokemonAttack.text = "Attack: " + args.navArgs.attack +"pt"
        binding.pokemonDefense.text = "Defense: " + args.navArgs.defense+"pt"
        binding.pokemonHeight.text = "Height: " + args.navArgs.height.toDouble()/10+"m"
        binding.pokemonWeight.text = "Weight: " + args.navArgs.weight.toDouble() /10 +"kg"

        binding.favoriteButton.setOnClickListener { toggleFavorite() }
        //Color changing
        when(args.navArgs.type)
        {
            "fire" -> {
                binding.pokemonImage.setBackgroundColor(Color.rgb(153, 19, 12))
                binding.pokemonId.setTextColor(Color.rgb(153, 19, 12))
                binding.attackBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(153, 19, 12)))
                binding.defenseBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(153, 19, 12)))
                binding.heightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(153, 19, 12)))
                binding.weightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(153, 19, 12)))
                binding.favoriteButton.setBackgroundColor(Color.rgb(153, 19, 12))

            }
            "poison" -> {
                binding.pokemonImage.setBackgroundColor(Color.rgb(36, 166, 36))
                binding.pokemonId.setTextColor(Color.rgb(36, 166, 36))
                binding.attackBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(36, 166, 36)))
                binding.defenseBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(36, 166, 36)))
                binding.heightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(36, 166, 36)))
                binding.weightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(36, 166, 36)))
                binding.favoriteButton.setBackgroundColor(Color.rgb(36, 166, 36))
            }
            "water" ->  {
                binding.pokemonImage.setBackgroundColor(Color.rgb(9, 48, 156))
                binding.pokemonId.setTextColor(Color.rgb(9, 48, 156))
                binding.attackBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(9, 48, 156)))
                binding.defenseBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(9, 48, 156)))
                binding.heightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(9, 48, 156)))
                binding.weightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(9, 48, 156)))
                binding.favoriteButton.setBackgroundColor(Color.rgb(9, 48, 156))
            }
            "flying" ->  {
                binding.pokemonImage.setBackgroundColor(Color.rgb(21, 169, 214))
                binding.pokemonId.setTextColor(Color.rgb(21, 169, 214))
                binding.attackBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(21, 169, 214)))
                binding.defenseBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(21, 169, 214)))
                binding.heightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(21, 169, 214)))
                binding.weightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(21, 169, 214)))
                binding.favoriteButton.setBackgroundColor(Color.rgb(21, 169, 214))
            }
            "bug" ->  {
                binding.pokemonImage.setBackgroundColor(Color.rgb(24, 66, 3))
                binding.pokemonId.setTextColor(Color.rgb(24, 66, 3))
                binding.attackBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(24, 66, 3)))
                binding.defenseBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(24, 66, 3)))
                binding.heightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(24, 66, 3)))
                binding.weightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(24, 66, 3)))
                binding.favoriteButton.setBackgroundColor(Color.rgb(24, 66, 3))
            }
            "electric" ->  {
                binding.pokemonImage.setBackgroundColor(Color.rgb(179, 171, 21))
                binding.pokemonId.setTextColor(Color.rgb(179, 171, 21))
                binding.attackBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(179, 171, 21)))
                binding.defenseBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(179, 171, 21)))
                binding.heightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(179, 171, 21)))
                binding.weightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(179, 171, 21)))
                binding.favoriteButton.setBackgroundColor(Color.rgb(179, 171, 21))
            }
            "ground" ->  {
                binding.pokemonImage.setBackgroundColor(Color.rgb(79, 53, 8))
                binding.pokemonId.setTextColor(Color.rgb(79, 53, 8))
                binding.attackBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(79, 53, 8)))
                binding.defenseBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(79, 53, 8)))
                binding.heightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(79, 53, 8)))
                binding.weightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(79, 53, 8)))
                binding.favoriteButton.setBackgroundColor(Color.rgb(79, 53, 8))
            }

            "normal" ->  {
                binding.pokemonImage.setBackgroundColor(Color.rgb(207, 118, 17))
                binding.pokemonId.setTextColor(Color.rgb(207, 118, 17))
                binding.attackBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(207, 118, 17)))
                binding.defenseBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(207, 118, 17)))
                binding.heightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(207, 118, 17)))
                binding.weightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(207, 118, 17)))
                binding.favoriteButton.setBackgroundColor(Color.rgb(207, 118, 17))
            }
            "fairy" ->  {
                binding.pokemonImage.setBackgroundColor(Color.rgb(158, 17, 116))
                binding.pokemonId.setTextColor(Color.rgb(158, 17, 116))
                binding.attackBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(158, 17, 116)))
                binding.defenseBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(158, 17, 116)))
                binding.heightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(158, 17, 116)))
                binding.weightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(158, 17, 116)))
                binding.favoriteButton.setBackgroundColor(Color.rgb(158, 17, 116))
            }
            "psychic" ->  {
                binding.pokemonImage.setBackgroundColor(Color.rgb(103, 45, 117))
                binding.pokemonId.setTextColor(Color.rgb(103, 45, 117))
                binding.attackBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(103, 45, 117)))
                binding.defenseBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(103, 45, 117)))
                binding.heightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(103, 45, 117)))
                binding.weightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(103, 45, 117)))
                binding.favoriteButton.setBackgroundColor(Color.rgb(103, 45, 117))
            }
            "grass" ->  {
                binding.pokemonImage.setBackgroundColor(Color.rgb(65, 120, 42))
                binding.pokemonId.setTextColor(Color.rgb(65, 120, 42))
                binding.attackBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(65, 120, 42)))
                binding.defenseBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(65, 120, 42)))
                binding.heightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(65, 120, 42)))
                binding.weightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(65, 120, 42)))
                binding.favoriteButton.setBackgroundColor(Color.rgb(65, 120, 42))
            }
            "fighting" ->  {
                binding.pokemonImage.setBackgroundColor(Color.rgb(219, 99, 7))
                binding.pokemonId.setTextColor(Color.rgb(219, 99, 7))
                binding.attackBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(219, 99, 7)))
                binding.defenseBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(219, 99, 7)))
                binding.heightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(219, 99, 7)))
                binding.weightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(219, 99, 7)))
                binding.favoriteButton.setBackgroundColor(Color.rgb(219, 99, 7))

            }
            "steel" ->  {
                binding.pokemonImage.setBackgroundColor(Color.rgb(92, 92, 92))
                binding.pokemonId.setTextColor(Color.rgb(92, 92, 92))
                binding.attackBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(92, 92, 92)))
                binding.defenseBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(92, 92, 92)))
                binding.heightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(92, 92, 92)))
                binding.weightBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(92, 92, 92)))
                binding.favoriteButton.setBackgroundColor(Color.rgb(92, 92, 92))
            }
        }

        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.navArgs.name.titlecase()
        updateButtonText()
    }
    private fun updateButtonText() {
        lifecycleScope.launch(Dispatchers.IO) {
            val isInFavorites = favoritesRepository.isInFavorites(args.navArgs)
            val btnText = if(isInFavorites) "Remove from favorites" else "Add to favorites"

            withContext(Dispatchers.Main) {
                binding.favoriteButton.text = btnText
            }
        }
    }

    private fun toggleFavorite() {
        lifecycleScope.launch(Dispatchers.IO) {
            val isInFavorites = favoritesRepository.isInFavorites(args.navArgs)

            if(isInFavorites) {
                favoritesRepository.remove(args.navArgs)
            } else {
                favoritesRepository.add(args.navArgs)
            }

            updateButtonText()
        }
    }

}