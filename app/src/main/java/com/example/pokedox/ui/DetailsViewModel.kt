package com.example.pokedox.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedox.model.Pokemon

class DetailsViewModel: ViewModel() {

    private val _text: MutableLiveData<Pokemon> = MutableLiveData()

    private val _typePokemons: MutableLiveData<String> = MutableLiveData()
    val typePokemons: LiveData<String> = _typePokemons

    private val _nextEvolutionPokemons: MutableLiveData<String> = MutableLiveData()
    val nextEvolutionPokemons: LiveData<String> = _nextEvolutionPokemons

    private val _prevEvolutionPokemons: MutableLiveData<String> = MutableLiveData()
    val prevEvolutionPokemons: LiveData<String> = _prevEvolutionPokemons

    private val _weaknessesPokemons: MutableLiveData<String> = MutableLiveData()
    val weaknessesPokemons: LiveData<String> = _weaknessesPokemons



    fun pokemon(pokemon: Pokemon){

        for (o in pokemon.type){
            _typePokemons.value = o
        }
        try {
            for (q in pokemon.next_evolution){
                    _nextEvolutionPokemons.value = q.name
                }
        } catch (e: Exception) {
            _nextEvolutionPokemons.value = "Последняя стадия развития"
        }


        try {
            for (w in pokemon.prev_evolution){
                    _prevEvolutionPokemons.value = w.name
                }
        } catch (e: Exception) {
            _prevEvolutionPokemons.value = "Начальная стадия развития"
        }

        for (r in pokemon.weaknesses){
            _weaknessesPokemons.value = r
        }

    }
}