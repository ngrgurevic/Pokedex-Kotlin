package com.example.finalproject.remote

import com.example.finalproject.model.Pokedex
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokedexService {

    @GET("c5eda157e117ae5446a4")
    suspend fun getPokedex(): List<Pokedex>
    companion object {

        private const val baseUrl = "https://api.npoint.io/"

        val client: PokedexService = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokedexService::class.java)
    }
}