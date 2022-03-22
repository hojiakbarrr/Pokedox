package com.example.pokedox.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
        shimmerStart()
        swipe()
        /**
         * не менять настройки функции стоят так что никто никому не мешает
         */

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

    private fun swipe(){
        binding.apply {
            swipeToRefresh.setOnRefreshListener(this@MainActivity)
            swipeToRefresh.setColorSchemeColors(
               Color.RED,Color.BLUE,Color.DKGRAY
            )
        }
    }

    private fun showMainList() {
        viewModel.listPokemons.observe(this@MainActivity) { pokemons ->
            Log.d("teestinwg", "id ${pokemons}")
            mAdapter.pokeList = pokemons
            binding.swipeToRefresh.isRefreshing = false
            shimmerStop()

        }
    }

    private fun shimmerStop() {
        binding.apply {
            shimmerFrameLayout.stopShimmerAnimation()
            shimmerFrameLayout.visibility = View.INVISIBLE
            btnSearch.visibility = View.VISIBLE
            animeRecyclerView.visibility = View.VISIBLE
            searchInputLayout.visibility = View.VISIBLE
            searchInputEditText.visibility = View.VISIBLE
        }
    }

    private fun shimmerStart() {
        binding.apply {
            shimmerFrameLayout.startShimmerAnimation()
            shimmerFrameLayout.visibility = View.VISIBLE
            btnSearch.visibility = View.INVISIBLE
            animeRecyclerView.visibility = View.INVISIBLE
            searchInputLayout.visibility = View.INVISIBLE
            searchInputEditText.visibility = View.INVISIBLE
        }
    }

    override fun onRefresh() {
        binding.apply {
            shimmerStart()
            viewModel.detail()
            swipeToRefresh.isRefreshing = true
            swipeToRefresh.postDelayed({
                swipeToRefresh.isRefreshing =false
            }, 1500)
            shimmerStop()
        }
    }
}
