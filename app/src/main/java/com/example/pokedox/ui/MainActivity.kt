package com.example.pokedox.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pokedox.adapter.PokeAdapter
import com.example.pokedox.databinding.ActivityMainBinding
import com.example.pokedox.model.Pokemon
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(),SwipeRefreshLayout.OnRefreshListener {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mAdapter: PokeAdapter by lazy {
        PokeAdapter(this)
    }

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.swipeToRefresh.setOnRefreshListener(this)
        binding.swipeToRefresh.setColorSchemeColors(
            Color.GREEN,Color.RED,
            Color.BLUE,Color.DKGRAY
        )
        adapter()
        showMainList()
        search()





        setContentView(binding.root)
    }


    private fun search() {
        binding.apply {
            btnSearch.setOnClickListener {
                val text = searchInputEditText.text
                viewModel.search(text.toString())
                viewModel.listPokemons.observe(this@MainActivity) { pokemons ->
                    swipeToRefresh.isRefreshing = true
                    Log.d("teestinwg", "id ${pokemons}")
                    mAdapter.pokeList = pokemons
                    swipeToRefresh.isRefreshing = false
                }
            }
        }
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
            binding.swipeToRefresh.isRefreshing = false

        }
    }

    override fun onRefresh() {
        binding.apply {
            viewModel.detail()
            swipeToRefresh.isRefreshing = true
            swipeToRefresh.postDelayed({
                swipeToRefresh.isRefreshing =false
            }, 1500)
        }
    }
}
