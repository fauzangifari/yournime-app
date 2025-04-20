package com.fauzangifari.yournime.presentation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fauzangifari.yournime.databinding.ActivityHomeBinding
import com.fauzangifari.yournime.presentation.adapter.AiringAnimeAdapter
import com.fauzangifari.yournime.presentation.adapter.UpcomingAnimeAdapter
import com.fauzangifari.yournime.presentation.adapter.TopAnimeAdapter
import com.fauzangifari.yournime.presentation.detail.DetailActivity
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var topAnimeAdapter: TopAnimeAdapter
    private lateinit var upcomingAnimeAdapter: UpcomingAnimeAdapter
    private lateinit var airingAnimeAdapter: AiringAnimeAdapter

    private lateinit var splitInstallManager: SplitInstallManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splitInstallManager = SplitInstallManagerFactory.create(this)

        setupRecyclerView()
        observeViewModel()

        binding.btnFavorite.setOnClickListener {
            if (splitInstallManager.installedModules.contains("favorite_feature")) {
                openFavoriteActivity()
            } else {
                installFavoriteModule()
            }
        }
    }

    private fun installFavoriteModule(){
        val request = SplitInstallRequest.newBuilder()
            .addModule("favorite_feature")
            .build()

        splitInstallManager.startInstall(request)
            .addOnSuccessListener {
                Log.d("HomeActivity", "Module installed successfully")
                openFavoriteActivity()
            }
            .addOnFailureListener { exception ->
                Log.e("HomeActivity", "Failed to install module: ${exception.message}")
            }
    }

    private fun openFavoriteActivity(){
        try {
            val intent = Intent(
                this,
                Class.forName("com.fauzangifari.favorite_feature.FavoriteActivity")
            )
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            Log.e("HomeActivity", "FavoriteActivity not found: ${e.message}")
            Toast.makeText(this, "FavoriteActivity not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            homeViewModel.state.collect { state ->

                handleLoading(state.topAnimeLoading, state.upcomingAnimeLoading, state.airingAnimeLoading)

                handleError(state.topAnimeError, state.upcomingAnimeError, state.airingAnimeError)

                topAnimeAdapter.submitList(state.topAnimeList)
                upcomingAnimeAdapter.submitList(state.upcomingAnimeList)
                airingAnimeAdapter.submitList(state.airingAnimeList)
            }
        }
    }

    private fun handleLoading(isTopAnimeLoading: Boolean, isUpcomingAnimeLoading: Boolean, isAiringAnimeLoading: Boolean) {
        binding.progressTop.visibility = if (isTopAnimeLoading) View.VISIBLE else View.GONE
        binding.progressRecommend.visibility = if (isUpcomingAnimeLoading) View.VISIBLE else View.GONE
        binding.progressAiring.visibility = if (isAiringAnimeLoading) View.VISIBLE else View.GONE
    }

    private fun handleError(topError: String?, upcomingError: String?, airingError: String?) {
        topError?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        upcomingError?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        airingError?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        topAnimeAdapter = TopAnimeAdapter { anime ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("animeId", anime.id)
            }
            startActivity(intent)
        }

        upcomingAnimeAdapter = UpcomingAnimeAdapter { anime ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("animeId", anime.id)
            }
            startActivity(intent)
        }

        airingAnimeAdapter = AiringAnimeAdapter { anime ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("animeId", anime.id)
            }
            startActivity(intent)
        }

        binding.rvTopAnime.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTopAnime.adapter = topAnimeAdapter

        binding.rvUpcomingAnime.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvUpcomingAnime.adapter = upcomingAnimeAdapter

        binding.rvAiringAnime.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvAiringAnime.adapter = airingAnimeAdapter
    }
}

