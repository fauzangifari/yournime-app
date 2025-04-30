package com.fauzangifari.favorite_feature.ui.adapter

import com.fauzangifari.yournime.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fauzangifari.domain.model.Anime

class FavoriteAnimeAdapter(
    private val onItemClick: (Anime) -> Unit
) : ListAdapter<Anime, FavoriteAnimeAdapter.AnimeViewHolder>(AnimeDiffCallback()){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_anime_detail, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: AnimeViewHolder,
        position: Int
    ) {
        val anime = getItem(position)
        holder.bind(anime)
    }


    inner class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgAnime: ImageView = itemView.findViewById(R.id.imgAnime)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvScore: TextView = itemView.findViewById(R.id.tvScore)
        private val tvInfo: TextView = itemView.findViewById(R.id.tvInfo)
        private val tvSeason: TextView = itemView.findViewById(R.id.tvSeason)
        private val tvGenre: TextView = itemView.findViewById(R.id.tvGenre)

        fun bind(anime: Anime){
            tvTitle.text = anime.title
            tvScore.text = "⭐ ${anime.score} • ${anime.scoredBy} votes"
            tvInfo.text = "${anime.type}, ${anime.episodes} eps • ${anime.duration} • ${anime.duration}"
            tvSeason.text = "${anime.status} • ${anime.season} ${anime.year}"
            tvGenre.text = anime.genre?.joinToString { it.name }

            Glide.with(itemView.context)
                .load(anime.images)
                .into(imgAnime)

            itemView.setOnClickListener {
                onItemClick(anime)
            }
        }
    }

    class AnimeDiffCallback : DiffUtil.ItemCallback<Anime>() {
        override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean =
            oldItem == newItem
    }
}