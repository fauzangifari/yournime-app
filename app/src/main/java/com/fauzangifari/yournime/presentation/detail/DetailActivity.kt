package com.fauzangifari.yournime.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.fauzangifari.yournime.R
import com.fauzangifari.yournime.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val animeId = intent.getIntExtra("animeId", -1)
        if (animeId != -1) {
            detailViewModel.fetchDetailAnime(animeId)
            detailViewModel.fetchIsAnimeFavorite(animeId)
        }

        observerState()

        binding.btnAddFavorite.setOnClickListener {
            if (detailViewModel.state.value.isAnimeFavorite) {
                Toast.makeText(this, "Anime sudah ada di favorit", Toast.LENGTH_SHORT).show()
            } else {
                detailViewModel.state.value.detailAnime?.let { anime ->
                    val genres = anime.genre
                    detailViewModel.insertAnimeFavorite(anime, genres)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observerState() {
        lifecycleScope.launch {
            detailViewModel.state.collectLatest { state ->
                when {
                    state.detailAnimeLoading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    state.detailAnime != null -> {
                        binding.progressBar.visibility = View.GONE
                        val anime = state.detailAnime

                        Glide.with(this@DetailActivity)
                            .load(anime.images)
                            .into(binding.imgAnime)

                        binding.tvTitle.text = anime.title
                        binding.tvInfo.text = "${anime.type}, ${anime.year} • ${anime.status} • ${anime.duration}"
                        binding.tvScore.text = "⭐ ${anime.score}"
                        binding.tvRank.text = "Rank #${anime.rank} • Popularity #${anime.popularity}"
                        binding.tvMembers.text = "Members ${anime.members} • Favorites ${anime.favorites}"
                        binding.tvGenres.text = "Genres: ${anime.genre.joinToString { it.name }}"
                        binding.tvSynopsis.text = anime.synopsis
                        binding.tvBackground.text = anime.background
                    } else -> {
                    Toast.makeText(this@DetailActivity, "${state.detailAnimeError}", Toast.LENGTH_SHORT).show()
                    }
                }

                if (state.isAnimeFavorite) {
                    binding.btnAddFavorite.text = "Sudah di Favorit"
                    binding.btnAddFavorite.isEnabled = false
                } else {
                    binding.btnAddFavorite.text = "Tambah ke Favorit"
                    binding.btnAddFavorite.isEnabled = true
                }

                when {
                    state.insertAnimeLoading -> {
                        binding.btnAddFavorite.isEnabled = false
                    }
                    state.insertAnime != null -> {
                        binding.btnAddFavorite.isEnabled = false
                        Toast.makeText(this@DetailActivity, "Berhasil menambahkan ke favorit!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
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
}

