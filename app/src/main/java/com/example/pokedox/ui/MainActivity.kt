package com.example.pokedox.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedox.adapter.PokeAdapter
import com.example.pokedox.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mAdapter: PokeAdapter by lazy {
        PokeAdapter(this)
    }
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter()
        showMainList()

        setContentView(binding.root)


    }

    private fun adapter() {
        binding.apply {
            animeRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 3)
            animeRecyclerView.adapter = mAdapter
            animeRecyclerView.setHasFixedSize(true)
        }
    }

    private fun showMainList() {

        viewModel.listPokemons.observe(this@MainActivity) { pokemons ->
            Log.d("teestinwg", "id ${pokemons}")
            mAdapter.pokeList = pokemons

        }
    }
}
