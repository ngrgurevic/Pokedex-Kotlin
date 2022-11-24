package com.example.finalproject.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Pokedex
import com.example.finalproject.repository.PokedexRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokedexViewModel(
    private val repository: PokedexRepository = PokedexRepository()
): ViewModel() {
    val pokedexData: MutableLiveData<List<Pokedex>> = MutableLiveData()

    init {
        getPokedex()
    }

    fun getPokedex() {
        viewModelScope.launch(Dispatchers.IO) {
            val pokedexes:List<Pokedex> = repository.getPokedex()
            pokedexData.postValue(pokedexes)
        }
    }

    // I shouldn't use suspend fun in ViewModel
    // but other work-around didn't work so I had to use it

    suspend fun search(query: String): List<Pokedex> {
        return repository.search(query)
    }

}