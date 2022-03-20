package com.example.pokedox.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedox.model.Pokemon
import com.example.pokedox.repository.PokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject


@HiltViewModel()
class MainViewModel @Inject constructor(private val repository: PokeRepository) : ViewModel() {

    private val _listPokemons: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val listPokemons: LiveData<List<Pokemon>> = _listPokemons

    init {
        detail()
    }

    private fun detail() = viewModelScope.launch {

        val response = withContext(Dispatchers.IO) {
            repository.pokemonList()
        }

        if (response.isSuccessful) {
            for (o in response.body()!!.pokemon){

                if (o.name.lowercase(Locale.getDefault()) == "pikachu"){
                    val ee = o
                    Log.d("teestingww", "name ${ee}")
                }
            }
            _listPokemons.value = response.body()!!.pokemon
            Log.d("teesting", "id ${response.body()}")

        }

    }
}