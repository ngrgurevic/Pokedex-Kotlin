package com.example.finalproject.local

import androidx.room.*
import com.example.finalproject.model.Pokedex

@Dao
interface PokedexDao {
    @Query("SELECT * FROM pokedex")
    suspend fun getAll(): List<Pokedex>

    @Query("SELECT * FROM pokedex WHERE id = :id LIMIT 1")
    suspend fun getByID(id: Int): Pokedex?

    @Query("SELECT * FROM pokedex WHERE name LIKE '%' || :query || '%' ")
    suspend fun searchByName(query: String): List<Pokedex>

    @Insert
    suspend fun insert(pokedex: Pokedex)

    @Delete
    suspend fun delete(pokedex: Pokedex)
}


@Database(entities = [Pokedex::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokedexDao(): PokedexDao
}

