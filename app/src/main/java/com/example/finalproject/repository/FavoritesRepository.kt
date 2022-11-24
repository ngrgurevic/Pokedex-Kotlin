package com.example.finalproject.repository

import android.content.Context
import androidx.room.Room
import com.example.finalproject.local.AppDatabase
import com.example.finalproject.model.Pokedex

class FavoritesRepository(applicationContext: Context) {

    private val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "pokedex-db"
    ).build()

    private val pokeDao = db.pokedexDao()

    suspend fun isInFavorites(pokedex: Pokedex) = pokeDao.getByID(pokedex.id) != null

    suspend fun getAllPokemon() = pokeDao.getAll()

    suspend fun add(pokedex: Pokedex) = pokeDao.insert(pokedex)

    suspend fun remove(pokedex: Pokedex) = pokeDao.delete(pokedex)

    suspend fun searchByName(query: String) = pokeDao.searchByName(query)
}