package com.fauzangifari.yournime.presentation.search

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fauzangifari.yournime.R
import com.fauzangifari.yournime.databinding.ActivitySearchBinding
import com.fauzangifari.yournime.presentation.adapter.SearchAnimeAdapter
import com.fauzangifari.yournime.presentation.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModel()

    private lateinit var searchAnimeAdapter: SearchAnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchViewModel.fetchAnimeBySearch(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun observeViewModel(){
        lifecycleScope.launchWhenStarted {
            searchViewModel.state.collect { state ->

                handleLoading(state.searchLoading)
                handleError(state.searchError)
                searchAnimeAdapter.submitList(state.searchAnimeList)

            }
        }
    }

    private fun handleLoading(isSearchAnimeLoading: Boolean){
        binding.progressBar.visibility = if (isSearchAnimeLoading) View.VISIBLE else View.GONE
    }

    private fun handleError(searchError: String?){
        searchError?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView(){
        searchAnimeAdapter = SearchAnimeAdapter { anime ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("animeId", anime.id)
            }
            startActivity(intent)
        }

        binding.rvSearchResult.adapter = searchAnimeAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}