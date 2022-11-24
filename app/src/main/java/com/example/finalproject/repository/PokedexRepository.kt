package com.example.finalproject.repository

import com.example.finalproject.model.Pokedex
import com.example.finalproject.remote.PokedexService

class PokedexRepository(
    private val client: PokedexService = PokedexService.client
) {
    suspend fun getPokedex(): List<Pokedex> = client.getPokedex()

    suspend fun search(query: String): List<Pokedex> {
        return client.getPokedex()
            .filter { query.lowercase() in it.name.lowercase() }
    }
}