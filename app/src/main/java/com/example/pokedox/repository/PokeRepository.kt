package com.example.pokedox.repository

import com.example.pokedox.api.ApiServise
import com.example.pokedox.model.PokemonModel
import retrofit2.Response
import javax.inject.Inject

class PokeRepository @Inject constructor(private val api: ApiServise){

    suspend fun pokemonList(): Response<PokemonModel> = api.get_Pokemons()

}