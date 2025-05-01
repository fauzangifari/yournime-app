package com.fauzangifari.favorite_feature.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fauzangifari.favorite_feature.databinding.ActivityFavoriteBinding
import com.fauzangifari.favorite_feature.di.favoriteFeatureModule
import com.fauzangifari.favorite_feature.ui.adapter.FavoriteAnimeAdapter
import com.fauzangifari.yournime.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import androidx.core.net.toUri

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var favoriteAdapter: FavoriteAnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteFeatureModule)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel(){
        lifecycleScope.launchWhenStarted {
            favoriteViewModel.state.collect {
                when {
                    it.favoriteAnimeLoading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    it.favoriteAnime != null -> {
                        binding.progressBar.visibility = View.GONE
                        favoriteAdapter.submitList(it.favoriteAnime)
                    }
                    it.favoriteAnimeError != null -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@FavoriteActivity, it.favoriteAnimeError, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        favoriteAdapter = FavoriteAnimeAdapter {anime ->
            val intent = Intent(Intent.ACTION_VIEW, "https://yournime.com/detail/${anime.id}".toUri())
            startActivity(intent)
        }

        binding.rvFavoriteAnime.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvFavoriteAnime.adapter = favoriteAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
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

    override fun onResume() {
        super.onResume()
        favoriteViewModel.fetchAnimeFavorite()
    }

}