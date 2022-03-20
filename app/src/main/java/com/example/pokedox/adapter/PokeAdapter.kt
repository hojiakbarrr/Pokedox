package com.example.pokedox.adapter

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.pokedox.R
import com.example.pokedox.databinding.PokemonItemBinding
import com.example.pokedox.model.Pokemon
import com.example.pokedox.model.PokemonModel
import com.example.pokedox.ui.DetailsFragment
import com.squareup.picasso.Picasso

class PokeAdapter(private val parentActivity: AppCompatActivity,
) : RecyclerView.Adapter<PokeAdapter.ViewHolder>() {

    var pokeList: List<Pokemon> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            binding.apply {

                Picasso.get().load(pokemon.img).into(image)
                name.text = pokemon.name

//                Glide.with(itemView.context).load(pokemon.img).into(image)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeAdapter.ViewHolder {


        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        val binding = PokemonItemBinding.bind(view)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokeAdapter.ViewHolder, position: Int) {

        holder.bind(pokeList[position])


        holder.itemView.setOnClickListener {

            DetailsFragment(pokeList[position]).apply {
                show(parentActivity.supportFragmentManager,"DetailsFragment")
            }
        }

    }

    override fun getItemCount() = pokeList.size


}