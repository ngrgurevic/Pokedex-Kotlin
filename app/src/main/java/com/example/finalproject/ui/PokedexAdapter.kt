package com.example.finalproject.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.finalproject.R
import com.example.finalproject.databinding.PokedexItemBinding
import com.example.finalproject.extensions.titlecase
import com.example.finalproject.model.Pokedex

class PokedexAdapter(
    private val onTap: (Pokedex) -> Unit,
): RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder>() {
    class PokedexViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = PokedexItemBinding.bind(itemView)

        fun bind(pokedex: Pokedex) {
            binding.pokemonImage.load(pokedex.imageUrl)
            binding.pokemonName.text = "Name: "  + pokedex.name.titlecase()
            binding.pokemonId.text = "#" + pokedex.id.toString()
            binding.pokemonType.text = "Type: " + pokedex.type.titlecase()
        }
    }

    private var data: List<Pokedex> = listOf()

    fun setData(newData: List<Pokedex>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.pokedex_item, parent, false)
        return PokedexViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        val pokedex = data[position]
        holder.bind(pokedex)
        holder.itemView.setOnClickListener {
            onTap(pokedex)
        }
    }
}