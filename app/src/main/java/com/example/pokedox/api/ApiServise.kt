package com.example.pokedox.api

import com.example.pokedox.model.PokemonModel
import com.example.pokedox.util.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiServise {

    @GET(Constants.END_POINT)
    suspend fun get_Pokemons(
    ): Response<PokemonModel>
}