package com.example.pokedox.repository

import com.example.pokedox.api.ApiServise
import com.example.pokedox.model.PokemonModel
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PokeRepository @Inject constructor(private val api: ApiServise){

    suspend fun pokemonList(): Response<PokemonModel> = api.get_Pokemons()

}