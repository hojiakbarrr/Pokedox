package com.example.pokedox.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedox.databinding.DetailsFragmentBinding
import com.example.pokedox.model.Pokemon
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment(
    private var pokemon: Pokemon,
) : BottomSheetDialogFragment() {

    private val binding: DetailsFragmentBinding by lazy {
        DetailsFragmentBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return binding.root
    }











    @SuppressLint("SetTextI18n")
    private fun loading() {
        binding.apply {


            viewModel.pokemon(pokemon)

            rating.text = "type of pokemon"
            rating2.text = "Weaknesses"
            rating4.text = "Next_evolution"
            rating5.text = "Prev_evolution"

            Picasso.get().load(pokemon.img).into(image)
            name.text = pokemon.name

            viewModel.weaknessesPokemons.observe(this@DetailsFragment){
                weaknesses.text = it
            }
            viewModel.nextEvolutionPokemons.observe(this@DetailsFragment){
                    next.text = it
            }
            viewModel.prevEvolutionPokemons.observe(this@DetailsFragment){
                    prev.text = it
            }
            viewModel.typePokemons.observe(this@DetailsFragment){
                types.text = it
            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        // TODO: Use the ViewModel

        loading()

    }

}